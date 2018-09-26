import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingReqComponent } from './pending-req.component';

describe('PendingReqComponent', () => {
  let component: PendingReqComponent;
  let fixture: ComponentFixture<PendingReqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingReqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
