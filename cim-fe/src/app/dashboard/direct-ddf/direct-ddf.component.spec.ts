import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectDdfComponent } from './direct-ddf.component';

describe('DirectDdfComponent', () => {
  let component: DirectDdfComponent;
  let fixture: ComponentFixture<DirectDdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DirectDdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DirectDdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
