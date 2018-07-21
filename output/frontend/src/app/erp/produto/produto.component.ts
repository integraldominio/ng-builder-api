import { Component, OnInit } from '@angular/core';
import { ProdutoService, Produto } from './produto.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';

@Component({
  selector: 'app-produto',
  templateUrl: './produto.component.html',
  styleUrls: ['./produto.component.css']
})
export class ProdutoComponent implements OnInit {

  // form
  form = new FormGroup({});
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  model = {};

  // table
  displayedColumns = [
  'nome',
  'email',
  'telefone',
  'situacao',
  'situacaoToString',
  'registerDate',
  ];
  
  dataSource: Array<Produto> = [];

  fields: FormlyFieldConfig[] =
  [
   { key: 'nome', type: 'input', templateOptions: { type: 'text', label: 'Nome', placeholder: 'Informe Nome', required: true } },
   { key: 'email', type: 'input', templateOptions: { type: 'text', label: 'e-mail', placeholder: 'Informe e-mail', required: true } },
   { key: 'telefone', type: 'input', templateOptions: { type: 'text', label: 'Telefone', placeholder: 'Informe Telefone', required: true } },
   { key: 'situacao', type: 'input', templateOptions: { type: 'text', label: 'Situacao', placeholder: 'Informe Situacao', required: true } },
   { key: 'situacaoToString', type: 'input', templateOptions: { type: 'text', label: 'Situacao', placeholder: 'Informe Situacao', required: false } },
   { key: 'registerDate', type: 'input', templateOptions: { type: 'text', label: 'Data Registro', placeholder: 'Informe Data Registro', required: false } },
  ] ;

  constructor (
    private produtoService: ProdutoService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.listAll();
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.produtoService
        .create( model as Produto )
        .subscribe(  _ => { console.log(model); this.listAll(); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  listAll() {
    this.produtoService.listAll().subscribe(
      data => {
        this.dataSource  = data as Array<Produto>;
        console.log( this.dataSource );
      }
    );
  }

  addNew () {
  }

  startEdit(cliente) {
  }

  deleteItem(o: Produto) {
    this.produtoService.delete(o.id)
    .subscribe( _ => this.listAll() );
  }
}
