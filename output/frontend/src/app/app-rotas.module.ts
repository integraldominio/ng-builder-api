import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SidenaveComponent } from './pages/sidenav/sidenav.component';
import { SobreComponent } from './pages/sobre/sobre.component';
import { ErroComponent } from './pages/erro/erro.component';

import { ProjetoComponent } from './erp/projeto/projeto.component';
import { ArtefatoComponent } from './erp/artefato/artefato.component';
import { ElementoComponent } from './erp/elemento/elemento.component';
import { ConfiguracaoComponent } from './erp/configuracao/configuracao.component';
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
    { path: 'projeto',  component: ProjetoComponent, canActivate: [AuthGuard] },
    { path: 'artefato',  component: ArtefatoComponent, canActivate: [AuthGuard] },
    { path: 'elemento',  component: ElementoComponent, canActivate: [AuthGuard] },
    { path: 'configuracao',  component: ConfiguracaoComponent, canActivate: [AuthGuard] },
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
