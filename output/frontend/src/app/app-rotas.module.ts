import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SidenaveComponent } from './pages/sidenav/sidenav.component';
import { SobreComponent } from './pages/sobre/sobre.component';
import { ErroComponent } from './pages/erro/erro.component';

import { ClienteComponent } from './erp/cliente/cliente.component';
import { ProdutoComponent } from './erp/produto/produto.component';

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
    { path: 'cliente',  component: ClienteComponent, canActivate: [AuthGuard] },
    { path: 'produto',  component: ProdutoComponent, canActivate: [AuthGuard] },
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
