<#-- 

The following input types can be used with matInput:

color
date
datetime-local
email
month
number
password
search
tel
text
time
url
week

 -->
<mat-form-field class="example-full-width">
    <input matInput placeholder="Email" [formControl]="emailFormControl"
           [errorStateMatcher]="matcher">
    <mat-hint>Errors appear instantly!</mat-hint>
    <mat-error *ngIf="emailFormControl.hasError('email') && !emailFormControl.hasError('required')">
      Please enter a valid email address
    </mat-error>
    <mat-error *ngIf="emailFormControl.hasError('required')">
      Email is <strong>required</strong>
    </mat-error>
</mat-form-field>








import {Component} from '@angular/core';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';

/** Error when invalid control is dirty, touched, or submitted. */
export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

/** @title Input with a custom ErrorStateMatcher */
@Component({
  selector: 'input-error-state-matcher-example',
  templateUrl: './input-error-state-matcher-example.html',
  styleUrls: ['./input-error-state-matcher-example.css'],
})
export class InputErrorStateMatcherExample {
  emailFormControl = new FormControl('', [
    Validators.required,
    Validators.email,
  ]);

  matcher = new MyErrorStateMatcher();
}