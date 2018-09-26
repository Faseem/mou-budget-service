import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NsrComponent } from './nsr.component';

describe('NsrComponent', () => {
  let component: NsrComponent;
  let fixture: ComponentFixture<NsrComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NsrComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NsrComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
