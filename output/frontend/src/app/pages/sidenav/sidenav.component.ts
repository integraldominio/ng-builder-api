import { Component, OnInit, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { SidenavService } from './sidenav.service';
import { NavigationStart, ResolveStart, Router } from '@angular/router';
import { MatSidenav } from '@angular/material';
import { AuthenticationService } from '../../infra/security';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenaveComponent implements OnInit {

  private islogado = false;

  @ViewChild('sidenav') public sidenav: MatSidenav;

  constructor (
    private router: Router,
    private breakpointObserver: BreakpointObserver,
    private sidenavService: SidenavService,
    private authenticationService: AuthenticationService
  ) {}

  isHandset: Observable<BreakpointState> = this.breakpointObserver.observe(Breakpoints.Handset);

  ngOnInit(): void {

  }

  logout() {
    this.sidenav.toggle();
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}

