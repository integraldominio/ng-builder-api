<div fxLayout="row wrap" class="form-group">
    <div fxFlex.gt-sm="100" class="divide">
      <mat-card>
        <mat-card-title> {{title}}  </mat-card-title>
        <mat-card-content>

        <form [formGroup]="form" >
          <formly-form
            [form]="form"
            [options]="options"
            [fields]="fields"
            [model]="model">
            <button mat-button (click)="onSubmit(model)" >Salvar</button>
            <button mat-button (click)="options.resetModel()">Limpar</button>
          </formly-form>
        </form>

  </mat-card-content>
  </mat-card>
  </div>
  </div>
  