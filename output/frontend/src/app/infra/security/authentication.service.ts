import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { ConfigService } from './config.service';

@Injectable()
export class AuthenticationService {

    constructor(
      private http: HttpClient,
      private configService: ConfigService
    ) { }

    login(username: string, password: string) {
        return this.
            http.post<any>( this.configService.getLogin_url(), { username: username, password: password })
            .pipe(map((res: any) => {
                // login successful if there's a jwt token in the response
                if (res && res.token) {
                    console.log(JSON.stringify({ username, token: res.token }));
                    // store username and jwt token in local storage to keep user logged in between page refreshes
                    localStorage.setItem('currentUser', JSON.stringify({ username, token: res.token }));
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
}
