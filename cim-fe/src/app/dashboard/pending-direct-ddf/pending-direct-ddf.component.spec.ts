import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PendingDirectDdfComponent } from './pending-direct-ddf.component';

describe('PendingDirectDdfComponent', () => {
  let component: PendingDirectDdfComponent;
  let fixture: ComponentFixture<PendingDirectDdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PendingDirectDdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PendingDirectDdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
