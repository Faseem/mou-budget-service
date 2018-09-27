import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidenavDatatableComponent } from './sidenav-datatable.component';

describe('SidenavDatatableComponent', () => {
  let component: SidenavDatatableComponent;
  let fixture: ComponentFixture<SidenavDatatableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidenavDatatableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidenavDatatableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
