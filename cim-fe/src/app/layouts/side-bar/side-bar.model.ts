export interface SideBarLink {
  hasDivider: boolean;
  title: string;
  isDropdown: boolean;
  dropdownLinks: SideBarLink[];
  icon: string;
  routerLink: string;
  text: string;
  isFavorite: boolean;
  canAccess: boolean;
}

export interface FavoriteLink {
  link: string;
  title: string;
}

