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
import { ProjetoService, Projeto } from './projeto.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';

import { PortalService } from '../portal/portal.service';

@Component({
  selector: 'app-projeto-form',
  templateUrl: './projeto-form.component.html',
  styleUrls: ['./projeto-form.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class ProjetoFormComponent implements OnInit {

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
  portal: null,
  };
  // Datatable
  displayedColumns = [
  'Portal',
  'nome',
  'descricao',
  'serverLang',
  'outputDirectory',
  'actions'
  ];
  dataSource: Array<Projeto> = [];
  // Fields
  fields: FormlyFieldConfig[] = [
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'portal', type: 'select',
      templateOptions: {
        label: 'Portal',
        placeholder: 'Informe Portal',
        required: true,
        maxLength: 50,
        valueProp: 'id',
        labelProp: 'nome',
        options: this.portalService.listAll(),
     }
  },
    {className: 'flex-1', key: 'nome', type: 'input',
      templateOptions: {
        label: 'Nome',
        placeholder: 'Informe Nome',
        required: true,
        maxLength: 100,
     }
  },
    {className: 'flex-1', key: 'descricao', type: 'input',defaultValue: 'Sistema muito legal, gerado com o ngx-buider',
      templateOptions: {
        label: 'Descrição',
        placeholder: 'Informe Descrição',
        required: true,
        maxLength: 100,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'nomeBackendApp', type: 'input',defaultValue: 'app-backend',
      templateOptions: {
        label: 'Nome Backend App',
        placeholder: 'Informe Nome Backend App',
        required: true,
        maxLength: 100,
     }
  },
    {className: 'flex-1', key: 'nomeFrontEndApp', type: 'input',defaultValue: 'app-frontend',
      templateOptions: {
        label: 'Nome Frontend App',
        placeholder: 'Informe Nome Frontend App',
        required: true,
        maxLength: 100,
     }
  },
    {className: 'flex-1', key: 'iconeApp', type: 'input',defaultValue: '/assets/icon.svg',
      templateOptions: {
        label: 'Ícone App',
        placeholder: 'Informe Ícone App',
        required: true,
        maxLength: 100,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'imageApp', type: 'input',defaultValue: 'assets/back-image.svg',
      templateOptions: {
        label: 'Image App',
        placeholder: 'Informe Image App',
        required: true,
        maxLength: 100,
     }
  },
    {className: 'flex-1', key: 'serverLang', type: 'select',defaultValue: 'java',
      templateOptions: {
        label: 'Server Language',
        placeholder: 'Informe Server Language',
        required: true,
        maxLength: 50,
        options: [{ value: 'java', label: 'java'}, {value: 'js', label: 'js'}]
     }
  },
    {className: 'flex-1', key: 'serverHost', type: 'input',defaultValue: 'localhost',
      templateOptions: {
        label: 'Server Host',
        placeholder: 'Informe Server Host',
        required: true,
        maxLength: 100,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'serverPort', type: 'input',defaultValue: 3000,
      templateOptions: {
        label: 'Server port',
        placeholder: 'Informe Server port',
        required: true,
        max: 9999,
     }
  },
    {className: 'flex-1', key: 'frontHost', type: 'input',defaultValue: 'localhost',
      templateOptions: {
        label: 'Front Host',
        placeholder: 'Informe Front Host',
        required: true,
        maxLength: 100,
     }
  },
    {className: 'flex-1', key: 'frontPort', type: 'input',defaultValue: 5000,
      templateOptions: {
        label: 'Front port',
        placeholder: 'Informe Front port',
        required: true,
        max: 99990,
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'useLogin', type: 'checkbox',defaultValue: true,
      templateOptions: {
        label: 'Use Login',
        placeholder: 'Informe Use Login',
        required: true,
        
     }
  },
    {className: 'flex-1', key: 'useRoles', type: 'checkbox',defaultValue: true,
      templateOptions: {
        label: 'Use Roles',
        placeholder: 'Informe Use Roles',
        required: true,
        
     }
  },
   ]},
    { fieldGroupClassName: 'display-flex',
    fieldGroup: [
    {className: 'flex-1', key: 'outputDirectory', type: 'input',defaultValue: 'sistemax',
      templateOptions: {
        label: 'Output Directory',
        placeholder: 'Informe Output Directory',
        required: true,
        maxLength: 100,
     }
  },
   ]},
  ];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private projetoService: ProjetoService,
    private messageService: MessageService,
    private portalService: PortalService,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.loadProjeto( parseInt( this.id ) );
  }

  setFormTitle( id: any  ) {
    this.title = 'Projetos';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
       this.model = this.portalToProjeto(this.model);
      this.projetoService
        .create( this.model as Projeto )
        .subscribe(  _ => { console.log(model);  this.router.navigate(['/projeto']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  portalToProjeto(m: any): Projeto {
      m.portal = { id: m.portal };
      return m as Projeto;
  }

  loadProjeto(id: number)  {
    if ( this.id !== null ) {
       this.projetoService.read(id).subscribe(
       data => {
       this.model  = data as Projeto;
       this.model.portal = data.portal.id;
    });
    }
  }

}
