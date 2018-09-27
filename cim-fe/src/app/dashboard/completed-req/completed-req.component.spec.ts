import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletedReqComponent } from './completed-req.component';

describe('CompletedReqComponent', () => {
  let component: CompletedReqComponent;
  let fixture: ComponentFixture<CompletedReqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompletedReqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletedReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
