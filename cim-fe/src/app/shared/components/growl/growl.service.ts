import {Injectable} from "@angular/core";
import {Subject} from "rxjs/Subject";
import {PanelMessage} from "./growl.model";
/**
 * Created by anuradha on 6/15/2017.
 */
@Injectable()
export class GrowlService {
  private messageShowSource = new Subject<PanelMessage>();
  messageShowed$ = this.messageShowSource.asObservable();

  showMessage(msg: PanelMessage) {
    this.messageShowSource.next(msg);
  }
}
