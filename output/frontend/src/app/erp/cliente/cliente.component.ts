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
  displayedColumns = [   ];
  dataSource: Array<Cliente> = [];

  fields: FormlyFieldConfig[] =
  [
     
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
