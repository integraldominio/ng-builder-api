import { Component, OnInit } from '@angular/core';
import { ClienteService, Cliente } from './cliente.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

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
  
  dataSource: Array<Cliente> = [];

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
    private clienteService: ClienteService,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.listAll();
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.clienteService
        .create( model as Cliente )
        .subscribe(  _ => { console.log(model); this.listAll(); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  listAll() {
    this.clienteService.listAll().subscribe(
      data => {
        this.dataSource  = data as Array<Cliente>;
        console.log( this.dataSource );
      }
    );
  }

  addNew () {
  }

  startEdit(cliente) {
  }

  deleteItem(o: Cliente) {
    this.clienteService.delete(o.id)
    .subscribe( _ => this.listAll() );
  }
}
