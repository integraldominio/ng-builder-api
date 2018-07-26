import { Component, OnInit } from '@angular/core';
import { ArtefatoService, Artefato } from './artefato.service';
import { MessageService } from '../../infra/security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-artefato-form',
  templateUrl: './artefato-form.component.html',
  styleUrls: ['./artefato-form.component.css']
})
export class ArtefatoFormComponent implements OnInit {

      // form
    id: string;
    title: string;

    form = new FormGroup({});
    options: FormlyFormOptions = {
        formState: {
        awesomeIsForced: false,
        },
    };
    model = {};

    fields: FormlyFieldConfig[] =
    [
     { key: 'nome', type: 'input', templateOptions: { type: 'text', label: 'Nome', placeholder: 'Informe Nome', required: true } },
     { key: 'resourceName', type: 'input', 
     templateOptions: { type: 'text', label: 'Resource Name', placeholder: 'Informe Resource Name', required: true } },
     { key: 'className', type: 'input', 
     templateOptions: { type: 'text', label: 'Class Name', placeholder: 'Informe Class Name', required: true } },
     { key: 'classFolder', type: 'input', 
     templateOptions: { type: 'text', label: 'Class Folder', placeholder: 'Informe Class Folder', required: true } },
     { key: 'tmplateTs', type: 'input', 
     templateOptions: { type: 'text', label: 'Template Ts', placeholder: 'Informe Template Ts', required: true } },
     { key: 'templateHtml', type: 'input', 
     templateOptions: { type: 'text', label: 'Template Html', placeholder: 'Informe Template Html', required: true } },
     { key: 'templateCss', type: 'input', 
     templateOptions: { type: 'text', label: 'Template Css', placeholder: 'Informe Template Css', required: true } },
    ] ;

    constructor (
      private artefatoService: ArtefatoService,
      private messageService: MessageService,
      private router: Router,
      private route: ActivatedRoute,
    ) { }

    ngOnInit() {
      this.id = this.route.snapshot.paramMap.get('id');
      this.setFormTitle( this.id );
    }

    setFormTitle( id: any  ) {
        this.title = 'Artefato';
        if ( this.id === null ) {
            this.title = 'Novo ' + this.title;
        } else {
          this.title = 'Editar ' + this.title;
        }
    }

    onSubmit(model) {
        if (this.form.valid) {
          this.artefatoService
            .create( model as Artefato )
            .subscribe(  _ => { console.log(model); });
        } else {
          this.messageService.info('Informe corretamente dados obrigatórios.');
        }
      }
}
