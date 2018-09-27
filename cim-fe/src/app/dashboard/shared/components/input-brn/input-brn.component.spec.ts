import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputBrnComponent } from './input-brn.component';

describe('InputBrnComponent', () => {
  let component: InputBrnComponent;
  let fixture: ComponentFixture<InputBrnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputBrnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputBrnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
