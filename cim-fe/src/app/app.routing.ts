import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Layouts
import { FullLayoutComponent } from './layouts/full-layout/full-layout.component';
import { SimpleLayoutComponent } from './layouts/simple-layout/simple-layout.component';
import { AuthGuard } from "./guards/auth.guard";
import { DeniedLoginComponent } from './layouts/denied-login/denied-login';
/* import { ProductListComponent } from "app/freed-om/product-list/product-list.component";
import { FreedOMAreas } from "app/freed-om/shared/constants.model";
import { ProductDetailComponent } from "app/freed-om/product-detail/product-detail.component";
import { FreedOmStaticComponent } from "app/freed-om/freed-om-static/freed-om-static.component"; */

export const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'denied-login', component: DeniedLoginComponent },
  {
    path: '',
    component: FullLayoutComponent,
    data: {
      title: 'Home',
      area: ''
    },
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        loadChildren: './dashboard/dashboard.module#DashboardModule',
        data: {
          title: ''
        }
      }
    ]
  },
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
