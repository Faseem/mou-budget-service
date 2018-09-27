/**
 * Created by anuradha on 6/2/2017.
 */
import {Injectable} from '@angular/core';


@Injectable()
export class StorageServicee {

  static accesToken = "accessToken";

  public getAccessToken(): any {
    return this.getStorage(StorageServicee.accesToken);
  }

  public setAccessToken(token: string): any {
    if (token) {
      this.setStorage(false, StorageServicee.accesToken, token);
    }
  }

  public setStorage(islocalstorage: boolean, storageKey: string, storageItem: any): void {
    if (islocalstorage) {
      localStorage.setItem(storageKey, storageItem);
    } else {
      sessionStorage.setItem(storageKey, storageItem);
    }
  }

  public getStorage(storageKey: string): any {
    // Check for the token from either session or local storage
    let value = localStorage.getItem(storageKey);
    if (value == null) {
      value = sessionStorage.getItem(storageKey);
    }

    return value;
  }

  public clearStorage() {
    localStorage.clear();
    sessionStorage.clear();
  }
}
