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

import { Component, OnInit , ViewEncapsulation  } from '@angular/core';
import { ArtefatoService, Artefato } from './artefato.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';

import { ProjetoService } from '../projeto/projeto.service';

@Component({
  selector: 'app-artefato-form',
  templateUrl: './artefato-form.component.html',
  styleUrls: ['./artefato-form.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ArtefatoFormComponent implements OnInit {

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
  projeto: null,
  };
  // Datatable
  displayedColumns = [
  'tipo',
  'nome',
  'resourceName',
  'className',
  'actions'
  ];
  dataSource: Array<Artefato> = [];
  // Fields
  fields: FormlyFieldConfig[] = [
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'projeto', type: 'select',
      templateOptions: {
        label: 'Projeto',
        placeholder: 'Informe Projeto',
        required: true,
        maxLength: 50,
        valueProp: 'id',
        labelProp: 'nome',
        options: this.projetoService.listAll(),
     }
  },
    {className: 'flex-1', key: 'tipo', type: 'select',
      templateOptions: {
        label: 'Tipo',
        placeholder: 'Informe Tipo',
        required: true,
        maxLength: 50,
        options: [{ value: 'Crud', label: 'Crud'}, {value: 'MasterDetail', label: 'MasterDetail'}, {value: 'Template', label: 'Template'}, {value: 'Dialogo', label: 'Dialogo'}, {value: 'Report', label: 'Report'}, {value: 'Grafico', label: 'Grafico'}]
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'nome', type: 'input',defaultValue: 'Catálogo de Peças',
      templateOptions: {
        label: 'Nome',
        placeholder: 'Informe Nome',
        required: true,
        maxLength: 50,
     }
  },
    {className: 'flex-1', key: 'resourceName', type: 'input',defaultValue: 'catalogos',
      templateOptions: {
        label: 'Resource Name',
        placeholder: 'Informe Resource Name',
        required: true,
        maxLength: 501,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'className', type: 'input',defaultValue: 'Catalogo',
      templateOptions: {
        label: 'Class Name',
        placeholder: 'Informe Class Name',
        required: true,
        maxLength: 50,
     }
  },
    {className: 'flex-1', key: 'classFolder', type: 'input',defaultValue: 'catalogo',
      templateOptions: {
        label: 'Class Folder',
        placeholder: 'Informe Class Folder',
        required: true,
        maxLength: 50,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'crudEstilo', type: 'select',defaultValue: 'single',
      templateOptions: {
        label: 'Estilo Entrada de Dados',
        placeholder: 'Informe Estilo Entrada de Dados',
        required: true,
        
        options: [{ value: 'single', label: 'Coluna Simples'}, {value: 'multi', label: 'Coluna Múltipla'}]
     }
  },
    {className: 'flex-1', key: 'paginaHome', type: 'checkbox',
      templateOptions: {
        label: 'Página Home',
        placeholder: 'Informe Página Home',
        required: false,
        
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'templateTs', type: 'textarea',
      templateOptions: {
        label: 'Template Ts',
        placeholder: 'Informe Template Ts',
        required: false,
        
        rows: 3,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'templateHtml', type: 'textarea',
      templateOptions: {
        label: 'Template Html',
        placeholder: 'Informe Template Html',
        required: false,
        
        rows: 3,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'templateCss', type: 'textarea',
      templateOptions: {
        label: 'Template Css',
        placeholder: 'Informe Template Css',
        required: false,
        
        rows: 3,
     }
  },
   ]},
  ];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private artefatoService: ArtefatoService,
    private messageService: MessageService,
    private projetoService: ProjetoService,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.loadArtefato( parseInt( this.id ) );
  }

  setFormTitle( id: any  ) {
    this.title = 'Artefatos';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
       this.model = this.projetoToArtefato(this.model);
      this.artefatoService
        .create( this.model as Artefato )
        .subscribe(  _ => { console.log(model);  this.router.navigate(['/artefato']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  projetoToArtefato(m: any): Artefato {
      m.projeto = { id: m.projeto };
      return m as Artefato;
  }

  loadArtefato(id: number)  {
    if ( this.id !== null ) {
       this.artefatoService.read(id).subscribe(
       data => {
       this.model  = data as Artefato;
       this.model.projeto = data.projeto.id;
    });
    }
  }

}
