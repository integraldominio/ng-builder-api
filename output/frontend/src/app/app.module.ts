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

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './shared/material.module';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule , HTTP_INTERCEPTORS } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormlyModule } from '@ngx-formly/core';
import { FormlyMaterialModule } from '@ngx-formly/material';
import { FormlyMatDatepickerModule } from '@ngx-formly/material/datepicker';
import { FormlyMatToggleModule } from '@ngx-formly/material/toggle';
import { AuthGuard, GuestGuard, AdminGuard, AuthenticationService,
          ConfigService, JwtInterceptor, MessageService } from './infra/security';
import { AppRotasModule } from './app-rotas.module';
import { LoginComponent } from './pages/login/login.component';
import { SidenaveComponent } from './pages/sidenav/sidenav.component';
import { SobreComponent } from './pages/sobre/sobre.component';
import { ErroComponent } from './pages/erro/erro.component';
import { BaseComponent } from './pages/base/base.component';
import { HomeComponent } from './pages/home/home.component';
import { SidenavService } from './pages/sidenav/sidenav.service';
import { AutocompleteTypeComponent } from './shared/autocomplete/autocomplete-type.component';

import { MaxCharPipe } from './infra/pipes/MaxCharPipe';
import { DashcardComponent } from './infra/comps/dashcard/dashcard.component';
import { Ng2OdometerModule } from 'ng2-odometer';

import { PortalGridComponent } from './erp/portal/portal-grid.component';
import { PortalFormComponent } from './erp/portal/portal-form.component';
import { ProjetoGridComponent } from './erp/projeto/projeto-grid.component';
import { ProjetoFormComponent } from './erp/projeto/projeto-form.component';
import { ArtefatoGridComponent } from './erp/artefato/artefato-grid.component';
import { ArtefatoFormComponent } from './erp/artefato/artefato-form.component';
import { ElementoGridComponent } from './erp/elemento/elemento-grid.component';
import { ElementoFormComponent } from './erp/elemento/elemento-form.component';
import { ConfiguracaoGridComponent } from './erp/configuracao/configuracao-grid.component';
import { ConfiguracaoFormComponent } from './erp/configuracao/configuracao-form.component';
import { BuildAppComponent } from './erp/buildapp/buildapp.component';

import { UserGridComponent } from './infra/users/user-grid.component';
import { UserFormComponent } from './infra/users/user-form.component';
import { UserService, ApiService } from './infra/auth';

@NgModule({
  declarations: [
    BaseComponent,
    SidenaveComponent,
    SobreComponent,
    ErroComponent,
    LoginComponent,
    HomeComponent,
    AutocompleteTypeComponent,
    MaxCharPipe,
    DashcardComponent,
    UserGridComponent,
    UserFormComponent,
    PortalGridComponent,
    PortalFormComponent,
    ProjetoGridComponent,
    ProjetoFormComponent,
    ArtefatoGridComponent,
    ArtefatoFormComponent,
    ElementoGridComponent,
    ElementoFormComponent,
    ConfiguracaoGridComponent,
    ConfiguracaoFormComponent,
    BuildAppComponent,
    ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRotasModule,
    FormsModule,
    FormlyMaterialModule,
    FormlyMatDatepickerModule,
    FormlyMatToggleModule,
    FormlyModule.forRoot({
      types: [{
        name: 'autocomplete',
        component: AutocompleteTypeComponent,
        wrappers: ['form-field'],
      }],
      validationMessages: [
        { name: 'required', message: 'Campo requerido' },
      ],
    }),
    Ng2OdometerModule.forRoot(),
  ],
  providers: [
    ApiService,
    AuthGuard,
    GuestGuard,
    AdminGuard,
    AuthenticationService,
    ConfigService,
    JwtInterceptor,
    MessageService,
    UserService,
    SidenavService,
    {
      /// iterceptar requisioes http e colocar token
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }
  ],
  bootstrap: [BaseComponent]
})
export class AppModule { }
