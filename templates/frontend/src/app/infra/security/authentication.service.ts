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
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ConfigService } from './config.service';
import { UserService } from '../auth';
import { User, Authority } from '../users/users.service';

@Injectable()
export class AuthenticationService {

    constructor(
      private http: HttpClient,
      private configService: ConfigService,
      private userService: UserService
    ) { }

    login(username: string, password: string) {
        return this.
            http.post<any>( this.configService.getLogin_url(), { username: username, password: password })
            .pipe(map((res: any) => {
                // login successful if there's a jwt token in the response
                if (res && res.token) {
                    // console.log(JSON.stringify({ username, token: res.token }));
                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ username, token: res.token }));

                    this.userService.getMyInfo().subscribe(
                      info => {
                          // console.log( '>>>authorities: ');
                          // console.log( info );
                          // console.log( '>>>username: ');
                          // console.log( username );
                          localStorage.setItem('currentUser', JSON.stringify({ username, token: res.token, authorities: info }));
                    }
                  );

                }
            }));
    }

    getToken(): String {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      const token = currentUser && currentUser.token;
      return token ? token : '';
    }

    isLoggedIn(): boolean {
      const token: String = this.getToken();
      return token && token.length > 0;
    }

    logout(): void {
      localStorage.removeItem('currentUser');
    }

    getCurrentUser() {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      return currentUser ? currentUser : '';
    }

    isAdmin(): boolean {
      const currentUser: User = JSON.parse(localStorage.getItem('currentUser'));
      const a: Array<Authority> = currentUser.authorities;
      // console.log( '>>>authorities');
      // console.log( a );
      // console.log( a.find( aut => aut.authority === 'ROLE_ADMIN' ) );
      return true;
    }

}



