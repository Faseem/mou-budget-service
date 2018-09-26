import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { Router } from "@angular/router";
// Libraries
import * as _ from "underscore";
// Components
//import { SalesPersonModalComponent } from 'app/common/sales-person-modal.component';
// Services
import { SideBarLink } from './side-bar.model';
import { UserService } from "../../service/user.service";
import { StorageService } from "../../dashboard/shared/services/storage.service";
import { CoreService } from 'app/shared/core.service';
// Utilities
import { NotificationUtils } from "../../shared/utils/notification-utils";
import { ErrorHandler } from "../../shared/services/error-handler";
//import { PinAuthoricationLogModalComponent } from '../../order/modals/pin-auth-log/pin-auth-log.component';
//import { ViewAssetsModalComponent } from "../../order/modals/view-assets/view-assets.component";
import { AuthGuardService } from '../../guards/auth.guard.service';
import { AuthService } from '../../shared/services/auth/auth.service';
//import { StorageService } from "app/service/storage.service";
declare var $: any;
@Component({
  selector: 'side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss'],
  providers: [UserService]
})
export class SideBarComponent implements OnInit {

  @Input() links: SideBarLink[];
  private isSearchVisible = false;

  public currentUserDetails: any;
  private selectedSalePerson = undefined;

  /* @ViewChild(SalesPersonModalComponent)
  salesPersonModalComponent: SalesPersonModalComponent;
 */
  /* @ViewChild(PinAuthoricationLogModalComponent)
  pinAuthoricationLogModalComponent: PinAuthoricationLogModalComponent;

  @ViewChild(ViewAssetsModalComponent)
  viewAssetsModalComponent: ViewAssetsModalComponent; */

  constructor(private userService: UserService,
    private errorHandler: ErrorHandler,
    private storageService: StorageService,
    private notificationUtils: NotificationUtils,
    private router: Router,
    private coreService: CoreService,
    private authGuardService: AuthGuardService,
    private authService: AuthService) {
  }

  public menuPermissionModel = {
    newOrder: false,
    viewOrderDetails: false,
    ReturnReplacement: false,
    pinAuthLogs: false,
    assets: false,
    mouCustomer: false,
    mouBudget: false,
    mouDetails: false,
    mouAgreement: false
  }
  ngOnInit() {

    this.getCurrentUserDetails();
    //this.storageService.clearSalesPerson();
    let salesPerson = this.coreService.getSalesPerson();
    if (!_.isUndefined(salesPerson)) {
      this.storageService.setSalesPerson(salesPerson);
      this.selectedSalePerson = salesPerson;
    }
  }

  ngDoCheck() {
    this.menuPermissionModel.assets = true // this.authGuardService.canAccess('ASSET');
    this.menuPermissionModel.mouAgreement = true //this.authGuardService.canAccess('MOU_AGGREEMENT')
    this.menuPermissionModel.mouBudget = true //this.authGuardService.canAccess('MOU_BUDGET')
    this.menuPermissionModel.mouCustomer = true //this.authGuardService.canAccess('MOU_CUSTOMER')
    this.menuPermissionModel.mouDetails = true //this.authGuardService.canAccess('MOU_VIEW_DETAILS')
    this.menuPermissionModel.newOrder = true //this.authGuardService.canAccess('ORDER_CAPTURE')
    this.menuPermissionModel.pinAuthLogs = true //this.authGuardService.canAccess('PIN_AUTH_LOG')
    this.menuPermissionModel.ReturnReplacement = true //this.authGuardService.canAccess('RETURN_REPLACEMENT')
    this.menuPermissionModel.viewOrderDetails = true //this.authGuardService.canAccess('VIEW_EDIT_ORDER')
  }

  getCurrentUserDetails() {
    this.currentUserDetails = undefined;
    this.notificationUtils.showMainLoading();
    this.userService.getCurrentUser()
      .subscribe(
        res => {
          this.notificationUtils.hideMainLoading();
          if (!_.isNull(res)) {
            this.currentUserDetails = res;
            this.storageService.getUser()
              .setLoginUserName(res.loginUserName)
              .setPmsProfileId(res.pmsProfileId)
              .setIdentificationNumber(res.identificationNumber)
              .setCreatedDate(res.createdDate)
            sessionStorage.setItem('currentUser', res.loginUserName),
              sessionStorage.setItem('outletName', res.outletName),
              this.storageService.getLocation()
                .setLocationCode(res.pmsLocationId)
                .setLocationName(res.branchName);
          }
        },
        error => {
          this.notificationUtils.hideMainLoading();
          //this.router.navigate(['/500']);
          //this.router.navigate(['/denied-login'], { queryParams: { code: 'OUTLET_ERROR' }, queryParamsHandling: 'merge' })
        });
  }

  salesPersonChanged(value) {
    this.selectedSalePerson = value;
  }

  removeSalesPerson() {
    this.selectedSalePerson = undefined;
    this.coreService.setSalesPerson(undefined);
  }

  /* onFilterSalesPersonClick() {
    this.salesPersonModalComponent.showModal();
  } */

  /* openPinAuthLogs() {
    this.pinAuthoricationLogModalComponent.showModal();
  }

  openViewAssets() {
    this.viewAssetsModalComponent.showModal();
  }
 */
  showLoading() {
    if ($("#search-type").length > 0) {
    } else {
      this.notificationUtils.showMainLoading();
    }
  }
}
