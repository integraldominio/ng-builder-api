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
import { PortalService, Portal } from './portal.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-portal-form',
  templateUrl: './portal-form.component.html',
  styleUrls: ['./portal-form.component.css']
})
export class PortalFormComponent implements OnInit {

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
  'nome',
  'descricao',
  'templateTs',
  'templateCss',
  'templateHtml',
  'actions'
  ];
  dataSource: Array<Portal> = [];
  // Fieds
  fields: FormlyFieldConfig[] = [
  {
     key: 'nome', type: 'textarea',
     templateOptions: {
        label: 'Nome',
        placeholder: 'Informe Nome',
        required: true,
        maxLength: 100,
     }
  },
  {
     key: 'descricao', type: 'textarea',
     templateOptions: {
        label: 'descricao',
        placeholder: 'Informe descricao',
        required: true,
        maxLength: 100,
     }
  },
  {
     key: 'templateTs', type: 'textarea',
     templateOptions: {
        label: 'template Ts',
        placeholder: 'Informe template Ts',
        required: true,
        maxLength: 100,
     }
  },
  {
     key: 'templateCss', type: 'textarea',
     templateOptions: {
        label: 'template Css',
        placeholder: 'Informe template Css',
        required: true,
        maxLength: 100,
     }
  },
  {
     key: 'templateHtml', type: 'textarea',
     templateOptions: {
        label: 'template Html',
        placeholder: 'Informe template Html',
        required: true,
        maxLength: 100,
     }
  },
  ];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private portalService: PortalService,
    private messageService: MessageService,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.loadPortal( parseInt( this.id ) );
  }

  setFormTitle( id: any  ) {
    this.title = 'Portais';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.portalService
        .create( model as Portal )
        .subscribe(  _ => { console.log(model);  this.router.navigate(['/portal']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }


  loadPortal(id: number)  {
    if ( this.id !== null ) {
       this.portalService.read(id).subscribe(
       data => {
       this.model  = data as Portal;
    });
    }
  }

}
