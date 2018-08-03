import { NgModule } from '@angular/core';
import { DashcardComponent } from './dashcard/dashcard.component';
import { FileUploadComponent } from './file-upload/file-upload.component';

@NgModule({
    imports: [
        DashcardComponent,
        FileUploadComponent,
    ],
    declarations: [
        DashcardComponent,
        FileUploadComponent,
    ],
  })
  export class CompsModule {}
