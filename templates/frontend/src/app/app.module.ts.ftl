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

<#list projeto.artefatos as artefato >  
import { ${artefato.className}Component } from './erp/${artefato.classFolder}/${artefato.classFolder}.component';
</#list>

@NgModule({
  declarations: [
    BaseComponent,
    SidenaveComponent,
    SobreComponent,
    ErroComponent,
    LoginComponent,
    HomeComponent,
    
 	<#list projeto.artefatos as artefato >  
    ${artefato.className}Component,
	</#list>   
    
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