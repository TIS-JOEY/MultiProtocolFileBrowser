import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatIconModule } from '@angular/material/icon';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { FileManagerComponent } from './file-manager.component';
import { FolderCreateDialogComponent } from './folder-create-dialog/folder-create-dialog.component';
import { FileNodeRenameDialogComponent } from './file-node-rename-dialog/file-node-rename-dialog.component';

@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    FlexLayoutModule,
    MatIconModule,
    MatGridListModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
    MatButtonModule
  ],
  declarations: [FileManagerComponent,
                 FolderCreateDialogComponent,
                 FileNodeRenameDialogComponent],
  exports: [FileManagerComponent],
  entryComponents: [FolderCreateDialogComponent,
                    FileNodeRenameDialogComponent]
})
export class FileManagerModule {}
