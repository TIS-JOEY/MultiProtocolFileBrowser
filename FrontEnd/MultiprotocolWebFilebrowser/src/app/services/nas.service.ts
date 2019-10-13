import { Injectable } from '@angular/core';

import { v4 } from 'uuid';
import { FileNode } from '../model/file-node';
import { BehaviorSubject } from 'rxjs';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse, HttpRequest } from '@angular/common/http';
import { RequestBody } from '../model/request-body';
import { ResponseBody } from '../model/response-body';
import { catchError, map } from "rxjs/operators";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'Basic my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class NasService {
  private requestBody: RequestBody;
  private responseBody: ResponseBody;
  private querySubject: BehaviorSubject<FileNode[]>;
  private url: string;
  constructor(private http: HttpClient) {}

  buildRequestedUrl(serviceName: string, applicationId: string, target?: string): string {
    return `http://filebrowser.tsmc.com:8080/${serviceName}/${applicationId}/fileNodes/${target}`;
  }

  listChildFileNodes(serviceName: string, applicationId: string, targetPath: string): Observable<ResponseBody> {
    return this.http.get(this.buildRequestedUrl(serviceName, applicationId, 'childs'), {
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

  renameFileNode(serviceName: string, applicationId: string, sourcePath: string, targetPath: string): Observable<ResponseBody> {
    this.requestBody = {
      sourcePath, targetPath
    };
    return this.http.put(this.buildRequestedUrl(serviceName, applicationId, 'name'), this.requestBody, httpOptions).pipe(
      map(this.extractData),
      catchError(this.handleError)
    );
  }

  deleteFileNode(serviceName, applicationId, targetPath: string): Observable<ResponseBody> {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: {
        targetPaths: [targetPath]
      },
    };
    return this.http.delete(this.buildRequestedUrl(serviceName, applicationId, ''), options ).pipe(
      map(this.extractData),
      catchError(this.handleError)
    );
  }

  createDir(serviceName: string, applicationId: string, targetPath: string): Observable<ResponseBody> {
    this.requestBody = {
      targetPath
    };
    const config = { headers: new HttpHeaders().set('Content-Type', 'application/json') };
    return this.http.post(this.buildRequestedUrl(serviceName, applicationId, 'dir'), this.requestBody, config).pipe(
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

  /*
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

  */

}
