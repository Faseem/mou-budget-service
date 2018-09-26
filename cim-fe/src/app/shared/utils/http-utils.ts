import {Headers, Http, RequestOptions} from "@angular/http";
import {Injectable, OnInit} from "@angular/core";
import {Observable} from 'rxjs/Observable';
import "rxjs/Rx";

/**
 * Created by manoj on 6/19/17.
 */

const globalRequestOptions = new RequestOptions({
  headers: new Headers({'Content-Type': 'application/json'}),
  withCredentials: true
});

@Injectable()
export class HttpUtils {
  ngOnInit() {
    console.log('init')
  }

  constructor(private _http: Http) {
  }

  httpGet(url: string, headers?: Headers): any {
    return this._http
      .get(url, (headers != null) ? headers : globalRequestOptions)
      .map((res: any) => this.handleResponse(res))
      .catch((error: any) => this.handleError(error));
  }

  httpPost(url: string, body: any, headers?: Headers): any {
    return this._http.post(url, body, (headers != null) ? headers : globalRequestOptions)
      .map((res: any) => this.handleResponse(res))
      .catch((error: any) => this.handleError(error));
  }

  httpPostOnlyHeaders(url: string, body: any, headers?: Headers): any {
    return this._http.post(url, body, (headers != null) ? headers : globalRequestOptions)
    .catch((error: any) => this.handleError(error));
  }

  httpPut(url: string, body: any, headers?: Headers): any {
    return this._http.put(url, body, (headers != null) ? headers : globalRequestOptions)
      .map((res: any) => this.handleResponse(res))
      .catch((error: any) => this.handleError(error));
  }

  httpPatch(url: string, body: any, headers?: Headers): any {
    return this._http.patch(url, body, (headers != null) ? headers : globalRequestOptions)
      .map((res: any) => this.handleResponse(res))
      .catch((error: any) => this.handleError(error));
  }

  httpDelete(url: string, headers?: Headers): any {
    return this._http.delete(url, (headers != null) ? headers : globalRequestOptions)
      .map((res: any) => this.handleResponse(res))
      .catch((error: any) => this.handleError(error));
  }


  private handleResponse(res: any): any {
    return res.json();
  }

  private handleError(error: any) {
    return Observable.throw(error || 'Internal server error');
  }
}
