import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { NotificationUtils } from "app/shared/utils/notification-utils";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { MifeService } from "app/dashboard/shared/services/mife.service";

import * as _ from "underscore";
import * as _s from "underscore.string";

@Component({
  selector: 'input-brn',
  templateUrl: './input-brn.component.html',
  styleUrls: ['./input-brn.component.scss']
})
export class InputBrnComponent implements OnInit {

  @Output() 
  brnChangedEmitter = new EventEmitter();

  @Input()
  requestPage: string = 'New Page';

  //brNumber: string;
  brForm: FormGroup;

  searchTypes: any = [
    {name: 'BRN'},
    {name: 'CONTRACT NUMBER'},
    {name: 'CONY'},
  ]

  constructor(
    private notificationUtils: NotificationUtils,
    private formBuilder: FormBuilder,
    private mifeService: MifeService
  ) { }

  ngOnInit() {
    this.brForm = this.formBuilder.group({
      searchType: ['', [Validators.required]],
      brNumber: ['', [Validators.required]],
    });

    //this.brForm.controls['searchType'] = this.searchTypes[0].name;
  }

  onBrNumberSubmit(){
    let brNumber = this.brForm.controls['brNumber'].value;
    let type = this.brForm.controls['searchType'].value;
    let search:any = {
      inputKey : brNumber,
      inputType : type
    }
    console.log("searchType : "+ type);
    if (_s.isBlank(brNumber)) {
      return;
    }
    this.brnChangedEmitter.emit(search);
  }

}
