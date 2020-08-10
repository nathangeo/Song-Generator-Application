import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PolyrythmComponent } from './polyrythm.component';

describe('PolyrythmComponent', () => {
  let component: PolyrythmComponent;
  let fixture: ComponentFixture<PolyrythmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PolyrythmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PolyrythmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
