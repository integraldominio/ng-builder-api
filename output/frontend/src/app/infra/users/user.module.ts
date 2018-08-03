import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core'; // necessário para diretitas do usuário: [ CUSTOM_ELEMENTS_SCHEMA ]
import { CommonModule } from '@angular/common';  // nessessário não encontra diretivas do angular se nãpo colocar....
import { UserRoutingModule } from './user-routing.module';
import { UserFormComponent } from './user-form.component';
import { UserGridComponent } from './user-grid.component';
import { UserUploadComponent } from './user-upload.component';
import { MaterialModule } from '../../shared/material.module';
import { FormlyModule } from '@ngx-formly/core';
import { FormlyMaterialModule } from '@ngx-formly/material';
import { FormlyMatDatepickerModule } from '@ngx-formly/material/datepicker';
import { FormlyMatToggleModule } from '@ngx-formly/material/toggle';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CompsModule } from './../comps/comps.mpdule';

@NgModule({
    imports: [
    CommonModule,
        UserRoutingModule,
        MaterialModule,
        FormlyMatToggleModule,
        FormlyMatDatepickerModule,
        FormlyMaterialModule,
        FormsModule,
        ReactiveFormsModule,
        FormlyModule,
        CompsModule
      ],
    declarations: [
      UserFormComponent,
      UserGridComponent,
      UserUploadComponent
    ],
    schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
  })
  export class UserModule {}
