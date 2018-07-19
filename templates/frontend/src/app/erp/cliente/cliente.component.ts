import { Component, OnInit } from '@angular/core';
import { ${className}Service, ${className} } from './${classFolder}.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';

@Component({
  selector: 'app-${classFolder}',
  templateUrl: './${classFolder}.component.html',
  styleUrls: ['./${classFolder}.component.css']
})
export class ${className}Component implements OnInit {

  // form
  form = new FormGroup({});
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  model = {};

  // table
  displayedColumns = [ ${colunas} ];
  dataSource: Array<Cliente> = [];

  fields: FormlyFieldConfig[] =
  [
    ${inputs}
  ] ;

  constructor (
    private ${classFolder}Service: ${className}Service,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.listAll();
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.${classFolder}Service
        .create( model as ${className} )
        .subscribe(  _ => { console.log(model); this.listAll(); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  listAll() {
    this.${classFolder}Service.listAll().subscribe(
      data => {
        this.dataSource  = data as Array<${className}>;
        console.log( this.dataSource );
      }
    );
  }

  addNew () {
  }

  startEdit(cliente) {
  }

  deleteItem(o: ${className}) {
    this.${classFolder}Service.delete(o.id)
    .subscribe( _ => this.listAll() );
  }
}
