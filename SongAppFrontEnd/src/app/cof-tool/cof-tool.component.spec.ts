import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CofToolComponent } from './cof-tool.component';

describe('CofToolComponent', () => {
  let component: CofToolComponent;
  let fixture: ComponentFixture<CofToolComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CofToolComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CofToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
