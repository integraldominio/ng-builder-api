<mat-sidenav-container class="sidenav-container" >
  <mat-sidenav
    #sidenav
    class="sidenav"
    fixedInViewport="true"
    [attr.role]="isHandset ? 'dialog' : 'navigation'"
    [mode]="(isHandset | async)!.matches ? 'over' : 'side'"
    [opened]="!(isHandset | async)!.matches">

    <mat-toolbar color="primary" class="mat-elevation-z6" style="height:56px;"  >
      <img src="./../../../assets/images/logo.svg" style="width: 32px;" /> NgERP
    </mat-toolbar>

    <mat-nav-list>
        <a mat-list-item  (click)="sidenav.toggle()" [routerLink]="['/fatura']"  >Fatura</a>
        <a mat-list-item  (click)="sidenav.toggle()" [routerLink]="['/cliente']"  >Cliente</a>
        <a mat-list-item  (click)="sidenav.toggle()" [routerLink]="['/produto']" >Produto</a>
        <a mat-list-item  (click)="sidenav.toggle()" [routerLink]="['/sobre']" >Sobre</a>
        <a mat-list-item  (click)="logout()" >Logout</a>
    </mat-nav-list>
  </mat-sidenav>

  <mat-sidenav-content  >
    <mat-toolbar color="primary" class="fixed-header mat-elevation-z6" style="height:56px;" >
      <button
        type="button"
        aria-label="Toggle sidenav"
        mat-icon-button
        (click)="sidenav.toggle()"
       >
       <!-- PQ *ngIf="(isHandset | async)!.matches" -->
        <mat-icon aria-label="Side nav toggle icon">menu</mat-icon>
      </button>

    </mat-toolbar>

    <div class="inner-sidenav-content">
      <router-outlet></router-outlet>
    </div>

  </mat-sidenav-content>
</mat-sidenav-container>
