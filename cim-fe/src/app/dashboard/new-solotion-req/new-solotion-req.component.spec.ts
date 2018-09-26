import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSolotionReqComponent } from './new-solotion-req.component';

describe('NewSolotionReqComponent', () => {
  let component: NewSolotionReqComponent;
  let fixture: ComponentFixture<NewSolotionReqComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewSolotionReqComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewSolotionReqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
