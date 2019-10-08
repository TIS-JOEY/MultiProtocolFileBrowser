import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileNodeDeleteDialogComponent } from './file-node-delete-dialog.component';

describe('FileNodeDeleteDialogComponent', () => {
  let component: FileNodeDeleteDialogComponent;
  let fixture: ComponentFixture<FileNodeDeleteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileNodeDeleteDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileNodeDeleteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
