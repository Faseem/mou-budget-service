import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletedDirectDdfComponent } from './completed-direct-ddf.component';

describe('CompletedDirectDdfComponent', () => {
  let component: CompletedDirectDdfComponent;
  let fixture: ComponentFixture<CompletedDirectDdfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompletedDirectDdfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompletedDirectDdfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
