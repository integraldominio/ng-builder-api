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
import { ElementoService, Elemento } from './elemento.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';

import { ArtefatoService } from '../artefato/artefato.service';

@Component({
  selector: 'app-elemento-form',
  templateUrl: './elemento-form.component.html',
  styleUrls: ['./elemento-form.component.css']
})
export class ElementoFormComponent implements OnInit {

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
  artefato: null,
  };
  // Datatable
  displayedColumns = [
  'Artefato',
  'nome',
  'rotulo',
  'actions'
  ];
  dataSource: Array<Elemento> = [];
  // Fieds
  fields: FormlyFieldConfig[] = [
  {
     key: 'artefato', type: 'select',
     templateOptions: {
        label: 'Artefato',
        placeholder: 'Informe Artefato',
        required: true,
        
        valueProp: 'id',
        labelProp: 'nome',
        options: this.artefatoService.listAll(),
     }
  },
  {
     key: 'tipoElemento', type: 'select',
     templateOptions: {
        label: 'Tipo Elemento',
        placeholder: 'Informe Tipo Elemento',
        required: true,
        
        options: [{ value: 'Autocomplete', label: 'Autocomplete'} , {value: 'Checkbox', label: 'Checkbox'}, {value: 'Chips', label: 'Chips'} , { value: 'Datepicker', label: 'Datepicker'} , {value: 'Input', label: 'Input'} , {value: 'RadioButton', label: 'RadioButton'} , { value: 'RadioButton', label: 'RadioButton'} , { value: 'Select', label: 'Select'} , {value: 'SelectMultiple', label: 'SelectMultiple'} , {value:'SelectDB', label: 'SelectDB' } , { value: 'Slidetoggle', label: 'Slidetoggle'} , {value: 'TextArea', label: 'TextArea'} , {value: 'Tooltip', label: 'Tooltip' }] 
     }
  },
  {
     key: 'tipoField', type: 'select',
     templateOptions: {
        label: 'Tipo Field',
        placeholder: 'Informe Tipo Field',
        required: true,
        
        options: [ {value: 'Boolean', label: 'Boolean'}, {value: 'Date', label: 'Date'} , {value: 'DateTime', label: 'DateTime'} ,  {value: 5, label: 'Decimal'} ,  {value: 'Integer', label: 'Integer'} , {value: 'Long', label: 'Long'} , {value: 'NotAvailable', label: 'NotAvailable'} , {value: 'String', label: 'String'} , {value: 'Time', label: 'Time'}]
     }
  },
  {
     key: 'nome', type: 'input',
     templateOptions: {
        label: 'Nome',
        placeholder: 'Informe Nome',
        required: true,
        maxLength: 50,
     }
  },
  {
     key: 'rotulo', type: 'input',
     templateOptions: {
        label: 'Rótulo',
        placeholder: 'Informe Rótulo',
        required: true,
        maxLength: 50,
     }
  },
  {
     key: 'inicial', type: 'input',
     templateOptions: {
        label: 'Valor Inicial',
        placeholder: 'Informe Valor Inicial',
        required: false,
        
     }
  },
  {
     key: 'mascara', type: 'input',
     templateOptions: {
        label: 'Máscara Edição',
        placeholder: 'Informe Máscara Edição',
        required: false,
        
     }
  },
  {
     key: 'pipe', type: 'input',
     templateOptions: {
        label: 'Máscara Display',
        placeholder: 'Informe Máscara Display',
        required: false,
        
     }
  },
  {
     key: 'dica', type: 'input',
     templateOptions: {
        label: 'Hint(dica)',
        placeholder: 'Informe Hint(dica)',
        required: false,
        
     }
  },
  {
     key: 'requerido', type: 'checkbox',
     templateOptions: {
        label: 'Requerido',
        placeholder: 'Informe Requerido',
        required: true,
        
     }
  },
  {
     key: 'persistence', type: 'checkbox',
     templateOptions: {
        label: 'Persistence',
        placeholder: 'Informe Persistence',
        required: true,
        
     }
  },
  {
     key: 'tamanho', type: 'input',
     templateOptions: {
        label: 'Tamanho',
        placeholder: 'Informe Tamanho',
        required: true,
        
     }
  },
  {
     key: 'min', type: 'input',
     templateOptions: {
        label: 'Mínimo',
        placeholder: 'Informe Mínimo',
        required: false,
        
     }
  },
  {
     key: 'max', type: 'input',
     templateOptions: {
        label: 'Máximo',
        placeholder: 'Informe Máximo',
        required: false,
        
     }
  },
  {
     key: 'linhas', type: 'input',
     templateOptions: {
        label: 'Número Linhas',
        placeholder: 'Informe Número Linhas',
        required: false,
        
     }
  },
  {
     key: 'showcolumn', type: 'checkbox',
     templateOptions: {
        label: 'Show Column',
        placeholder: 'Informe Show Column',
        required: false,
        
     }
  },
  {
     key: 'valueProp', type: 'input',
     templateOptions: {
        label: 'Value Prop',
        placeholder: 'Informe Value Prop',
        required: false,
        
     }
  },
  {
     key: 'labelProp', type: 'input',
     templateOptions: {
        label: 'Label Prop',
        placeholder: 'Informe Label Prop',
        required: false,
        
     }
  },
  {
     key: 'options', type: 'textarea',
     templateOptions: {
        label: 'Opções',
        placeholder: 'Informe Opções',
        required: false,
        
     }
  },
  {
     key: 'ordenation', type: 'input',
     templateOptions: {
        label: 'Ordem',
        placeholder: 'Informe Ordem',
        required: false,
        
     }
  },
  ];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private elementoService: ElementoService,
    private messageService: MessageService,
    private artefatoService: ArtefatoService,
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.loadElemento( parseInt( this.id ) );
  }

  setFormTitle( id: any  ) {
    this.title = 'Elementos';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
       this.model = this.artefatoToElemento(this.model);
      this.elementoService
        .create( this.model as Elemento )
        .subscribe(  _ => { console.log(model);  this.router.navigate(['/elemento']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }

  artefatoToElemento(m: any): Elemento {
      m.artefato = { id: m.artefato };
      return m as Elemento;
  }

  loadElemento(id: number)  {
    if ( this.id !== null ) {
       this.elementoService.read(id).subscribe(
       data => {
       this.model  = data as Elemento;
       this.model.artefato = data.artefato.id;
    });
    }
  }

}
