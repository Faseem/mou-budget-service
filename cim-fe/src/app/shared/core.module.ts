import { NgModule, ModuleWithProviders } from '@angular/core';
import { CoreService } from './core.service';

@NgModule({})
export class CoreModule {
    static forRoot(): ModuleWithProviders {
        return {
          ngModule: CoreModule,
          providers: [CoreService]
        };
      }
}