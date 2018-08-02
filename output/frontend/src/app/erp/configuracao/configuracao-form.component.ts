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
import { ConfiguracaoService, Configuracao } from './configuracao.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-configuracao-form',
  templateUrl: './configuracao-form.component.html',
  styleUrls: ['./configuracao-form.component.css']
})
export class ConfiguracaoFormComponent implements OnInit {

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
  };
  // Datatable
  displayedColumns = [
  'nomeEmpresa',
  'siteEmpresa',
  'emailEmpresa',
  'outputDirectory',
  'appProperties',
  'actions'
  ];
  dataSource: Array<Configuracao> = [];
  // Fieds
  fields: FormlyFieldConfig[] = [
  {
     key: 'nomeEmpresa', type: 'input',
     templateOptions: {
        label: 'Nome Empresa',
        placeholder: 'Informe Nome Empresa',
        required: true,
        maxLength: 100,
     }
  },
  {
     key: 'siteEmpresa', type: 'input',
     templateOptions: {
        label: 'Site Empresa',
        placeholder: 'Informe Site Empresa',
        required: false,
        
     }
  },
  {
     key: 'emailEmpresa', type: 'input',
     templateOptions: {
        label: 'Email Empresa',
        placeholder: 'Informe Email Empresa',
        required: false,
        
     }
  },
  {
     key: 'outputDirectory', type: 'input',
     templateOptions: {
        label: 'Output Directory',
        placeholder: 'Informe Output Directory',
        required: false,
        
     }
  },
  {
     key: 'appProperties', type: 'input',
     templateOptions: {
        label: 'Application Properties',
        placeholder: 'Informe Application Properties',
        required: false,
        
     }
  },
  ];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private configuracaoService: ConfiguracaoService,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.loadConfiguracao( parseInt( this.id ) );
  }

  setFormTitle( id: any  ) {
    this.title = 'Configuração';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.configuracaoService
        .create( model as Configuracao )
        .subscribe(  _ => { console.log(model);  this.router.navigate(['/configuracao']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }


  loadConfiguracao(id: number)  {
    if ( this.id !== null ) {
       this.configuracaoService.read(id).subscribe(
       data => {
       this.model  = data as Configuracao;
    });
    }
  }

}
