import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { DashboardRoutingModule } from './dashboard-routing.module';
//import { StorageService } from "app/order/shared/dto/storage.service";
import { DashboardComponent } from "app/dashboard/dashboard.component";
import { NsrComponent } from "app/dashboard/nsr/nsr.component";
import { DeviceRequestComponent } from "app/dashboard/device-request/device-request.component";
import { DirectDdfComponent } from './direct-ddf/direct-ddf.component';
import { NewSolotionReqComponent } from './new-solotion-req/new-solotion-req.component';
import { MemoRequestComponent } from './memo-request/memo-request.component';
import { CallCreditsComponent } from './call-credits/call-credits.component';
import { FixedComponent } from './fixed/fixed.component';
//import { StorageService, StorageService } from "app/dashboard/shared/dto/storage.service";
import { CoreService } from "app/shared/core.service";
//import { StorageService as _storageService } from "../service/storage.service";
import { CoreModule } from "../shared/core.module";
import { HttpModule } from "@angular/http";
import { LoadingIndicatorModule } from "../shared/components/loading-indicator/loading-indicator.module";
import { ErrorHandler } from "../shared/services/error-handler";
import { Select2Module } from "ng2-select2";
import { DataTableModule } from "angular2-datatable";
import { ModalModule } from "../shared/components/modal/modal.module";
import { NgxMyDatePickerModule } from "ngx-mydatepicker";
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { ClipboardModule } from 'ngx-clipboard';
//import { UtilsService } from "../service/utils.service";
import { UserService } from "../service/user.service";
import { NguiAutoCompleteModule } from '@ngui/auto-complete';
import { CurrencyMaskModule } from "ng2-currency-mask";
import { CurrencyMaskConfig, CURRENCY_MASK_CONFIG } from "ng2-currency-mask/src/currency-mask.config";
import { KEYCLOAK_HTTP_PROVIDER } from "app/keycloak-service/keycloak.http";
import { KeycloakService } from "app/keycloak-service/keycloak.service";
import { HttpUtils } from "app/shared/utils/http-utils";
import { NotificationUtils } from "app/shared/utils/notification-utils";
//import { CustomerService } from "app/service/customer.service";
import { Validator } from "../shared/utils/validation-utils";
//import { OrderStoreService } from "app/dashboard/shared/order-store.service";
import { DateUtils } from "app/shared/utils/date-utils";
//import { DataService } from "app/dashboard/shared/dto/data.service";
//import { CartService } from "app/service/cart.service";
//import { StaticService } from "app/service/static.service";
import { ElementEventsUtils } from "app/shared/utils/element-events-utils";
import { StorageServicee } from "app/shared/services/auth/storage.service";
import { StorageService } from "app/dashboard/shared/services/storage.service";
import { CompletedReqComponent } from './completed-req/completed-req.component';
import { ApprovalReqComponent } from './approval-req/approval-req.component';
import { PendingReqComponent } from './pending-req/pending-req.component';
import { RejectedReqComponent } from './rejected-req/rejected-req.component';
import { CanceledReqComponent } from './canceled-req/canceled-req.component';
import { PendingDirectDdfComponent } from './pending-direct-ddf/pending-direct-ddf.component';
import { CompletedDirectDdfComponent } from './completed-direct-ddf/completed-direct-ddf.component';
import { RejectedDirectDdfComponent } from './rejected-direct-ddf/rejected-direct-ddf.component';
import { ApprovalComponent } from './approval/approval.component';
import { MifeService } from "app/dashboard/shared/services/mife.service";
import { InputBrnComponent } from './shared/components/input-brn/input-brn.component';
import { NetworkDetailService } from "app/dashboard/shared/services/networkDetail.service";

export const CustomCurrencyMaskConfig: CurrencyMaskConfig = {
  align: "right",
  allowNegative: true,
  allowZero: true,
  decimal: ",",
  precision: 2,
  prefix: "",
  suffix: "",
  thousands: "."
};

@NgModule({
  imports: [
    CoreModule.forRoot(),
    CommonModule,
    DashboardRoutingModule,
    HttpModule,
    LoadingIndicatorModule,
    FormsModule,
    Select2Module,
    ModalModule,
    DataTableModule,
    ReactiveFormsModule,
    NgxMyDatePickerModule,
    ToastModule.forRoot(),
    ClipboardModule,
    NguiAutoCompleteModule,
    CurrencyMaskModule
  ],
  providers:[
    KEYCLOAK_HTTP_PROVIDER,
    KeycloakService,
    HttpUtils, NotificationUtils, ErrorHandler,
    StorageServicee,
    //CustomerService,
    Validator,
    //OrderStoreService,
    StorageService,
    DateUtils,
    //DataService,
    //UtilsService,
    UserService,
    //StaticService,
    MifeService,
    NetworkDetailService,
    ElementEventsUtils,
    { provide: CURRENCY_MASK_CONFIG, useValue: CustomCurrencyMaskConfig }
  ],
  declarations: [
    DashboardComponent,
    NsrComponent,
    DeviceRequestComponent,
    DirectDdfComponent,
    NewSolotionReqComponent,
    MemoRequestComponent,
    CallCreditsComponent,
    FixedComponent,
    CompletedReqComponent,
    ApprovalReqComponent,
    PendingReqComponent,
    RejectedReqComponent,
    CanceledReqComponent,
    PendingDirectDdfComponent,
    CompletedDirectDdfComponent,
    RejectedDirectDdfComponent,
    ApprovalComponent,
    InputBrnComponent,
  ]
})
export class DashboardModule { }
