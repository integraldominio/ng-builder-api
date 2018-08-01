import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { SidenavService } from './sidenav.service';
import { NavigationStart, ResolveStart, Router } from '@angular/router';
import { MatSidenav } from '@angular/material';
import { AuthenticationService } from '../../infra/security';
import { OverlayContainer } from '@angular/cdk/overlay';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenaveComponent implements OnInit {

  private islogado = false;
  isToggledTheme = false;

  @ViewChild('sidenav') public sidenav: MatSidenav;

  constructor (
    private router: Router,
    private breakpointObserver: BreakpointObserver,
    private sidenavService: SidenavService,
    private authenticationService: AuthenticationService,
    private overlayContainer: OverlayContainer
  ) {}

  isHandset: Observable<BreakpointState> = this.breakpointObserver.observe(Breakpoints.Handset);

  ngOnInit(): void {
      this.sidenavService.setSidenav(this.sidenav);
  }

  logout() {
    this.toggle();
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  toggle() {
    this.isHandset
      .subscribe(
        arg => {
          if ( arg.matches ) {
            this.sidenavService.toggle();
          }
      });
  }

  theme() {
    console.log('>>>');
    this.isToggledTheme = !this.isToggledTheme;
  }
}

