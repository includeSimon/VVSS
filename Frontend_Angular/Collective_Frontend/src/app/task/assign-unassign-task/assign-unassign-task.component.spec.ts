import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignUnassignTaskComponent } from './assign-unassign-task.component';

describe('AssignUnassignTaskComponent', () => {
  let component: AssignUnassignTaskComponent;
  let fixture: ComponentFixture<AssignUnassignTaskComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssignUnassignTaskComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignUnassignTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
