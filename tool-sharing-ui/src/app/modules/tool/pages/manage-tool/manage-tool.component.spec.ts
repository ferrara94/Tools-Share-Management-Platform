import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageToolComponent } from './manage-tool.component';

describe('ManageToolComponent', () => {
  let component: ManageToolComponent;
  let fixture: ComponentFixture<ManageToolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageToolComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageToolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
