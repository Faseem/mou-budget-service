import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {ModalComponent} from "./modal.component";

@Component({
  selector: 'confirmation-modal',
  templateUrl: './confirmation.modal.component.html',
  styleUrls: ['./confirmation-modal.component.scss']
})
export class ConfirmationModalComponent implements OnInit {

  public title = 'Are You Sure';

  @ViewChild('confirmationmodal')
  private confirmationmodal:ModalComponent;

  public text: string;

  private data: any;

  private callbackFun;

  constructor() {
  }

  ngOnInit() {
  }

  onCompleteClick(){
    this.callbackFun(this.data);
  }

  public openModal(text: string, data: any, callbackFuc) {
    this.callbackFun = callbackFuc;
    this.text = text;
    this.data = data;
    this.confirmationmodal.show();
  }

  public closeModal() {
    this.confirmationmodal.hide();
  }
}
