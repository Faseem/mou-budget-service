import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CallCreditsComponent } from './call-credits.component';

describe('CallCreditsComponent', () => {
  let component: CallCreditsComponent;
  let fixture: ComponentFixture<CallCreditsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CallCreditsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CallCreditsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
