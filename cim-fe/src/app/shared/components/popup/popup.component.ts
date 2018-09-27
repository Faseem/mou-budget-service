import {Component, Input, OnInit, ViewChild} from '@angular/core';

@Component({
  selector: 'popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {
  @Input() title: string;
  @ViewChild("lgModal") lgModal: any;

  constructor() {
  }

  ngOnInit() {
  }

  show() {
    this.lgModal.show();
  }

  hide() {
    this.lgModal.hide();
  }
}
