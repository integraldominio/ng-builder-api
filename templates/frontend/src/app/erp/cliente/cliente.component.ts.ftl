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
  // Form
  form = new FormGroup({});
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  model = {};
  // Datatable
  displayedColumns = [
  <#list artefato.elementos as e >
  <#if e.showcolumn>
  '${e.nome}',
  </#if>
  </#list> 
  ];
  dataSource: Array<${artefato.className}> = [];
  // Fieds
  fields: FormlyFieldConfig[] = [
  <#list artefato.elementos as e >
  <#if e.toForm()>{
     key: '${e.nome}', type: '${e.toFormly()}',
     templateOptions: { label: '${e.rotulo}', placeholder: 'Informe ${e.rotulo}', required: ${e.requiredToString()},
     <#if e.hasOptions()>
        options: [
        ${e.options}
        ]
     </#if>
     }
     },
  </#if>
  </#list>
  ];

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
