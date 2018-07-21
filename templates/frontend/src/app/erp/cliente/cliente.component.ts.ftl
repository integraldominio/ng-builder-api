import { Component, OnInit } from '@angular/core';
import { ${artefato.className}Service, ${artefato.className} } from './${artefato.classFolder}.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';

@Component({
  selector: 'app-${artefato.classFolder}',
  templateUrl: './${artefato.classFolder}.component.html',
  styleUrls: ['./${artefato.classFolder}.component.css']
})
export class ${artefato.className}Component implements OnInit {

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
  <#list artefato.elementos as e >
  '${e.nome}',
  </#list> 
  ];
  
  dataSource: Array<${artefato.className}> = [];

  fields: FormlyFieldConfig[] =
  [
  <#list artefato.elementos as e >  
   { key: '${e.nome}', type: 'input', templateOptions: { type: 'text', label: '${e.rotulo}', placeholder: 'Informe ${e.rotulo}', required: <#if e.requerido>true<#else>false</#if> } },
  </#list> 
  ] ;

  constructor (
    private ${artefato.classFolder}Service: ${artefato.className}Service,
    private messageService: MessageService
  ) { }

  ngOnInit() {
    this.listAll();
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.${artefato.classFolder}Service
        .create( model as ${artefato.className} )
        .subscribe(  _ => { console.log(model); this.listAll(); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  listAll() {
    this.${artefato.classFolder}Service.listAll().subscribe(
      data => {
        this.dataSource  = data as Array<${artefato.className}>;
        console.log( this.dataSource );
      }
    );
  }

  addNew () {
  }

  startEdit(cliente) {
  }

  deleteItem(o: ${artefato.className}) {
    this.${artefato.classFolder}Service.delete(o.id)
    .subscribe( _ => this.listAll() );
  }
}
