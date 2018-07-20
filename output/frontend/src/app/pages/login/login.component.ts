import { Component, OnInit , OnDestroy} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy  {

  username: string;
  password: string;
  token: 'token: FAKE';

  constructor( private router: Router) {}

  ngOnInit() {
  }

  ngOnDestroy(): void {
  }

  login(): void {
    if (this.username === 'admin' && this.password === 'admin') {
     localStorage.setItem('currentUser', JSON.stringify({ username: this.username, token: this.token }));
     this.router.navigate( ['home'] );
    } else {
      alert('"Invalid credentials');
    }
  }
}
