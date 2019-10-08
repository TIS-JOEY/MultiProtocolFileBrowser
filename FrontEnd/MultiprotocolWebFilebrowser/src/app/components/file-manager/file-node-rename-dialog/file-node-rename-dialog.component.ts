import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-file-node-rename-dialog',
  templateUrl: './file-node-rename-dialog.component.html',
  styleUrls: ['./file-node-rename-dialog.component.css']
})
export class FileNodeRenameDialogComponent {

  newName: string;

  constructor(public dialogRef: MatDialogRef<FileNodeRenameDialogComponent>) {}

}
