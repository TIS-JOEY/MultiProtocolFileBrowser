import { Component, OnInit } from '@angular/core';
import { FileNode } from './model/file-node';
import { Observable } from 'rxjs';
import { NasService } from './services/nas.service';
import { ResponseBody } from './model/response-body';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  public fileNodes: Observable<FileNode []>;
  private responseBody: ResponseBody;
  private querySubject: BehaviorSubject<FileNode[]>;
  private serviceName: string;
  private applicationId: string = '123';

  constructor(public nasService: NasService) {}

  currentPath: string;
  canNavigateUp = false;

  getServiceName(serviceName: string) {
    this.serviceName = serviceName;
    this.currentPath = '/Users/joey/Documents';
    this.updateFileNodeQuery();
  }

  addFolder(targetName: string) {
    this.nasService.createDir(this.serviceName, this.applicationId, this.currentPath + '/' + targetName).subscribe(
      data => {
        console.log(data);
        this.updateFileNodeQuery();
      },
      err => {
        console.error(err);
      }
    );
  }

  deleteFileNode(targetPath: string) {
    this.nasService.deleteFileNode(this.serviceName, this.applicationId, targetPath).subscribe(
      data => {
        console.log(data);
        this.updateFileNodeQuery();
      },
      err => {
        console.error(err);
      }
    );
  }

  renameFileNode(res: any) {
    this.nasService.renameFileNode(this.serviceName, this.applicationId, res.sourcePath, res.targetPath).subscribe(
      data => {
        console.log(data);
        this.updateFileNodeQuery();
      },
      err => {
        console.error(err);
      }
    );
  }

  moveFileNode(event: { fileNode: FileNode; moveTo: FileNode }) {
    /*
    this.nasService.update(event.fileNode.id, { parent: event.moveTo.id });
    this.updateFileNodeQuery();
    */
  }

  updateFileNodeQuery() {
    this.nasService.listChildFileNodes(this.serviceName, this.applicationId, this.currentPath).subscribe(
      data => {
        const result: FileNode[] = [];
        data.result.forEach(element => {
          result.push(element);
        });
        if (!this.querySubject) {
          this.querySubject = new BehaviorSubject(result);
        } else {
          this.querySubject.next(result);
        }
        this.fileNodes = this.querySubject.asObservable();
      },
      err => { console.error(err); }
    );
  }

  navigateToFolder(targetPath: string) {
    this.currentPath = targetPath;
    this.updateFileNodeQuery();
    this.canNavigateUp = true;
  }

  navigateUp() {
    this.currentPath = this.popFromPath(this.currentPath);
    this.updateFileNodeQuery();
  }

  pushToPath(path: string, folderName: string) {
    let p = path ? path : '';
    p += `${folderName}/`;
    return p;
  }

  popFromPath(path: string) {
    let p = path ? path : '';
    let split = p.split('/');
    split.splice(split.length - 1, 1);
    p = split.join('/');
    return p;
  }
}

