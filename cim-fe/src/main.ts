import {enableProdMode} from '@angular/core';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';

import {AppModule} from './app/app.module';
import {environment, envName} from './environments/environment';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';
import {KeycloakService} from "./app/keycloak-service/keycloak.service";

if (environment.production) {
  enableProdMode();
}
 
let banner = ""+
"               ___________.____    _______________  ___.___ \n"+
"               \\_   _____/|    |   \\_   _____/\\   \\/  /|   |\n"+
"                |    __)  |    |    |    __)_  \\     / |   |\n"+
"                |    \\    |    |___ |        \\ /     \\ |   |\n"+
"                \\___  /   |_______ \\/_______  /___/\\  \\|___|\n"+
"                    \\/\            \\/\        \\/\      \\_/\    \n";
//Log Banner
console.log('%c' + banner, 'color: blue;');

let keycloakOptions = { 
  onLoad: 'check-sso', 
  checkLoginIframeInterval: 1, 
  configUrl: 'assets/keycloak-' + envName + '.json' 
}

KeycloakService.init(keycloakOptions)
.then(() => {
  if (KeycloakService.keycloakAuth.authenticated) {
    platformBrowserDynamic().bootstrapModule(AppModule);
  } else {
    KeycloakService.keycloakAuth.login();
  }
})
.catch((e: string) => {
  console.log('Error in ng2 bootstrap: ' + e);
});
