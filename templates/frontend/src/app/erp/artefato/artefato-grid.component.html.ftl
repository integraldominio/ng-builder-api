<div fxLayout="row wrap" class="form-group">
  <div fxFlex.gt-sm="100" class="divide">
    <mat-card>
      <mat-card-title>${artefato.nome}</mat-card-title>

      <a mat-fab color="accent" routerLink="/${artefato.classFolder}/add" >
        <mat-icon aria-label="add">add</mat-icon>
      </a>
    <mat-card-content>
    <div class="table-header">
        <mat-form-field>
          <input matInput (keyup)="applyFilter($event.target.value)" placeholder="Filter">
        </mat-form-field>
    </div>

	<mat-table #table [dataSource]="dataSource" matSort class="mat-cell">

	   <#list artefato.elementos as e >
	    <!-- ID Column -->
	    <ng-container matColumnDef="${e.nome?uncap_first}">
	      <mat-header-cell *matHeaderCellDef mat-sort-header>${e.rotulo}</mat-header-cell>
	      <mat-cell *matCellDef="let row" >{{row.${e.nome?uncap_first}<#if e.selectDB()>.${e.labelProp}</#if>}}</mat-cell>
	    </ng-container>
	  </#list>
  
    <!-- actions -->
    <ng-container matColumnDef="actions">
      <mat-header-cell *matHeaderCellDef >Actions</mat-header-cell>
      <mat-cell *matCellDef="let row"  >
 
        <button mat-mini-fab color="primary"routerLink="/${artefato.classFolder}/edit/{{row.id}}">
          <mat-icon aria-label="Edit">edit</mat-icon>
        </button>
        <button  mat-mini-fab   (click)="deleteItem(row)">
          <mat-icon aria-label="Delete">delete</mat-icon>
        </button>

      </mat-cell>
    </ng-container> 

    <mat-header-row *matHeaderRowDef="displayedColumns"></mat-header-row>
    <mat-row *matRowDef="let row; columns: displayedColumns;"></mat-row>
  </mat-table>


 <!-- <div class="no-results" [style.display]="dataSource.length == 0 ? '' : 'none'">
      No results
  </div> -->

 
  <mat-paginator [pageSizeOptions]="[5, 10, 25, 100]"></mat-paginator>

</mat-card-content>
</mat-card>
</div>
</div>
 
