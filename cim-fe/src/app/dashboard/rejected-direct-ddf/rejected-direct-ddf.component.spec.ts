import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RejectedDirectDdfComponent } from './rejected-direct-ddf.component';

describe('RejectedDirectDdfComponent', () => {
  let component: RejectedDirectDdfComponent;
  let fixture: ComponentFixture<RejectedDirectDdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RejectedDirectDdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RejectedDirectDdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
