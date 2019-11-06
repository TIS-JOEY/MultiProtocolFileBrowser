import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-summary-table',
  templateUrl: './summary-table.component.html',
  styleUrls: ['./summary-table.component.css']
})
export class SummaryTableComponent implements OnInit {

  @Input() columnDefs;
  @Input() rowData;

  constructor() { }

  ngOnInit() {
    console.log(this.columnDefs);

  }

}
