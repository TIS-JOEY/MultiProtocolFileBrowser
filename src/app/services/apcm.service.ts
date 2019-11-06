import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { ResponseBodyInterface } from '../models/response-body-interface';

@Injectable({
  providedIn: 'root'
})
export class ApcmService {

  data: ResponseBodyInterface = {
    code: '200',
    result: [
      {Process: 'N7',
       FailureMode: 'NW2',
       F12_BKM42_W940: '123',
       F12_BKM42_W941: '234',
       F12_BKM42_W942: '843',
       F12_BKM46_W940: '893',
       F12_BKM46_W941: '821',
       F12_BKM46_W942: '930'},
       {Process: 'N8',
       FailureMode: 'Na2',
       F12_BKM42_W940: '123',
       F12_BKM42_W941: '234',
       F12_BKM42_W942: '843',
       F12_BKM46_W940: '893',
       F12_BKM46_W941: '821',
       F12_BKM46_W942: '930'}
    ],
    message: ''
  };

  constructor() { }

  get(): Observable<ResponseBodyInterface> {
    return of(this.data);
  }

}
