import { Injectable } from '@angular/core';

import { v4 } from 'uuid';
import { FileNode } from '../model/file-node';
import { BehaviorSubject } from 'rxjs';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { RequestBody } from '../model/request-body';
import { ResponseBody } from '../model/response-body';
import { catchError, map } from "rxjs/operators";

export interface IFileService {
  add(fileElement: FileNode);
  delete(id: string);
  update(id: string, update: Partial<FileNode>);
  get(id: string): FileNode;
}

const url = 'http://filebrowser.tsmc.com:8080/localDrive/123/fileNodes';

@Injectable({
  providedIn: 'root'
})
export class NasService implements IFileService {
  private requestBody: RequestBody;
  private map = new Map<string, FileNode>();
  private querySubject: BehaviorSubject<FileNode[]>;
  constructor(private http: HttpClient) {}

  listChildFileNodes(targetPath: string): Observable<ResponseBody> {

    return this.http.get(url + '/childs', {
      headers: new HttpHeaders()
          .set('Authorization', 'c31z')
          .set('Content-Type', 'application/json'),
      params: {
        targetPath
      }
    }).pipe(
      map(this.extractData),
      catchError(this.handleError)
    );
  }

  /**
   * Function to handle error when the server return an error
   *
   * @param error
   */
  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code. The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` + `body was: ${error.error}`
      );
    }
    // return an observable with a user-facing error message
    return throwError(error);
  }

  /**
   * Function to extract the data when the server return some
   *
   * @param res
   */
  private extractData(res: Response) {
    let body = res;
    return body || {};
  }


  add(fileNode: FileNode) {
    fileNode.id = v4();
    this.map.set(fileNode.id, this.clone(fileNode));
    return fileNode;
  }

  delete(id: string) {
    this.map.delete(id);
  }

  update(id: string, update: Partial<FileNode>) {
    let fileNode = this.map.get(id);
    fileNode = Object.assign(fileNode, update);
    this.map.set(fileNode.id, fileNode);
  }

  get(id: string): FileNode {
    return this.map.get(id);
  }

  clone(fileNode: FileNode) {
    return JSON.parse(JSON.stringify(fileNode));
  }

}
