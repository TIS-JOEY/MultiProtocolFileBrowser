import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileNodeRenameDialogComponent } from './file-node-rename-dialog.component';

describe('FileNodeRenameDialogComponent', () => {
  let component: FileNodeRenameDialogComponent;
  let fixture: ComponentFixture<FileNodeRenameDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileNodeRenameDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileNodeRenameDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
