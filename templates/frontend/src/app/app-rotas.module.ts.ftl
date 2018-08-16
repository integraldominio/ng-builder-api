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

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SidenaveComponent } from './pages/sidenav/sidenav.component';
import { SobreComponent } from './pages/sobre/sobre.component';
import { ErroComponent } from './pages/erro/erro.component';

<#list projeto.artefatos as artefato >
<#if artefato.tipo == 'Crud' >  
import { ${artefato.className}GridComponent } from './erp/${artefato.classFolder}/${artefato.classFolder}-grid.component';
import { ${artefato.className}FormComponent } from './erp/${artefato.classFolder}/${artefato.classFolder}-form.component';
<#elseif artefato.tipo == 'Template' >
import { ${artefato.className}Component } from './erp/${artefato.classFolder}/${artefato.classFolder}.component';
</#if>
</#list>

import { AuthGuard, AdminGuard } from './infra/security';
import { BaseComponent } from './pages/base/base.component';
import { HomeComponent } from './pages/home/home.component';
import { UserGridComponent } from './infra/users/user-grid.component';
import { UserFormComponent } from './infra/users/user-form.component';
import { UserUploadComponent } from './infra/users/user-upload.component';

const routes: Routes =

[
  { path: '', component: BaseComponent,
    children: [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    ]
  },


 { path: '',  component: SidenaveComponent, canActivate: [AuthGuard] ,
  children: [
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'sobre', component: SobreComponent, canActivate: [AuthGuard]},
    <#list projeto.artefatos as artefato >
    <#if artefato.tipo == 'Template' >
    { path: '${artefato.classFolder}',  component: ${artefato.className}Component, canActivate: [AuthGuard] },
    <#elseif artefato.tipo == 'Crud' >
    { path: '${artefato.classFolder}', component: ${artefato.className}GridComponent, canActivate: [AuthGuard] },
    { path: '${artefato.classFolder}/edit/:id', component: ${artefato.className}FormComponent, canActivate: [AuthGuard] },
    { path: '${artefato.classFolder}/add',  component: ${artefato.className}FormComponent, canActivate: [AuthGuard] },
    </#if>
    </#list> 
    { path: 'profile', component: UserFormComponent, canActivate: [AdminGuard] },
    { path: 'user', component: UserGridComponent, canActivate: [AdminGuard] },
    { path: 'user/edit/:id', component: UserFormComponent, canActivate: [AdminGuard] },
    { path: 'user/add',  component: UserFormComponent, canActivate: [AdminGuard] },
    { path: 'user/foto/:id',  component: UserUploadComponent, canActivate: [AdminGuard] },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
  ]
},

{ path: '', redirectTo: '/login', pathMatch: 'full' },
{ path: '**', component: ErroComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes,{scrollPositionRestoration:'enabled'})],
  exports: [RouterModule]
})
export class AppRotasModule {}
