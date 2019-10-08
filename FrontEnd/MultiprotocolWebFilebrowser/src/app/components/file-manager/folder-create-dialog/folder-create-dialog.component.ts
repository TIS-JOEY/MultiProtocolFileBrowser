import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-folder-create-dialog',
  templateUrl: './folder-create-dialog.component.html',
  styleUrls: ['./folder-create-dialog.component.css']
})
export class FolderCreateDialogComponent {

  folderName: string;
  constructor(public dialogRef: MatDialogRef<FolderCreateDialogComponent>) {}

}
