import {Injectable} from '@angular/core'

@Injectable()
export class NetworkState {

  private _loading: String = "Loading";
  private _initial: String = "Initial";
  private _failed: String = "Failed";
  private _success: String = "Success";
  private _currentState: String = this._initial;

  constructor() {
    this._currentState = this._initial;
  }

  isLoading(): boolean {
    return this._currentState == this._loading;
  }

  isSuccess(): boolean {
    return this._currentState == this._success;
  }

  isFailed(): boolean {
    return this._currentState == this._failed;
  }

  isInitial(): boolean {
    return this._currentState == this._initial;
  }

  setLoading() {
    this._currentState = this._loading;
  }

  setFailed() {
    this._currentState = this._failed;
  }

  setSuccess() {
    this._currentState = this._success;
  }

  get currentState(): String {
    return this._currentState;
  }

  reset() {
    this._currentState = this._initial;
  }
}
