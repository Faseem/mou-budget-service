import {Component, Input} from '@angular/core';
import {Message} from "primeng/primeng";
import {PanelMessage} from "./growl.model";

@Component({
  selector: 'growl',
  templateUrl: './growl.component.html',
  styleUrls: ['./growl.component.scss']
})
export class GrowlComponent {

  list: Message[] = [];

  @Input()
  set msg(message: PanelMessage) {
    if (message != null && this.list.length > 0) {
      this.list = [];
    }
    if (message != null && this.list.length === 0) {
      this.list.push({ severity: message.severity, summary: message.summary, detail: message.detail });
      setTimeout(() => {
        this.list = [];
      }, 4000);
    }
  }
}
