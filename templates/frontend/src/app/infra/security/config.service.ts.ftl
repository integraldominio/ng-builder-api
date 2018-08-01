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

@Injectable()
export class ConfigService {

  private _api_url = '${projeto.serverHost}:${projeto.serverPort}';
  private _refresh_token_url = this._api_url + '/refresh';
  private _login_url = this._api_url + '/auth';
  private _logout_url = this._api_url + '/logout';
  private _change_password_url = this._api_url + '/auth/password/change';
  private _tip_password_url = this._api_url + '/auth/password/tip';
  private _tip_autorities_url = this._api_url + '/auth/authorities';
  private _whoami_url = this._api_url + '/auth/whoami';
  private _signup_url = this._api_url + '/auth/signup';
  private _user_url = this._api_url + '/user';
  private _users_url = this._user_url + '/all';
  private _reset_credentials_url = this._user_url + '/reset-credentials';
  private _foo_url = this._api_url + '/foo';
  private _report_url = this._api_url + '/report';
  private _foto_user_url = this._api_url + '/users/foto';

  getFotoUserUrl() {
    return this._foto_user_url;
  }

  getApiUrl() {
    return this._api_url;
  }
  getPassowrdUrl() {
    return this._tip_password_url;
  }
  getTipPasswordUrl() {
    return this._tip_autorities_url;
  }
  getReset_credentials_url(): string {
      return this._reset_credentials_url;
  }
  getRefresh_token_url(): string {
      return this._refresh_token_url;
  }
  getWhoami_url(): string {
      return this._whoami_url;
  }
  getUsers_url(): string {
      return this._users_url;
  }
  getLogin_url(): string {
      return this._login_url;
  }
  getLogout_url(): string {
      return this._logout_url;
  }
  getChange_password_url(): string {
      return this._change_password_url;
  }
  getSignup_url(): string {
      return this._signup_url;
  }
  getReport_url(): string {
    return this._report_url;
  }
}
