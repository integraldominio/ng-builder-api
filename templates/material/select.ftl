<mat-form-field>
  <mat-select placeholder="Favorite food">
    <mat-option *ngFor="let op of ${elemento.nome}" [value]="op.value">
      {{op.viewValue}}
    </mat-option>
  </mat-select>
</mat-form-field>