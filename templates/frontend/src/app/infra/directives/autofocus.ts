<input type="text" id="name" class="form-control" name="name" ngModel appAutofocus required>

import { Directive, AfterViewInit, ElementRef } from '@angular/core';
 
@Directive({
  selector: '[appAutofocus]'
})
export class AutofocusDirective implements AfterViewInit {
 
  constructor(private el: ElementRef) {
  }
 
  ngAfterViewInit() {
    this.el.nativeElement.focus();
  }
 
}

///This is typescript
import {Component, Input, Output, AfterContentInit, ContentChild,
  AfterViewChecked, AfterViewInit, ViewChild,ViewChildren} from 'angular2/core';

export class AppComponent implements AfterViewInit,AfterViewChecked { 
   @ViewChildren('input') vc;
  
   ngAfterViewInit() {            
        this.vc.first.nativeElement.focus();
    }
  
 }