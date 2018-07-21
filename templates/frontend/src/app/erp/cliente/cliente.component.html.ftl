<div fxLayout="row wrap" class="form-group">
  <div fxFlex.gt-sm="50" class="divide">
    <mat-card>
      <mat-card-title>Clientes</mat-card-title>
      <mat-card-content>



<mat-table #table [dataSource]="dataSource" matSort class="mat-cell">

    <!--- Note that these columns can be defined in any order.
          The actual rendered columns are set as a property on the row definition" -->

  <#list artefato.elementos as e >
    <!-- ID Column -->
    <ng-container matColumnDef="${e.nome}">
      <mat-header-cell *matHeaderCellDef mat-sort-header>${e.rotulo}</mat-header-cell>
      <mat-cell *matCellDef="let row" >{{row.${e.nome}}}</mat-cell>
    </ng-container>
</#list>
  
    <!-- actions -->
    <ng-container matColumnDef="actions">
      <mat-header-cell *matHeaderCellDef>
        <button mat-icon-button color="primary" (click)="addNew()">
          <mat-icon aria-label="Example icon-button with a heart icon">add</mat-icon>
        </button>
      </mat-header-cell>

      <mat-cell *matCellDef="let row; let i=index;">
        <button mat-icon-button color="primary" (click)="startEdit(row)">
          <mat-icon aria-label="Edit">edit</mat-icon>
        </button>

        <button mat-icon-button   (click)="deleteItem(row)">
          <mat-icon aria-label="Delete">delete</mat-icon>
        </button>
      </mat-cell>
    </ng-container>

    <mat-header-row *matHeaderRowDef="displayedColumns"></mat-header-row>
    <mat-row *matRowDef="let row; columns: displayedColumns;"></mat-row>
  </mat-table>


  <div class="no-results" [style.display]="dataSource.length == 0 ? '' : 'none'">
      No results
    </div>

  <mat-paginator #paginator
                 [length]="dataSource.length"
                 [pageIndex]="0"
                 [pageSize]="10"
                 [pageSizeOptions]="[5, 10, 25, 100]">
  </mat-paginator>




</mat-card-content>
</mat-card>
</div>
</div>



<div fxLayout="row wrap" class="form-group">
  <div fxFlex.gt-sm="50" class="divide">
    <mat-card>
      <mat-card-title>Adicionar</mat-card-title>
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
