import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { FileNode } from '../../model/file-node';
import { MatMenuTrigger } from '@angular/material/menu';
import { MatDialog } from '@angular/material/dialog';
import { FolderCreateDialogComponent } from './folder-create-dialog/folder-create-dialog.component';
import { FileNodeRenameDialogComponent } from './file-node-rename-dialog/file-node-rename-dialog.component';

@Component({
  selector: 'app-file-manager',
  templateUrl: './file-manager.component.html',
  styleUrls: ['./file-manager.component.css']
})
export class FileManagerComponent {

  constructor(public dialog: MatDialog) {}

  @Input() fileNodes: FileNode[];
  @Input() canNavigateUp: string;
  @Input() currentPath: string;

  @Output() folderAdded = new EventEmitter<string>();
  @Output() fileNodeDeleted = new EventEmitter<string>();
  @Output() fileNodeRenamed = new EventEmitter<{ sourcePath: string, targetPath: string }>();
  @Output() fileNodeMoved = new EventEmitter<{ fileNode: FileNode; moveTo: FileNode }>();
  @Output() navigatedDown = new EventEmitter<string>();
  @Output() navigatedUp = new EventEmitter();

  deleteFileNode(fileNode: FileNode) {
    this.fileNodeDeleted.emit(fileNode.path);
  }

  navigate(fileNode: FileNode) {
    if (fileNode.isDir) {
      this.navigatedDown.emit(fileNode.path);
    }
  }

  navigateUp() {
    this.navigatedUp.emit();
  }

  moveFileNode(fileNode: FileNode, moveTo: FileNode) {
    this.fileNodeMoved.emit({ fileNode, moveTo });
  }

  openCreateFolderDialog() {
    const dialogRef = this.dialog.open(FolderCreateDialogComponent);
    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this.folderAdded.emit(res);
      }
    });
  }

  openRenameFileNodeDialog(fileNode: FileNode) {
    const dialogRef = this.dialog.open(FileNodeRenameDialogComponent);
    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        this.fileNodeRenamed.emit({ sourcePath: fileNode.path, targetPath: fileNode.parentPath + '/' + res });
      }
    });
  }

  openMenu(event: MouseEvent, viewChild: MatMenuTrigger) {
    event.preventDefault();
    viewChild.openMenu();
  }

}
