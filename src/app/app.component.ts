import { Component, OnInit } from '@angular/core';
import { ApcmService } from './services/apcm.service';
import { BehaviorSubject, Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  prototypeColumns = [];

  columnDefs = [];
  rowData = [];

  observableColumnDefs: Observable<any[]>;
  observableRowData: Observable<any[]>;

  private columnDefsSubject: BehaviorSubject<any[]>;
  private rowDataSubject: BehaviorSubject<any[]>;

  constructor(private apcmService: ApcmService) { }

  ngOnInit() {
    this.queryApcmSummaryReportData();
  }

  queryApcmSummaryReportData() {
    this.apcmService.get().subscribe((data) => {
      const columnNames = Object.keys(data.result[0]);
      this.rowData = data.result;

      columnNames.forEach(columnName => {
        if (columnName.indexOf('_') >= 0) {
          this.buildNestedColumn2PrototypeColumns(columnName);
        } else {
          this.buildCommonColumn2PrototypeColumns(columnName);
        }
      });

      this.columnDefs = this.convertPrototypeColumns2AggridColumnDef(this.prototypeColumns);

      if (!this.rowDataSubject) {
        this.rowDataSubject = new BehaviorSubject(this.rowData);
      } else {
        this.rowDataSubject.next(this.rowData);
      }

      if (!this.columnDefsSubject) {
        this.columnDefsSubject = new BehaviorSubject(this.columnDefs);
      } else {
        this.columnDefsSubject.next(this.columnDefs);
      }

      this.observableColumnDefs = this.columnDefsSubject.asObservable();
      this.observableRowData = this.rowDataSubject.asObservable();
    });
  }

  buildNestedColumn2PrototypeColumns(nestedColumnNames: string) {
    const nestedColumnNamesSplit = nestedColumnNames.split('_');
    let currentNestedColumns = this.prototypeColumns;

    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < nestedColumnNamesSplit.length; i++) {

      let columnName = nestedColumnNamesSplit[i];
      if (i === nestedColumnNamesSplit.length - 1) {
        columnName = nestedColumnNames;
      }

      if (Object.keys(currentNestedColumns).indexOf(columnName) >= 0) {
        currentNestedColumns = currentNestedColumns[columnName];
      } else {
        currentNestedColumns[columnName] = {};
        currentNestedColumns = currentNestedColumns[columnName];
      }
    }
    /*
    nestedColumnNamesSplit.forEach(columnName => {
      if (Object.keys(currentNestedColumns).indexOf(columnName) >= 0) {
        currentNestedColumns = currentNestedColumns[columnName];
      } else {
        currentNestedColumns[columnName] = {};
        currentNestedColumns = currentNestedColumns[columnName];
      }
    });
    */
  }

  buildCommonColumn2PrototypeColumns(columnName: string) {
    if (Object.keys(this.prototypeColumns).indexOf(columnName) < 0) {
      this.prototypeColumns[columnName] = {};
    }
  }

  buildInsertAggridColumnDef(headerName: string, field: string, children: any[]) {

    if ( Object.keys(children).length !== 0 ) {
      return { headerName, children };
    } else {
      return { headerName, field };
    }
  }

  convertPrototypeColumns2AggridColumnDef(prototypeColumns: object[]) {

    const columnNames = Object.keys(prototypeColumns);
    const aggridColumnDefs = [];
    columnNames.forEach((columnName) => {
      let children = null;
      if (prototypeColumns[columnName] !== {}) {
        children = this.convertPrototypeColumns2AggridColumnDef(prototypeColumns[columnName]);
      }

      const insertAggridColumnDef = this.buildInsertAggridColumnDef(columnName, columnName, children);
      aggridColumnDefs.push(insertAggridColumnDef);
    });

    return aggridColumnDefs;
  }

}
