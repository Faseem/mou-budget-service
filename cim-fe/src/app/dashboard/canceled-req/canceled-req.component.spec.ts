import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CanceledReqComponent } from './canceled-req.component';

describe('CanceledReqComponent', () => {
  let component: CanceledReqComponent;
  let fixture: ComponentFixture<CanceledReqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CanceledReqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CanceledReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
