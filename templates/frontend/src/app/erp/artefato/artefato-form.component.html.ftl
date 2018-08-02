<div fxLayout="row wrap" class="form-group">
    <div fxFlex.gt-sm="100" class="divide">
      <mat-card>
        
        <mat-card-title>{{title}}</mat-card-title>
        <a mat-raised-button color="accent"  routerLink="/${artefato.classFolder}" >
          <mat-icon>chevron_left</mat-icon>
        </a>
        <button mat-raised-button color="accent" (click)="onSubmit()"><mat-icon>save</mat-icon></button>
        <button mat-raised-button color="accent" (click)="options.resetModel()"><mat-icon>clear</mat-icon></button>
        
        <mat-card-content>

        <form [formGroup]="form" (ngSubmit)="onSubmit(model)" >
          <formly-form
            [form]="form"
            [options]="options"
            [fields]="fields"
            [model]="model">
          </formly-form>
        </form>

  </mat-card-content>
  </mat-card>
  </div>
  </div>
  