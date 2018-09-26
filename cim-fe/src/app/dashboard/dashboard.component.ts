import {Component, OnInit} from '@angular/core';
import {FullLayoutService} from '../layouts/full-layout/full-layout.service';
import {AuthGuardService} from '../guards/auth.guard.service';

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(fullLayoutService: FullLayoutService, private authGuardService: AuthGuardService) {
  }

  ngOnInit() {
  }
}
