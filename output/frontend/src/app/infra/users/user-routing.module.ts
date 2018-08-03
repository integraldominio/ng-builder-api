import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserGridComponent } from './user-grid.component';
import { UserFormComponent } from './user-form.component';
import { UserUploadComponent } from './user-upload.component';

const routes: Routes = [
    { path: '', component: UserGridComponent },
    { path: 'add', component: UserFormComponent },
    { path: 'edit/:id', component: UserFormComponent },
    { path: 'foto/:id',  component: UserUploadComponent },
  ];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class UserRoutingModule {}



