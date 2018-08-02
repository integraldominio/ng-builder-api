@import '~@angular/material/theming';
@import "~@angular/material/prebuilt-themes/deeppurple-amber.css";

@include mat-core();
$app-dark-primary: mat-palette($mat-purple);
//$app-dark-primary: mat-palette($mat-indigo);
$app-dark-accent: mat-palette($mat-purple, A200, A100, A400);
$app-dark-theme: mat-dark-theme($app-dark-primary, $app-dark-accent);
.dark-theme {
  //@import "~@angular/material/prebuilt-themes/deeppurple-amber.css";
   @include angular-material-theme($app-dark-theme);
}
// Normal theme
$app-light-primary: mat-palette($mat-indigo);
// $app-light-accent: mat-palette($mat-pink, A200, A100, A400);
$app-light-accent: mat-palette($mat-indigo, A200, A100, A400);
$app-dark-accent: mat-palette($mat-indigo, A200, A100, A400);
// $app-dark-accent: mat-palette($mat-green, A200, A100, A400);
$app-light-theme: mat-light-theme($app-light-primary, $app-light-accent);
@include angular-material-theme($app-light-theme);

/* 
deeppurple-amber.css
indigo-pink.css
pink-bluegrey.css
purple-green.css
@import "~@angular/material/prebuilt-themes/deeppurple-amber.css";
*/




body{
  margin: 0;
}


/* Formulários */

:host {
  display: block;
  padding: 15px;
}

.form-check {
  display: block;
  margin: 16px 0 32px;
}

.display-block {
  display: block;
}

.divide {
  padding: 0 15px;
}
/*
.form-group {
  margin-bottom: 15px;
  margin-top: 15px;
} */

