import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from "app/dashboard/dashboard.component";
import { DashboardAreas } from "app/dashboard/shared/constant-modals";
import { AuthGuard } from "app/guards/auth.guard";
import { NsrComponent } from "app/dashboard/nsr/nsr.component";
import { DeviceRequestComponent } from "app/dashboard/device-request/device-request.component";
import { CallCreditsComponent } from "app/dashboard/call-credits/call-credits.component";
import { DirectDdfComponent } from "app/dashboard/direct-ddf/direct-ddf.component";
import { FixedComponent } from "app/dashboard/fixed/fixed.component";
import { MemoRequestComponent } from "app/dashboard/memo-request/memo-request.component";
import { NewSolotionReqComponent } from "app/dashboard/new-solotion-req/new-solotion-req.component";
import { CompletedReqComponent } from "app/dashboard/completed-req/completed-req.component";
import { ApprovalReqComponent } from "app/dashboard/approval-req/approval-req.component";
import { PendingReqComponent } from "app/dashboard/pending-req/pending-req.component";
import { RejectedReqComponent } from "app/dashboard/rejected-req/rejected-req.component";
import { CanceledReqComponent } from "app/dashboard/canceled-req/canceled-req.component";
import { PendingDirectDdfComponent } from "app/dashboard/pending-direct-ddf/pending-direct-ddf.component";
import { CompletedDirectDdfComponent } from "app/dashboard/completed-direct-ddf/completed-direct-ddf.component";
import { RejectedDirectDdfComponent } from "app/dashboard/rejected-direct-ddf/rejected-direct-ddf.component";
import { ApprovalComponent } from "app/dashboard/approval/approval.component";

const routes: Routes = [{
  path: 'dashboard',
  component: DashboardComponent,
  data: {
    title: 'home',
    area: DashboardAreas.Home
  },
  canActivate: [AuthGuard],
  children: [
    {
      path: 'nsr',
      component: NsrComponent,
      data: {
        title: 'NSR',
        area: DashboardAreas.NSR
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'devicerequest',
      component: DeviceRequestComponent,
      data: {
        title: 'Device Request',
        area: DashboardAreas.DeviceRequest
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'fixed',
      component: FixedComponent,
      data: {
        title: 'Fixed',
        area: DashboardAreas.Fixed
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'memorequest',
      component: MemoRequestComponent,
      data: {
        title: 'Memo Request',
        area: DashboardAreas.MemoRequest
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'newsolutionreq',
      component: NewSolotionReqComponent,
      data: {
        title: 'New Solution Req',
        area: DashboardAreas.NewSolutionReq
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'callcredits',
      component: CallCreditsComponent,
      data: {
        title: 'Call Credits',
        area: DashboardAreas.CallCredits
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'directddf',
      component: DirectDdfComponent,
      data: {
        title: 'Direct DDF',
        area: DashboardAreas.DirectDDF
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'completedreq',
      component: CompletedReqComponent,
      data: {
        title: 'Completed Req',
        area: DashboardAreas.CompletedReq
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'approvalreq',
      component: ApprovalReqComponent,
      data: {
        title: 'Approval Req',
        area: DashboardAreas.ApprovalReq
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'pendingreq',
      component: PendingReqComponent,
      data: {
        title: 'Pending Req',
        area: DashboardAreas.PendingReq
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'rejectedreq',
      component: RejectedReqComponent,
      data: {
        title: 'Rejected Req',
        area: DashboardAreas.RejectedReq
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'canceledreq',
      component: CanceledReqComponent,
      data: {
        title: 'Canceled Req',
        area: DashboardAreas.CanceledReq
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'pendingdirectddf',
      component: PendingDirectDdfComponent,
      data: {
        title: 'Pending Direct DDF',
        area: DashboardAreas.PendingDirectDDF
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'completeddirectddf',
      component: CompletedDirectDdfComponent,
      data: {
        title: 'Completed Direct DDF',
        area: DashboardAreas.CompletedDirectDDF
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'rejecteddirectddf',
      component: RejectedDirectDdfComponent,
      data: {
        title: 'Rejected Direct DDF',
        area: DashboardAreas.RejectedDirectDDF
      },
      canActivate: [AuthGuard]
    },
    {
      path: 'approval',
      component: ApprovalComponent,
      data: {
        title: 'Approval',
        area: DashboardAreas.Approval
      },
      canActivate: [AuthGuard]
    },
  ]
},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
