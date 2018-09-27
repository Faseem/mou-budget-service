import {AfterViewInit, Component, OnInit ,ViewChild} from '@angular/core';
import {SideBarLink, FavoriteLink} from '../side-bar/side-bar.model';
import {FullLayoutService} from './full-layout.service';
import {Router} from "@angular/router";
import {AuthGuardService} from "../../guards/auth.guard.service";
import {AuthService} from "../../shared/services/auth/auth.service";
import {GrowlService} from "../../shared/components/growl/growl.service";
import {CoreService} from 'app/shared/core.service';
import {PanelMessage} from "../../shared/components/growl/growl.model";
//import {OrderAreas} from "../../order/shared/constants.model";
import * as pkg from '../../../../package.json';
//import {ConfigurationsAreas} from "../../configurations/shared/constants.model";
/* import {MOUAreas} from "../../mou/shared/constants.model";
import {ViewOrderAreas} from "../../view-order/shared/constants.model"; */
import { KeycloakService } from "../../../app/keycloak-service/keycloak.service";
//import {AppInfoService} from '../../service/app-info.service';
import {NotificationUtils} from '../../shared/utils/notification-utils'
import { DashboardAreas } from "app/dashboard/shared/constant-modals";

declare var $: any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './full-layout.component.html',
})
export class FullLayoutComponent implements OnInit, AfterViewInit {

  public disabled = false;
  public status: { isopen: boolean } = { isopen: false };
  links: SideBarLink[];
  favLinks: FavoriteLink[];
  canAccessOrder = false;
  canAccessConfigurations = false;
  canAccessMou = false;
  canAccessViewOrder = false;
  growlMsg: PanelMessage;
  isShowMobileMenu = false;
  appVersion;//front end version

  userName: string;
  role: string
  image: any;

  public toggled(open: boolean): void {
    console.log('Dropdown is now: ', open);
  }

  public toggleDropdown($event: MouseEvent): void {
    $event.preventDefault();
    $event.stopPropagation();
    this.status.isopen = !this.status.isopen;
  }

  ngOnInit(): void {
    this.canAccessOrder = this.authGuardService.canAccess(DashboardAreas.Home);
    this.appVersion = pkg['version'];
    this.canAccessConfigurations = this.authGuardService.canAccess(DashboardAreas.DeviceRequest);
    /* this.canAccessMou = this.authGuardService.canAccess(MOUAreas.MOU);
    this.canAccessViewOrder = this.authGuardService.canAccess(ViewOrderAreas.ViewOrder); */
    if (this.role == undefined) this.role = sessionStorage.getItem('role');
    if (this.image == undefined) this.image = sessionStorage.getItem('image');
    if (this.userName == undefined) this.userName = sessionStorage.getItem('user');
    //this.appInfoService.initializeWebSocketConnection();
  }

  ngDoCheck() {
    if (this.role == undefined) this.role = sessionStorage.getItem('role');
    if (this.image == undefined) this.image = sessionStorage.getItem('image');
    if (this.userName == undefined) this.userName = sessionStorage.getItem('user');
  }

  public constructor(private router: Router,
    private fullLayoutService: FullLayoutService,
    private authGuardService: AuthGuardService,
    private authService: AuthService,
    private growlService: GrowlService,
    private coreService: CoreService,
    private keycloakService: KeycloakService,
    //private appInfoService: AppInfoService,
    private notificationService: NotificationUtils) {

    router.events.subscribe((val: any) => {
      if (val.url === '/') {
        fullLayoutService.clearLinks();
      }
    });

    this.links = fullLayoutService.links;

    growlService.messageShowed$.subscribe(
      data => {
        this.growlMsg = data;
      }
    )

  }

  logout() {
    this.coreService.setSalesPerson(undefined);
    this.authService.logout();
  }

  ngAfterViewInit() {
    $(function () {
      $('[data-toggle="tooltip"]').tooltip()
    })
  }



  
}
