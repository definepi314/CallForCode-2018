import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { MapViewService } from './../../service/map-view.service';

declare var google: any;

@Component({
  selector: 'app-ryb',
  templateUrl: './ryb.component.html',
  styleUrls: ['./ryb.component.css']
})
export class RybComponent implements OnInit {
  siteInfo: any;
  @ViewChild('map') mapElement: ElementRef;
  map: any;
  outerCoords = [];
  outerCoordsTemp = [];
  innerYCoords = [];
  innerYCoordsTemp = [];
  innerRCoords = [];
  innerRCoordsTemp = []
  constructor(public mapService: MapViewService) { }


  ngOnInit() {
    this.loadmap();
  }
  loadmap() {
    this.mapService.getSiteView().subscribe(res => {
      console.log(res);
      this.siteInfo = res;
      for (let i = 0; i < this.siteInfo.response.length; i++) {
        console.log(this.siteInfo.response[i]);
        console.log("my",this.siteInfo.response[i].blueZone.split(";"))
        const latlngarr = this.siteInfo.response[i].blueZone.split(";");
        const latlngarr1 = this.siteInfo.response[i].yellowZone.split(";");
        const latlngarr2 = this.siteInfo.response[i].redZone.split(";");
        
        for(var p=0;p<latlngarr.length;p++){
          this.outerCoordsTemp.push(latlngarr[p]);
        }
        for(var r=0;r<latlngarr1.length;r++){
          this.innerYCoordsTemp.push(latlngarr1[r]);
        }
        for(var q=0;q<latlngarr2.length;q++){
          this.innerRCoordsTemp.push(latlngarr2[q]);
        }

        const outerCoordsTemp = this.outerCoordsTemp;
        const innerYCoordsTemp = this.innerYCoordsTemp;
        const innerRCoordsTemp = this.innerRCoordsTemp;
        //console.log("akshat",outerCoordsTemp);
        for (var j = 0; j < this.outerCoordsTemp.length; j++) {
          let lat = parseFloat(this.outerCoordsTemp[j].split(",")[0]);
          let lng = parseFloat(this.outerCoordsTemp[j].split(",")[1]);
          console.log("check", lat, lng)
          this.outerCoords.push(new google.maps.LatLng(lat,lng));
        }
        for (var k = 0; k < this.innerYCoordsTemp.length; k++) {
          let lat = parseFloat(this.innerYCoordsTemp[k].split(",")[0]);
          let lng = parseFloat(this.innerYCoordsTemp[k].split(",")[1]);
          this.innerYCoords.push(new google.maps.LatLng(lat,lng));
        }
        for (var l = 0; l < this.innerRCoordsTemp.length; l++) {
          let lat = parseFloat(this.innerRCoordsTemp[l].split(",")[0]);
          let lng = parseFloat(this.innerRCoordsTemp[l].split(",")[1]);
          this.innerRCoords.push(new google.maps.LatLng(lat,lng));
        }
        console.log(this.innerRCoords);
        //const innercords = [nagpur, indore, indore, akola];
        let mapOptions = {
          center: new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)),
          zoom: 14,
          mapTypeId: google.maps.MapTypeId.TERRAIN
        }
        this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

        let marker = new google.maps.Marker({
          position: new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)),
          map: this.map,
          animation: google.maps.Animation.DROP,
          icon: "assets/Images/volcanoImg.png",
          title: this.siteInfo.response[i].volcanoName,
        });

        const firstPoly = new google.maps.Polygon({
          paths: [this.outerCoords, this.innerYCoords],
          strokeColor: 'blue',
          strokeOpacity: 0.8,
          strokeWeight: 2,
          map: this.map,
          fillColor: 'blue',
          fillOpacity: 0.35
        });

        const secondPoly = new google.maps.Polygon({
          paths: [this.innerYCoords,this.innerRCoords],
          strokeColor: 'yellow',
          strokeOpacity: 0.8,
          strokeWeight: 2,
          map: this.map,
          fillColor: 'yellow',
          fillOpacity: 0.35
        });
        const thirdPoly = new google.maps.Polygon({
          paths: [this.innerRCoords],
          strokeColor: 'red',
          strokeOpacity: 0.8,
          strokeWeight: 2,
          map: this.map,
          fillColor: 'red',
          fillOpacity: 0.35
        });
        
      }
    })
    // let betula = new google.maps.LatLng(21.884589, 77.887382);
    // let nagpur = new google.maps.LatLng(21.171914, 79.088978);
    // let indore = new google.maps.LatLng(22.739744, 75.858345);
    // indore = new google.maps.LatLng(23.245412, 77.439525);
    // let akola = new google.maps.LatLng(20.701930, 77.018657);

    // let bansawara = new google.maps.LatLng(23.545572, 74.444076);
    // let ashoknagar = new google.maps.LatLng(24.576602, 77.738067);
    // let raipur = new google.maps.LatLng(21.248772, 81.637266);
    // let chandrapur = new google.maps.LatLng(19.986731, 79.292704);
    // let jalgaon = new google.maps.LatLng(21.002397, 75.566180);


    // const outerCoords = [bansawara, ashoknagar, raipur, chandrapur, jalgaon];
    // const innercords = [nagpur, indore, indore, akola];
    // ;
    // let mapOptions = {
    //   center: betula,
    //   zoom: 7,
    //   mapTypeId: google.maps.MapTypeId.TERRAIN
    // }

    // this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);

    // const firstPoly = new google.maps.Polygon({
    //   paths: [outerCoords, innercords],
    //   strokeColor: '#FFC107',
    //   strokeOpacity: 0.8,
    //   strokeWeight: 2,
    //   map: this.map,
    //   fillColor: '#FFC107',
    //   fillOpacity: 0.35
    // });

    // const secondPoly = new google.maps.Polygon({
    //   paths: innercords,
    //   strokeColor: 'red',
    //   strokeOpacity: 0.8,
    //   strokeWeight: 2,
    //   map: this.map,
    //   fillColor: 'red',
    //   fillOpacity: 0.35
    // });
  }
}
