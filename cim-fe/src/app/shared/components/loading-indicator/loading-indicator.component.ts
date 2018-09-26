import { Component, OnInit } from '@angular/core';
import {NetworkState} from "./network-state.service";

@Component({
  selector: 'loading-indicator',
  templateUrl: './loading-indicator.component.html',
  styleUrls: ['./loading-indicator.component.scss']
})
export class LoadingIndicatorComponent implements OnInit {

  constructor(private _networkState: NetworkState) { }

  ngOnInit() {
  }

}
