import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MemoRequestComponent } from './memo-request.component';

describe('MemoRequestComponent', () => {
  let component: MemoRequestComponent;
  let fixture: ComponentFixture<MemoRequestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemoRequestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemoRequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
