import {Component, OnInit} from '@angular/core';
import {Cardapio, CardapioService} from './cardapio.service';
import {FormGroup} from '@angular/forms';
import {FormlyFormOptions, FormlyFieldConfig} from '@ngx-formly/core';
import {MessageService} from '../../infra/security';

@Component({
  selector: 'app-cardapio',
  templateUrl: './cardapio.component.html',
  styleUrls: ['./cardapio.component.css']
})

export class CardapioComponent implements OnInit {

  // form
  form = new FormGroup({});
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  model = {};

  // table
  displayedColumns = ['id', 'nome', 'categoria', 'descricao', 'image_source', 'valor', 'actions'];
  dataSource: Array<Cardapio> = [];

  fields: FormlyFieldConfig[] =
    [
      {
        key: 'descricao',
        type: 'input',
        templateOptions: {type: 'text', label: 'Nome', placeholder: 'Entre nome', required: true, }
      },

    ];

  constructor(
    private cardapioService: CardapioService,
    private messageService: MessageService
  ) {
  }

  ngOnInit() {
    this.listAll();
  }

  onSubmit(model) {
    if (this.form.valid) {

      if ((this.model as Cardapio).id != null) {

        this.cardapioService
          .update(model as Cardapio)
          .subscribe(_ => {
            console.log(model)
            this.listAll();
          });

      } else {

        this.cardapioService
          .create(model as Cardapio)
          .subscribe(_ => {
            console.log(model);
            this.listAll();
          });

      }

    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  listAll() {
    this.cardapioService.listAll().subscribe(
      data => {
        this.dataSource = data as Array<Cardapio>;
        console.log(this.dataSource);
      }
    );
  }

  addNew() {
  }

  startEdit(cardapio) {
  }

  onRowClicked(row) {
    this.model = row as Cardapio
  }

  deleteItem(cardapio: Cardapio) {
    this.cardapioService.delete(cardapio.id)
      .subscribe(_ => this.listAll());
  }

}
