import { MapViewService } from './../../service/map-view.service';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
declare var google: any;

@Component({
  selector: 'app-population',
  templateUrl: './population.component.html',
  styleUrls: ['./population.component.css']
})
export class PopulationComponent implements OnInit {
  popullationInfo: any;
  @ViewChild('map') mapElement: ElementRef;
  map: any;
  latitude: any;
  longitude: any;
  city: any;
  constructor(public mapService: MapViewService) { }

  ngOnInit() {
    console.log("milind");
    this.loadMap();
  }
  loadMap() {
    this.mapService.getPopullationView().subscribe(res => {
      //console.log(res);
      this.popullationInfo = res;
      //console.log(this.popullationInfo.response);
      let myLatLng = { lat: parseFloat("23.024003531654746"), lng: parseFloat("72.567483441803") };
      let mapOptions = {
        center: myLatLng,
        zoom: 7,
        mapTypeId: google.maps.MapTypeId.TERRAIN
      }
      this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
      for (let i = 0; i < this.popullationInfo.response.length; i++) {
        //console.log(this.popullationInfo.response[i]);
        for (let j = 0; j < this.popullationInfo.response[i].addressDTOs.length; j++) {
          //console.log(this.popullationInfo.response[i].addressDTOs[j]);
          this.latitude = this.popullationInfo.response[i].addressDTOs[j].latitude;
          this.longitude = this.popullationInfo.response[i].addressDTOs[j].longitude;
          this.city = this.popullationInfo.response[i].addressDTOs[j].city;
          let myLatLng = { lat: parseFloat(this.latitude), lng: parseFloat(this.longitude) };
          let marker = new google.maps.Marker({
            position: new google.maps.LatLng(myLatLng),
            map: this.map,
            animation: google.maps.Animation.DROP,
            icon: "assets/Images/pupullationImg.png",
            title: this.city,
          });
          console.log(myLatLng);
        }

      }
    })
  }
}
