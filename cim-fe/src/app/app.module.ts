import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { CoreModule } from './shared/core.module';
import { HashLocationStrategy, LocationStrategy } from "@angular/common";

import { AppComponent } from "./app.component";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TabsModule } from "ngx-bootstrap/tabs";
import { NAV_DROPDOWN_DIRECTIVES } from "./shared/directives/nav-dropdown.directive";
import { NgxMyDatePickerModule } from "ngx-mydatepicker";
import { ToastModule } from 'ng2-toastr/ng2-toastr';
import { ClipboardModule } from 'ngx-clipboard';

import { SIDEBAR_TOGGLE_DIRECTIVES } from "./layouts/side-bar/sidebar.directive";
import { AsideToggleDirective } from "./shared/directives/aside.directive";
// Routing Module
import { AppRoutingModule } from "./app.routing";
// Layouts
import { FullLayoutComponent } from "./layouts/full-layout/full-layout.component";
import { SideBarComponent } from "./layouts/side-bar/side-bar.component";
import { FullLayoutService } from "./layouts/full-layout/full-layout.service";
import { SimpleLayoutComponent } from "./layouts/simple-layout/simple-layout.component";
// Auth
import { AuthGuard } from "./guards/auth.guard";
import { AuthGuardService } from "./guards/auth.guard.service";
import { AuthService } from "./shared/services/auth/auth.service";
//import { ApiService } from "app/shared/services/api.service";
import { BreadcrumbComponent } from "./shared/components/breadcrumb/breadcrumb.component";
import { HttpModule } from "@angular/http";
//import {AuthConfig, AuthHttp} from "angular2-jwt";
import { StorageServicee } from "./shared/services/auth/storage.service";
import { GrowlService } from "./shared/components/growl/growl.service";
import { GrowlModule } from "./shared/components/growl/growl.module";
import { LoadingIndicatorModule } from "./shared/components/loading-indicator/loading-indicator.module";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ErrorHandler } from "./shared/services/error-handler";
import { NotificationUtils } from "./shared/utils/notification-utils";
import { Validator } from "./shared/utils/validation-utils";
//import { StorageService } from "./service/storage.service";
// Keycloak
import { KEYCLOAK_HTTP_PROVIDER } from "./keycloak-service/keycloak.http";
import { KeycloakService } from "./keycloak-service/keycloak.service";
import { HttpUtils } from "./shared/utils/http-utils";

//import { SalesPersonModalComponent } from './common/sales-person-modal.component';
import { ModalModule } from "./shared/components/modal/modal.module";
//import { PinAuthoricationLogModalComponent } from "./order/modals/pin-auth-log/pin-auth-log.component";
import { DataTableModule } from "angular2-datatable";
//import {ViewAssetsModalComponent} from "./order/modals/view-assets/view-assets.component";
import {StringUtils} from "./shared/utils/string-utils";
import { DeniedLoginComponent } from "./layouts/denied-login/denied-login";
import { HasPermissionDirective } from "./directives/has-permission.directive";
//import {AppInfoService} from './service/app-info.service';
import { StorageService } from "app/dashboard/shared/services/storage.service";

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    FormsModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    GrowlModule,
    LoadingIndicatorModule,
    NgxMyDatePickerModule,
    ToastModule.forRoot(),
    ClipboardModule,
    ModalModule,
    CoreModule.forRoot(),
    DataTableModule,
    ReactiveFormsModule
  ],
  declarations: [
    AppComponent,
    BreadcrumbComponent,
    FullLayoutComponent,
    SimpleLayoutComponent,
    NAV_DROPDOWN_DIRECTIVES,
    SIDEBAR_TOGGLE_DIRECTIVES,
    AsideToggleDirective,
    SideBarComponent,
    //SalesPersonModalComponent,
    //PinAuthoricationLogModalComponent,
    //ViewAssetsModalComponent,
    DeniedLoginComponent,
    HasPermissionDirective

  ],
  providers: [
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy,
    },
    // {
    //   provide: AuthHttp,
    //   useFactory: authHttpServiceFactory,
    //   deps: [Http, RequestOptions]
    // },
    FullLayoutService,
    AuthGuard,
    ErrorHandler,
    NotificationUtils,
    StringUtils,
    Validator,
    AuthGuardService,
    AuthService,
    //ApiService,
    StorageServicee,
    GrowlService,
    StorageService,
    KEYCLOAK_HTTP_PROVIDER,
    KeycloakService,
    HttpUtils,
    //AppInfoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
