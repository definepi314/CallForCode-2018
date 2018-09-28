import { MapViewService } from './../../service/map-view.service';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

declare var google: any;

@Component({
  selector: 'app-site',
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.css']
})
export class SiteComponent implements OnInit {
  @ViewChild('map') mapElement: ElementRef;
  map: any;
  siteInfo: any;
  constructor(public mapService: MapViewService) { }

  ngOnInit() {
    this.loadMap();
  }
  loadMap() {
    this.mapService.getSiteView().subscribe(res => {
     // console.log(res);
      this.siteInfo = res;
      //console.log(this.siteInfo.response);
      for (let i = 0; i < this.siteInfo.response.length; i++) {
        //console.log(this.siteInfo.response[i].latitude)
        let myLatLng = { lat: parseFloat(this.siteInfo.response[i].latitude), lng: parseFloat(this.siteInfo.response[i].longitude) };
        let mapOptions = {
          center: myLatLng,
          zoom: 7,
          mapTypeId: google.maps.MapTypeId.TERRAIN
        }
        this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
        let marker = new google.maps.Marker({
          position: myLatLng,
          map: this.map,
          animation: google.maps.Animation.DROP,
          icon: "assets/Images/volcanoImg.png",
          title: this.siteInfo.response[i].volcanoName,
        });

      }
    })
    // let myLatLng = { lat: -25.363, lng: 131.044 };
    // let mapOptions = {
    //   center: myLatLng,
    //   zoom: 7,
    //   mapTypeId: google.maps.MapTypeId.TERRAIN
    // }
    // this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    // let marker = new google.maps.Marker({
    //   position: myLatLng,
    //   map: this.map,
    //   animation: google.maps.Animation.DROP,
    //   title: 'Hello World!',
    // });
  }

}