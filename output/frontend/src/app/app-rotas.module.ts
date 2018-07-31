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

import { AuthGuard } from './infra/security';
import { BaseComponent } from './pages/base/base.component';
import { HomeComponent } from './pages/home/home.component';

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
    { path: 'portal', component: PortalGridComponent, canActivate: [AuthGuard] },
    { path: 'portal/edit/:id', component: PortalFormComponent, canActivate: [AuthGuard] },
    { path: 'portal/add',  component: PortalFormComponent, canActivate: [AuthGuard] },
    { path: 'projeto', component: ProjetoGridComponent, canActivate: [AuthGuard] },
    { path: 'projeto/edit/:id', component: ProjetoFormComponent, canActivate: [AuthGuard] },
    { path: 'projeto/add',  component: ProjetoFormComponent, canActivate: [AuthGuard] },
    { path: 'artefato', component: ArtefatoGridComponent, canActivate: [AuthGuard] },
    { path: 'artefato/edit/:id', component: ArtefatoFormComponent, canActivate: [AuthGuard] },
    { path: 'artefato/add',  component: ArtefatoFormComponent, canActivate: [AuthGuard] },
    { path: 'elemento', component: ElementoGridComponent, canActivate: [AuthGuard] },
    { path: 'elemento/edit/:id', component: ElementoFormComponent, canActivate: [AuthGuard] },
    { path: 'elemento/add',  component: ElementoFormComponent, canActivate: [AuthGuard] },
    { path: 'configuracao', component: ConfiguracaoGridComponent, canActivate: [AuthGuard] },
    { path: 'configuracao/edit/:id', component: ConfiguracaoFormComponent, canActivate: [AuthGuard] },
    { path: 'configuracao/add',  component: ConfiguracaoFormComponent, canActivate: [AuthGuard] },
    { path: 'buildapp',  component: BuildAppComponent, canActivate: [AuthGuard] },
    { path: '', redirectTo: 'home', pathMatch: 'full' }
  ]
},

{ path: '', redirectTo: '/login', pathMatch: 'full' },
{ path: '**', component: ErroComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRotasModule {}
