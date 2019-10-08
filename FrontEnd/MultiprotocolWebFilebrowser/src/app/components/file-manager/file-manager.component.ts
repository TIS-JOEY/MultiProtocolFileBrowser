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

  @Output() folderAdded = new EventEmitter<{ name: string }>();
  @Output() fileNodeRemoved = new EventEmitter<FileNode>();
  @Output() fileNodeRenamed = new EventEmitter<FileNode>();
  @Output() fileNodeMoved = new EventEmitter<{ fileNode: FileNode; moveTo: FileNode }>();
  @Output() navigatedDown = new EventEmitter<FileNode>();
  @Output() navigatedUp = new EventEmitter();

  deleteFileNode(fileNode: FileNode) {
    this.fileNodeRemoved.emit(fileNode);
  }

  navigate(fileNode: FileNode) {
    if (fileNode.isDir) {
      this.navigatedDown.emit(fileNode);
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
        this.folderAdded.emit({ name: res });
      }
    });
  }

  openRenameFileNodeDialog(fileNode: FileNode) {
    const dialogRef = this.dialog.open(FileNodeRenameDialogComponent);
    dialogRef.afterClosed().subscribe(res => {
      if (res) {
        fileNode.name = res;
        this.fileNodeRenamed.emit(fileNode);
      }
    });
  }

  openMenu(event: MouseEvent, viewChild: MatMenuTrigger) {
    event.preventDefault();
    viewChild.openMenu();
  }

}
