import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectedReqComponent } from './rejected-req.component';

describe('RejectedReqComponent', () => {
  let component: RejectedReqComponent;
  let fixture: ComponentFixture<RejectedReqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RejectedReqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RejectedReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
