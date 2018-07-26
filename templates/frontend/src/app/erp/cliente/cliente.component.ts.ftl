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

<#list artefato.elementos as e >
<#if e.selectDB() >
import { ${e.nome}Service } from '../${e.nome?lower_case}/${e.nome?lower_case}.service';
</#if>
</#list>

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
    private ${artefato.classFolder}Service: ${artefato.className}Service,
    private messageService: MessageService,
	<#list artefato.elementos as e >
    <#if e.selectDB() >
    private ${e.nome?lower_case}Service: ${e.nome}Service,
	</#if>
	</#list>    
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
