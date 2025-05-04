import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnedToolsComponent } from './returned-tools.component';

describe('ReturnedToolsComponent', () => {
  let component: ReturnedToolsComponent;
  let fixture: ComponentFixture<ReturnedToolsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReturnedToolsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReturnedToolsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
