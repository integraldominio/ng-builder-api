<ng-progress></ng-progress>
<mat-sidenav-container [class.dark-theme]="isToggledTheme"  >
  <mat-sidenav
    #sidenav
    class="sidenav"
    fixedInViewport="true"
    [attr.role]="isHandset ? 'dialog' : 'navigation'"
    [mode]="(isHandset | async)!.matches ? 'over' : 'side'"
    [opened]="!(isHandset | async)!.matches">

    <mat-toolbar color="primary" class="mat-elevation-z6" style="height:56px;"  >

      <button type="button" aria-label="Toggle sidenav" mat-icon-button (click)="sidenav.toggle()" >
        <mat-icon aria-label="Side nav toggle icon">menu</mat-icon>
      </button>
      <img src="./../../../assets/images/logo.svg" style="width: 20px; padding-right: 5px" /> ngx-builder

    </mat-toolbar>

    <mat-nav-list>
        <a mat-list-item  (click)="toggle()" [routerLink]="['/home']" >Home</a>
    <#list projeto.artefatos as artefato > 
        <a mat-list-item  (click)="toggle()" [routerLink]="['/${artefato.classFolder}']" >${artefato.nome}</a>
	</#list>        
        <a mat-list-item  (click)="toggle()" [routerLink]="['/user']" *ngIf="isAdmin()" >Usuários</a>
        <a mat-list-item  (click)="toggle()" [routerLink]="['/profile']" >Meu Perfil</a>
        <a mat-list-item  (click)="theme()" >Tema</a>
        <a mat-list-item  (click)="toggle()" [routerLink]="['/sobre']" >Sobre</a>
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
      <img src="./../../../assets/images/logo.svg" style="width: 20px; padding-right: 5px" /> ngx-builder

      <span class="example-spacer"></span>

      <button mat-icon-button [matMenuTriggerFor]="moreMenu" aria-label="More"
      (click)="$event.stopPropagation();">
      <mat-icon class="secondary-text">person</mat-icon> 
      </button>
      <mat-menu #moreMenu="matMenu">
          <button mat-menu-item aria-label="edit" [routerLink]="['/profile']">
              <mat-icon>edit</mat-icon>
              <span>Meu perfil</span>
          </button>
      </mat-menu>
      <button mat-icon-button  (click)="logout()" aria-label="More">
      <mat-icon class="secondary-text">exit_to_app</mat-icon> 
      </button>

    </mat-toolbar>

    <div class="inner-sidenav-content">
      <router-outlet></router-outlet>
    </div>

  </mat-sidenav-content>
</mat-sidenav-container>
