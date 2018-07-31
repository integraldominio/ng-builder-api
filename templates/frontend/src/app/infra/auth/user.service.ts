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
import { ApiService } from './api.service';
import { ConfigService } from '../security/config.service';
import { map } from 'rxjs/operators';
@Injectable()
export class UserService {

  currentUser;

  constructor(
    private apiService: ApiService,
    private config: ConfigService
  ) { }

  initUser() {
    const promise = this.apiService.get(this.config.getRefresh_token_url()).toPromise()
    .then(res => {
      if (res.access_token !== null) {
        return this.getMyInfo().toPromise()
        .then(user => {
          this.currentUser = user;
        });
      }
    })
    .catch(() => null);
    return promise;
  }

  resetCredentials() {
    return this.apiService.get(this.config.getReset_credentials_url());
  }

  getMyInfo() {
    return this.apiService.get(this.config.getWhoami_url()).pipe( map(user => this.currentUser = user));
  }

  getAll() {
    return this.apiService.get(this.config.getUsers_url());
  }

  getReport() {
    return this.apiService.get(this.config.getReport_url());
  }

  logout() {
    this.currentUser = null;
    localStorage.removeItem('currentUser');
  }

}
