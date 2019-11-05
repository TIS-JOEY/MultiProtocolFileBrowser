import { Injectable } from '@angular/core';
import { of, Observable } from 'rxjs';
import { ResponseBodyInterface } from '../model/response-body-interface';

@Injectable({
  providedIn: 'root'
})
export class ApcmService {

  data: ResponseBodyInterface = {
    code: '200',
    result:
      {
        columns: [
          'process',
          'failureMode',
          'FAB12_BKM4.2_W940',
          'FAB12_BKM4.2_W941',
          'FAB12_BKM4.2_W942'
        ],
        values: {
          process: 'N7',
          failureMode: 'NDU212',
          'FAB12_BKM4.2_W940': '123',
          'FAB12_BKM4.2_W941': '456',
          'FAB12_BKM4.2_W942': '789',
        }
      },
    message: ''
  };

  constructor() { }

  get(): Observable<ResponseBodyInterface> {
    return of(this.data);
  }

}
