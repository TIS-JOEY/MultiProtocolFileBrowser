import { Component, OnInit } from '@angular/core';
import { ApcmService } from './services/apcm.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'aggridtest';
  columnDefs = [];
  rowData = [];

  constructor(private apcmService: ApcmService) { }

  ngOnInit() {
    this.queryApcmSummaryData();
  }

  queryApcmSummaryData() {
    this.apcmService.get().subscribe(
      (data) => {
        this.apcmGridInit(data.result);
        console.log(this.columnDefs);
        console.log(this.rowData);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  apcmGridInit(data): any {

    this.rowData = [data.values];
    data.columns.forEach(columnName => {
      let columnDef: any = {};

      if (columnName.indexOf('_') >= 0 ) {
        columnDef = this.buildHierarchicalColumnDefs(columnName);
      } else {
        columnDef.headerName = columnName;
        columnDef.field = columnName;
      }

      this.columnDefs.push(columnDef);
    });
  }

  buildHierarchicalColumnDefs(columnName: string) {

    let childColumn;
    const columnNameSplit: string[] = columnName.split('_');

    // tslint:disable-next-line: prefer-for-of
    for (let i = columnNameSplit.length - 1; i >= 0; i--) {
      const currentColumn: any = {};
      currentColumn.headerName = columnNameSplit[i];
      currentColumn.field = columnNameSplit[i];

      if (childColumn === undefined) {
        currentColumn.field = columnName;
      } else {
        currentColumn.children = [childColumn];
      }
      childColumn = currentColumn;
    }

    return childColumn;
  }

}
