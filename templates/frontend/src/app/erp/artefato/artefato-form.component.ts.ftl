/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

import { Component, OnInit } from '@angular/core';
import { ${artefato.className}Service, ${artefato.className} } from './${artefato.classFolder}.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';

<#list artefato.elementos as e >
<#if e.selectDB() >
import { ${e.nome}Service } from '../${e.nome?lower_case}/${e.nome?lower_case}.service';
</#if>
</#list>

@Component({
  selector: 'app-${artefato.classFolder}-form',
  templateUrl: './${artefato.classFolder}-form.component.html',
  styleUrls: ['./${artefato.classFolder}-form.component.css']
})
export class ${artefato.className}FormComponent implements OnInit {

  id: string;
  title: string;
  // Form
  form = new FormGroup({});
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  model = {
  <#list artefato.elementos as e >
  <#if e.toForm()>
  <#if e.selectDB() >
  ${${e.nome?uncap_first}:null,
  </#if>
  </#if>
  </#list>
  };
  // Datatable
  displayedColumns = [
  <#list artefato.elementos as e >
  <#if e.showcolumn>
  '${e.nome}',
  </#if>
  </#list> 
  'actions'
  ];
  dataSource: Array<${artefato.className}> = [];
  // Fieds
  fields: FormlyFieldConfig[] = [
  <#list artefato.elementos as e >
  <#if e.toForm()>{
     key: '${e.nome?uncap_first}', type: '${e.toFormly()}', <#if e.inicial??>defaultValue: ${e.getDefault()},</#if>
     templateOptions: {
        label: '${e.rotulo}',
        placeholder: 'Informe ${e.rotulo}',
        required: ${e.requiredToString()},
        <#if e.selectDB() >
        valueProp: '${e.valueProp}',
        labelProp: '${e.labelProp}',
        options: this.${e.nome?lower_case}Service.listAll(),
        <#elseif e.hasOptions() >
        options: ${e.options}
        </#if>
     }
  },
  </#if>
  </#list>
  ];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private ${artefato.classFolder}Service: ${artefato.className}Service,
    private messageService: MessageService,
	<#list artefato.elementos as e >
    <#if e.selectDB() >
    private ${e.nome?lower_case}Service: ${e.nome}Service,
	</#if>
	</#list>    
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.load${artefato.className}( parseInt( this.id ) );
  }

  setFormTitle( id: any  ) {
    this.title = '${artefato.nome}';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
       <#list artefato.elementos as e >
       <#if e.toForm()>
       <#if e.selectDB() >
       model = this.${e.nome?lower_case}To${artefato.className}(model);
	   </#if>
	   </#if>
	   </#list>
      this.${artefato.classFolder}Service
        .create( model as ${artefato.className} )
        .subscribe(  _ => { console.log(model);  this.router.navigate(['/${artefato.classFolder}']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  <#list artefato.elementos as e >
  <#if e.toForm()>
  <#if e.selectDB() >
  ${e.nome?lower_case}To${artefato.className}(m: any): ${artefato.className} {
      m.${e.nome?uncap_first} = { id: m.${e.nome?uncap_first} };
      return m as ${artefato.className};
  }
  </#if>
  </#if>
  </#list>

  load${artefato.className}(id: number)  {
    if ( this.id !== null ) {
       this.${artefato.classFolder}Service.read(id).subscribe(
       data => {
       this.model  = data as ${artefato.className};
       <#list artefato.elementos as e >
       <#if e.toForm()>
       <#if e.selectDB() >
       this.model.${e.nome?uncap_first} = data.${e.nome?uncap_first}.id;
       </#if>
       </#if>
       </#list>
    });
    }
  }

}
