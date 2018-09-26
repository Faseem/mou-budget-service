/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import {Injectable} from '@angular/core';
import {
  Http,
  Request,
  XHRBackend,
  ConnectionBackend,
  RequestOptions,
  RequestOptionsArgs,
  Response,
  Headers
} from '@angular/http';

import {KeycloakService} from './keycloak.service';
import {Observable} from 'rxjs/Rx';

/**
 * This provides a wrapper over the ng2 Http class that insures tokens are refreshed on each request.
 */
@Injectable()
export class KeycloakHttp extends Http {
  constructor(_backend: ConnectionBackend, _defaultOptions: RequestOptions, private _keycloakService: KeycloakService) {
    super(_backend, _defaultOptions);
  }

  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
    if (!this._keycloakService.authenticated()) return super.request(url, options)
      .catch((error: Response) => this.handleUnauthorizedRequests(error));

    let token = this._keycloakService.getTokenWithoutPromise();

    if (typeof url === 'string') {
      const authOptions = new RequestOptions({headers: new Headers({'Authorization': 'Bearer ' + token})});
      let opts = new RequestOptions().merge(options).merge(authOptions);
      return super.request(url, opts).catch((error: Response) => this.handleUnauthorizedRequests(error));
    } else if (url instanceof Request) {
      url.headers.set('Authorization', 'Bearer ' + token);
      return super.request(url).catch((error: Response) => this.handleUnauthorizedRequests(error));
    }
  }

  handleUnauthorizedRequests(error: Response) {
    if ((error.status == 401 || error.status == 403)/* && (window.location.href.match(/\?/g) || []).length < 2*/) {
      this._keycloakService.login();
    }
    return Observable.throw(error.json());
  }
}

export function keycloakHttpFactory(backend: XHRBackend, defaultOptions: RequestOptions, keycloakService: KeycloakService) {
  return new KeycloakHttp(backend, defaultOptions, keycloakService);
}

export const KEYCLOAK_HTTP_PROVIDER = {
  provide: Http,
  useFactory: keycloakHttpFactory,
  deps: [XHRBackend, RequestOptions, KeycloakService]
};
