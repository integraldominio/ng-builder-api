import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MaterialModule } from './shared/material.module';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormlyModule } from '@ngx-formly/core';
import { FormlyMaterialModule } from '@ngx-formly/material';

import { AuthGuard, GuestGuard, AdminGuard, AuthenticationService,
          ConfigService, JwtInterceptor, MessageService, UserService } from './infra/security';
 
import { AppRotasModule } from './app-rotas.module';
import { LoginComponent } from './pages/login/login.component';
import { SidenaveComponent } from './pages/sidenav/sidenav.component';
import { SobreComponent } from './pages/sobre/sobre.component';
import { ErroComponent } from './pages/erro/erro.component';
import { BaseComponent } from './pages/base/base.component';
import { HomeComponent } from './pages/home/home.component';
import { SidenavService } from './pages/sidenav/sidenav.service';

import { ProjetoComponent } from './erp/projeto/projeto.component';
import { ArtefatoComponent } from './erp/artefato/artefato.component';
import { ElementoComponent } from './erp/elemento/elemento.component';
import { ConfiguracaoComponent } from './erp/configuracao/configuracao.component';
import { BuildAppComponent } from './erp/buildapp/buildapp.component';

@NgModule({
  declarations: [
    BaseComponent,
    SidenaveComponent,
    SobreComponent,
    ErroComponent,
    LoginComponent,
    HomeComponent,
    
    ProjetoComponent,
    ArtefatoComponent,
    ElementoComponent,
    ConfiguracaoComponent,
    BuildAppComponent,
    
    ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    FormsModule,
    FormlyModule.forRoot(),
    FormlyMaterialModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRotasModule
  ],
  providers: [
    AuthGuard,
    GuestGuard,
    AdminGuard,
    AuthenticationService,
    ConfigService,
    JwtInterceptor,
    MessageService,
    UserService,
    SidenavService
  ],
  bootstrap: [BaseComponent]
})
export class AppModule { }
