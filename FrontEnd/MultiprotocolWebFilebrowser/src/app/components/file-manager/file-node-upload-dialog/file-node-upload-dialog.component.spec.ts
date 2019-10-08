import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FileNodeUploadDialogComponent } from './file-node-upload-dialog.component';

describe('FileNodeUploadDialogComponent', () => {
  let component: FileNodeUploadDialogComponent;
  let fixture: ComponentFixture<FileNodeUploadDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FileNodeUploadDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FileNodeUploadDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
