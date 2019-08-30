/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

import { Component, OnInit, ViewChild } from "@angular/core";
import {
  BreakpointObserver,
  Breakpoints,
  BreakpointState
} from "@angular/cdk/layout";
import { Observable } from "rxjs";
import { SidenavService } from "./sidenav.service";
import { NavigationStart, ResolveStart, Router } from "@angular/router";
import { MatSidenav } from "@angular/material/sidenav";
import { AuthenticationService } from "../../infra/security";
import { OverlayContainer } from "@angular/cdk/overlay";

@Component({
  selector: "app-sidenav",
  templateUrl: "./sidenav.component.html",
  styleUrls: ["./sidenav.component.css"]
})
export class SidenaveComponent implements OnInit {
  private islogado = false;
  isToggledTheme = false;

  @ViewChild("sidenav", { static: false }) public sidenav: MatSidenav;

  constructor(
    private router: Router,
    private breakpointObserver: BreakpointObserver,
    private sidenavService: SidenavService,
    private authenticationService: AuthenticationService,
    private overlayContainer: OverlayContainer
  ) {}

  isHandset: Observable<BreakpointState> = this.breakpointObserver.observe(
    Breakpoints.Handset
  );

  ngOnInit(): void {
    this.sidenavService.setSidenav(this.sidenav);
  }

  logout() {
    this.toggle();
    this.authenticationService.logout();
    this.router.navigate(["/login"]);
  }

  toggle() {
    this.isHandset.subscribe(arg => {
      if (arg.matches) {
        this.sidenavService.toggle();
      }
    });
  }

  theme() {
    console.log(">>>");
    this.isToggledTheme = !this.isToggledTheme;
  }

  isAdmin(): boolean {
    return this.authenticationService.isAdmin();
  }
}
