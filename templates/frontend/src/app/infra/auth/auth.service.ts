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

import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service';
import { UserService } from './user.service';
import { ConfigService } from '../security/config.service';
import { MatSnackBar } from '@angular/material';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class AuthService {

  constructor(
    private apiService: ApiService,
    private userService: UserService,
    private config: ConfigService,
    private msg: MatSnackBar

  ) { }

  login(user) {
    const loginHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body =  JSON.stringify({username: user.username, password: user.password});
    return this.apiService.post(this.config.getLogin_url(), body)
      .pipe( map(() => {
      console.log('Login success');
      this.msg.open('Login success');
      this.userService.getMyInfo().subscribe();
    }) );
  }

  signup(user) {
    return this.apiService.post(this.config.getSignup_url(), JSON.stringify(user))
    .pipe(
      tap( () => {
                  console.log('Sign up success');
                  this.msg.open('Sign up success');
                } )
    )
  }

  logout() {
    return this.apiService.get(this.config.getLogout_url(), {})
      .pipe( map(() => {
        this.userService.currentUser = null;
        this.msg.open('Logout success');
      }) );
  }

  changePassowrd(passwordChanger) {
    return this.apiService.post(this.config.getChange_password_url(), passwordChanger);
  }

}
