
import { Injectable } from '@angular/core';
import {SideBarLink} from '../side-bar/side-bar.model';

@Injectable()
export class FullLayoutService {
  links: SideBarLink[]
  constructor() {
    this.links = [];
  }

  public setLinks(links: SideBarLink[]) {
    this.clearLinks();
    links.forEach(l => {
      this.links.push(l);
      }
    );
  }

  public clearLinks() {
    while (this.links.length) {
      this.links.pop();
    }
  }
}
