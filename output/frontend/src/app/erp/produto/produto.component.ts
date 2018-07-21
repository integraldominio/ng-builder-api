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
  displayedColumns = [   ];
  dataSource: Array<Produto> = [];

  fields: FormlyFieldConfig[] =
  [
     
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
