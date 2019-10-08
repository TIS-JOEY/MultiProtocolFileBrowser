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
export class AppComponent implements OnInit {
  public fileNodes: Observable<FileNode []>;
  private responseBody: ResponseBody;
  private querySubject: BehaviorSubject<FileNode[]>;

  constructor(public nasService: NasService) {}

  currentNode: FileNode ;
  currentPath: string;
  canNavigateUp = false;

  ngOnInit() {
    this.currentPath = '/Users/joey/';
    this.updateFileNodeQuery();
  }

  addFolder(folder: { name: string }) {
    this.updateFileNodeQuery();
  }

  removeFileNode(fileNode: FileNode) {
    this.nasService.delete(fileNode.id);
    this.updateFileNodeQuery();
  }

  navigateToFolder(fileNode: FileNode) {
    this.currentNode = fileNode;
    this.currentPath = fileNode.path;
    this.updateFileNodeQuery();
    this.canNavigateUp = true;
  }

  navigateUp() {
    this.currentPath = this.currentNode.parentPath;
    this.updateFileNodeQuery();
  }

  moveFileNode(event: { fileNode: FileNode; moveTo: FileNode }) {
    /*
    this.nasService.update(event.fileNode.id, { parent: event.moveTo.id });
    this.updateFileNodeQuery();
    */
  }

  renameFileNode(fileNode: FileNode) {
    this.nasService.update(fileNode.id, { name: fileNode.name });
    this.updateFileNodeQuery();
  }

  updateFileNodeQuery() {
    this.nasService.listChildFileNodes(this.currentPath).subscribe(
      data => {
        const result: FileNode[] = [];
        console.log(data.result[0]);
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

  pushToPath(path: string, folderName: string) {
    let p = path ? path : '';
    p += `${folderName}/`;
    return p;
  }

  popFromPath(path: string) {
    let p = path ? path : '';
    let split = p.split('/');
    split.splice(split.length - 2, 1);
    p = split.join('/');
    return p;
  }
}

