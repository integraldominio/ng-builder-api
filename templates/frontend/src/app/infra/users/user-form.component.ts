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
import { UsersService, User } from './users.service';
import { MessageService } from '../security';
import { FormGroup} from '@angular/forms';
import { FormlyFieldConfig, FormlyFormOptions } from '@ngx-formly/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ConfigService } from './../security/config.service';


@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  // upload
  labelUploadButtom = 'Upload Foto';
  target = '';

  //
  id: string;
  title: string;
  // Form
  form = new FormGroup({});
  options: FormlyFormOptions = {
    formState: {
      awesomeIsForced: false,
    },
  };
  model: User = new User;

  // Fieds
  fields: FormlyFieldConfig[] = [
  {
     key: 'username', type: 'input',
     templateOptions: {
        label: 'Login (Min:4 Max:10)',
        placeholder: 'Informe nome login',
        required: true,
        minLength: 4,
        maxLength: 50,
     }
  },
  {
    key: 'firstname', type: 'input',
    templateOptions: {
       label: 'Nome (Min:4 Max:10)',
       placeholder: 'Informe Nome',
       required: true,
       minLength: 4,
       maxLength: 50,
   }
 },
 {
  key: 'lastname', type: 'input',
  templateOptions: {
     label: 'Sobrenome (Min:4 Max:10)',
     placeholder: 'Informe Sobrenome',
     required: true,
     minLength: 4,
     maxLength: 50,
}
},
{
  key: 'email', type: 'input',
  templateOptions: {
     label: 'Emai (Min:4 Max:10)',
     placeholder: 'Informe Email',
     required: true,
     minLength: 4,
     maxLength: 50,
}
},
{
  key: 'phone', type: 'input',
  templateOptions: {
     label: 'Telefone (Min:4 Max:10)',
     placeholder: 'Informe Telefone',
     required: true,
     minLength: 4,
     maxLength: 50,
}
},
{
  key: 'password', type: 'input',
  templateOptions: {
     type: 'password',
     label: 'Senha (Min:4 Max:10)',
     placeholder: 'Informe Senha',
     required: true,
     minLength: 4,
     maxLength: 10,

  }
},
{
  key: 'enabled', type: 'checkbox',
  templateOptions: {
     label: 'Situação',
     required: true,
  }
},
];

  constructor (
    private router: Router,
    private route: ActivatedRoute,
    private userService: UsersService,
    private messageService: MessageService,
    private configService: ConfigService
  ) { }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setFormTitle( this.id );
    this.loadUser( parseInt( this.id ) );
    this.target = this.configService.getFotoUserUrl();
  }

  setFormTitle( id: any  ) {
    this.title = 'Usuário';
    if ( this.id === null ) {
       this.title = 'Novo ' + this.title;
    } else {
       this.title = 'Editar ' + this.title;
    }
  }

  onSubmit(model) {
    if (this.form.valid) {
      this.userService
        .create( this.model as User )
        .subscribe(  _ => { this.router.navigate(['/user']); });
    } else {
      this.messageService.info('Informe corretamente dados obrigatórios.');
    }
  }


  loadUser(id: number)  {
    if ( this.id !== null ) {
       this.userService.read(id).subscribe(
       data => {
         console.log('>>>');
         console.log(this.model);
       this.model  = data as User;
    });
    }
  }

 onFileComplete(data: any) {
    console.log(data); // We just print out data bubbled up from event emitter.
 }

}
