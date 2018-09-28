import { DisasterTrainingService } from './../../service/disaster-training.service';
import { VolcanoZoneService } from './../../service/volcano-zone.service';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Highcharts } from 'angular-highcharts';
import drilldown from 'highcharts/modules/drilldown.src.js';
drilldown(Highcharts);

export class disaster {
  id: number;
  name: string;
}

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent implements OnInit {
  checklistNames: any;
  checklistCount: any;
  checkListData = [];
  subCheckListDatas: any;
  subCheckListData = [];
  subChecklistArray = [];
  checkListDataBody = [];
  checkLists: disaster[];
  disasterId:number;
  disasterName:string
  @ViewChild('container') containerElement: ElementRef;
  constructor(public volcanoService: VolcanoZoneService, public disasterTrainService: DisasterTrainingService) { }

  ngOnInit() {
    this.disasterTrainService.lookupDisaster().subscribe((res: disaster[]) => {
      //console.log(res);
      this.checkLists = res;
    })
  }
  changeList(event) {
    //console.log(event.value);
    this.disasterName=event.value.name;
    this.disasterId = event.value.id;
    this.loadServiceData();
  }
  loadServiceData() {
    let analyticBody = {
      "volcanoId": this.disasterId
    }
    this.volcanoService.getAnalytics(analyticBody).subscribe(res => {
      //console.log(res['response']);
      for (let i = 0; i < res['response'].length; i++) {
        //console.log(res['response'][i]);
        this.subCheckListDatas = res['response'][i].subAnalyticsDTO;
        this.checklistNames = res['response'][i].checklistName;
        this.checklistCount = res['response'][i].count;
        let checklistDataBody = {
          "name": this.checklistNames,
          "y": this.checklistCount,
          drilldown: this.checklistNames
        }
        this.checkListData.push(checklistDataBody);
        this.subChecklistArray = [];
        for (let j = 0; j < this.subCheckListDatas.length; j++) {

          this.subCheckListData = [];
         // console.log(this.subCheckListDatas[j]);
          this.subCheckListData.push(this.subCheckListDatas[j].subchecklistName, this.subCheckListDatas[j].subCount);
         // console.log("%%%%%%%%%%%%%%%", this.subCheckListData);
          this.subChecklistArray.push(this.subCheckListData)
        }
        let subchecklistBody = {
          "name": this.checklistNames,
          "id": this.checklistNames,
          "data": this.subChecklistArray,
        }
        this.checkListDataBody.push(subchecklistBody);

      }
     // console.log("########", this.checkListData);
    //  console.log("@@@@", this.checkListDataBody);
      this.loadBarChart();
    })
  }
  loadBarChart() {
    let that = this
    Highcharts.chart('container', {
      chart: {
        type: 'column'
      },
      title: {
        text: this.disasterName+' Analytics'
      },
      subtitle: {
        text: 'Click the columns to view Details.'
        // Source: <a href="http://statcounter.com" target="_blank">statcounter.com</a>
      },
      xAxis: {
        type: 'category'
      },
      yAxis: {
        title: {
          text: 'Total percent People share'
        }

      },
      legend: {
        enabled: false
      },
      plotOptions: {
        series: {
          //  borderWidth: 0,
          dataLabels: {
            enabled: true,
            format: '{point.y:.1f}%'
          }
        }
      },
      colors: ['#b9dcc7'],
      tooltip: {
        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
      },
      credits: {
        enabled: false
      },
      "series": [
        {
          "name": "Analytics",
          // "colorByPoint": true,
          "data": this.checkListData
          // [
          //   {
          //     "name": "Foods",
          //     "y": 62.74,
          //     drilldown: "Foods"
          //   },
          //   {
          //     "name": "Clothes",
          //     "y": 10.57,
          //     drilldown: "Clothes"
          //   },
          //   {
          //     "name": "Medicines",
          //     "y": 7.23,
          //     drilldown: "Medicines"
          //   }
          // ]
        }
      ],
      drilldown: {
        "series": this.checkListDataBody
        // "series": [
        //   {
        //     "name": "Foods",
        //     "id": "Foods",
        //     "data": [
        //       [
        //         "Jawar",
        //         0.1
        //       ],
        //       [
        //         "Vegetables",
        //         1.3
        //       ],
        //       [
        //         "Rice",
        //         53.02
        //       ],
        //       [
        //         "Oats",
        //         1.4
        //       ],
        //       [
        //         "Water",
        //         0.88
        //       ],
        //       [
        //         "Chiken",
        //         0.56
        //       ],
        //       [
        //         "Oils",
        //         0.45
        //       ]
        //     ]
        //   },
        //   {
        //     "name": "Clothes",
        //     "id": "Clothes",
        //     "data": [
        //       [
        //         "Jeans",
        //         1.02
        //       ],
        //       [
        //         "Shirts",
        //         7.36
        //       ],
        //       [
        //         "T-shirts",
        //         0.35
        //       ],
        //       [
        //         "Blankets",
        //         0.11
        //       ],
        //     ]
        //   },
        //   {
        //     "name": "Medicines",
        //     "id": "Medicines",
        //     "data": [
        //       [
        //         "Crocine",
        //         6.2
        //       ],
        //       [
        //         "Saradone",
        //         0.29
        //       ],
        //       [
        //         "Vicks",
        //         0.27
        //       ],
        //       [
        //         "Body-Pain",
        //         0.47
        //       ]
        //     ]
        //   }
        // ]
      }
    });
  }
}
