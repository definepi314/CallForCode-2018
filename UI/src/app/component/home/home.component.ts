import { Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { MapViewService } from './../../service/map-view.service';

declare var google: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  siteInfo: any;
  @ViewChild('map') mapElement: ElementRef;
  @ViewChild('searchBoxInput') searchBoxInput: ElementRef;
  popullationInfo: any;
  map: any;
  outerCoords = [];
  outerCoordsTemp = [];
  innerYCoords = [];
  innerYCoordsTemp = [];
  innerRCoords = [];
  innerRCoordsTemp = [];
  latitude: any;
  longitude: any;
  city: any;
  outerCoords2 = [];
  innerCoords2 = [];
  volcanoFlag = false;
  popullationFlag = false;
  markerVolcano: any;
  searchBox: any;
  constructor(public mapService: MapViewService) { }


  ngOnInit() {
   // const that = this;
    setTimeout(()=>{
      let mapOptions = {
        center: new google.maps.LatLng(parseFloat("-35.11793454997535"), parseFloat("139.1667958984375")),
        zoom: 8,
        mapTypeId: google.maps.MapTypeId.SATELLITE
      }
      this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
     
      this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);
  
      this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
      this.linkBox();
      this.loadmap("all");
    },2000)
    
  }

  loadmap(str) {
    if (str == "all") {
      let mapOptions = {
      center: new google.maps.LatLng("-35.11793454997535", "139.1667958984375"),
      zoom: 8,
      mapTypeId: google.maps.MapTypeId.SATELLITE
    }
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
   
    this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);
    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
    this.linkBox();
      this.popullationView();
      this.volcanoView();
      
    } else if (str == "volcano") {
      let mapOptions = {
      center: new google.maps.LatLng("-35.11793454997535", "139.1667958984375"),
      zoom: 8,
      mapTypeId: google.maps.MapTypeId.SATELLITE
    }
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);
    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
    this.linkBox();

      this.volcanoView();
    } else if (str == 'population') {
      let mapOptions = {
      center: new google.maps.LatLng("-35.11793454997535", "139.1667958984375"),
      zoom: 8,
      mapTypeId: google.maps.MapTypeId.SATELLITE
    }
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);

    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
    this.linkBox();
      this.popullationView();
    }

  }

  linkBox() {
    this.map.addListener('bounds_changed', (e) => {
      this.searchBox.setBounds(this.map.getBounds());
    });

    this.searchBox.addListener('places_changed', (e) => {
      const places = this.searchBox.getPlaces();
      if (places.length === 0) {
        return;
      }

      const bounds = new google.maps.LatLngBounds();
      places.forEach((place) => {
        if (!place.geometry) {
          console.log('Returned place contains no geometry');
          return;
        }
        if (place.geometry.viewport) {
          bounds.union(place.geometry.viewport);
        } else {
          bounds.extend(place.geometry.location);
        }
      });
      this.map.fitBounds(bounds);
    });
  }

  volcanoView() {

    this.mapService.getSiteView().subscribe(res => {
      console.log(res);
      this.siteInfo = res;

      for (let i = 0; i < this.siteInfo.response.length; i++) {

        if (this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone != null) {
          this.outerCoordsTemp = [];
          this.innerYCoordsTemp = [];
          this.innerRCoordsTemp = [];
          this.outerCoords = [];
          this.innerYCoords = [];
          this.innerRCoords = [];

          const latlngarr = this.siteInfo.response[i].blueZone.split(";");
          const latlngarr1 = this.siteInfo.response[i].yellowZone.split(";");
          const latlngarr2 = this.siteInfo.response[i].redZone.split(";");

          for (var p = 0; p < latlngarr.length; p++) {
            this.outerCoordsTemp.push(latlngarr[p]);
          }
          for (var r = 0; r < latlngarr1.length; r++) {
            this.innerYCoordsTemp.push(latlngarr1[r]);
          }
          for (var q = 0; q < latlngarr2.length; q++) {
            this.innerRCoordsTemp.push(latlngarr2[q]);
          }

          const outerCoordsTemp = this.outerCoordsTemp;
          const innerYCoordsTemp = this.innerYCoordsTemp;
          const innerRCoordsTemp = this.innerRCoordsTemp;
         
          for (var j = 0; j < this.outerCoordsTemp.length; j++) {
            let lat = parseFloat(this.outerCoordsTemp[j].split(",")[0]);
            let lng = parseFloat(this.outerCoordsTemp[j].split(",")[1]);
           
            this.outerCoords.push(new google.maps.LatLng(lat, lng));
          }
          for (var k = 0; k < this.innerYCoordsTemp.length; k++) {
            let lat = parseFloat(this.innerYCoordsTemp[k].split(",")[0]);
            let lng = parseFloat(this.innerYCoordsTemp[k].split(",")[1]);
            this.innerYCoords.push(new google.maps.LatLng(lat, lng));
          }
          for (var l = 0; l < this.innerRCoordsTemp.length; l++) {
            let lat = parseFloat(this.innerRCoordsTemp[l].split(",")[0]);
            let lng = parseFloat(this.innerRCoordsTemp[l].split(",")[1]);
            this.innerRCoords.push(new google.maps.LatLng(lat, lng));
          }


          new google.maps.Polygon({
            paths: [this.outerCoords, this.innerYCoords],
            strokeColor: 'blue',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            map: this.map,
            fillColor: 'blue',
            fillOpacity: 0.35
          });

          new google.maps.Polygon({
            paths: [this.innerYCoords, this.innerRCoords],
            strokeColor: 'yellow',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            map: this.map,
            fillColor: 'yellow',
            fillOpacity: 0.35
          });
          new google.maps.Polygon({
            paths: [this.innerRCoords],
            strokeColor: 'red',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            map: this.map,
            fillColor: 'red',
            fillOpacity: 0.35
          });
        } else if ((this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone == null && this.siteInfo.response[i].redZone == null) || (this.siteInfo.response[i].blueZone == null && this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone == null) || (this.siteInfo.response[i].blueZone == null && this.siteInfo.response[i].yellowZone == null && this.siteInfo.response[i].redZone != null)) {
          this.outerCoords = [];
          this.outerCoordsTemp = [];
          let latlngarr = "";
          if (this.siteInfo.response[i].blueZone != null) {
            latlngarr = this.siteInfo.response[i].blueZone.split(";");
          } else if (this.siteInfo.response[i].yellowZone != null) {
            latlngarr = this.siteInfo.response[i].yellowZone.split(";");
          } else if (this.siteInfo.response[i].redZone != null) {
            latlngarr = this.siteInfo.response[i].redZone.split(";");
          }
          for (var p = 0; p < latlngarr.length; p++) {
            this.outerCoordsTemp.push(latlngarr[p]);
          }


          const outerCoordsTemp = this.outerCoordsTemp;

          for (var j = 0; j < this.outerCoordsTemp.length; j++) {
            let lat = parseFloat(this.outerCoordsTemp[j].split(",")[0]);
            let lng = parseFloat(this.outerCoordsTemp[j].split(",")[1]);
            this.outerCoords.push(new google.maps.LatLng(lat, lng));
          }
          if (this.siteInfo.response[i].blueZone != null) {
            new google.maps.Polygon({
              paths: [this.outerCoords],
              strokeColor: 'blue',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'blue',
              fillOpacity: 0.35
            });
          } else if (this.siteInfo.response[i].yellowZone != null) {
            new google.maps.Polygon({
              paths: [this.outerCoords],
              strokeColor: 'yellow',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'yellow',
              fillOpacity: 0.35
            });
          } else if (this.siteInfo.response[i].redZone != null) {
            new google.maps.Polygon({
              paths: [this.outerCoords],
              strokeColor: 'red',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'red',
              fillOpacity: 0.35
            });
          }

        } else if ((this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone == null) || (this.siteInfo.response[i].blueZone == null && this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone != null) || (this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone == null && this.siteInfo.response[i].redZone != null)) {
          this.outerCoords2 = [];
          this.innerCoords2 = [];
          
          
          
          let latlngarr = "";
          let latlngarr1 = "";
          if (this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone != null) {
            latlngarr = this.siteInfo.response[i].blueZone.split(";");
            latlngarr1 = this.siteInfo.response[i].yellowZone.split(";");
          } else if (this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone != null) {
            latlngarr = this.siteInfo.response[i].yellowZone.split(";");
            latlngarr1 = this.siteInfo.response[i].redZone.split(";");
          } else if (this.siteInfo.response[i].redZone != null && this.siteInfo.response[i].blueZone != null) {
            latlngarr = this.siteInfo.response[i].blueZone.split(";");
            latlngarr1 = this.siteInfo.response[i].redZone.split(";");
          }
          for (var p = 0; p < latlngarr.length; p++) {
            this.outerCoords2.push(new google.maps.LatLng(parseFloat(latlngarr[p].split(",")[0]), parseFloat(latlngarr[p].split(",")[1])));
          }
          for (var p = 0; p < latlngarr1.length; p++) {
            this.innerCoords2.push(new google.maps.LatLng(parseFloat(latlngarr1[p].split(",")[0]), parseFloat(latlngarr1[p].split(",")[1])));
          }
          if (this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone != null) {
            const secondPoly = new google.maps.Polygon({
              paths: [this.outerCoords2, this.innerCoords2],
              strokeColor: 'blue',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'blue',
              fillOpacity: 0.35
            });
            const thirdPoly = new google.maps.Polygon({
              paths: [this.innerCoords2],
              strokeColor: 'yellow',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'yellow',
              fillOpacity: 0.35
            });
          } else if (this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone != null) {
            const secondPoly = new google.maps.Polygon({
              paths: [this.outerCoords2, this.innerCoords2],
              strokeColor: 'yellow',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'yellow',
              fillOpacity: 0.35
            });
            const thirdPoly = new google.maps.Polygon({
              paths: [this.innerCoords2],
              strokeColor: 'red',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'red',
              fillOpacity: 0.35
            });
          } else if (this.siteInfo.response[i].redZone != null && this.siteInfo.response[i].blueZone != null) {
            new google.maps.Polygon({
              paths: [this.outerCoords2, this.innerCoords2],
              strokeColor: 'blue',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'blue',
              fillOpacity: 0.35
            });
            new google.maps.Polygon({
              paths: [this.innerCoords2],
              strokeColor: 'red',
              strokeOpacity: 0.8,
              strokeWeight: 2,
              map: this.map,
              fillColor: 'red',
              fillOpacity: 0.35
            });
  
          }
       
        }
      
        this.markerVolcano = new google.maps.Marker({
          position: new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)),
          map: this.map,
          animation: google.maps.Animation.DROP,
          icon: "assets/Images/volcanoImg.png",
          title: this.siteInfo.response[i].volcanoName,
        });
        this.markerVolcano.addListener('click', (e) => {
          this.map.panTo(new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)));
          this.map.setZoom(15);
          //marker.setAnimation(google.maps.Animation.BOUNCE);
        })
      }


    })
  }

  popullationView() {

    this.mapService.getPopullationView().subscribe(res => {
    
      this.popullationInfo = res;
   

      for (let i = 0; i < this.popullationInfo.response.length; i++) {
      
        for (let j = 0; j < this.popullationInfo.response[i].addressDTOs.length; j++) {
      
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
         

          marker.addListener('click', (e) => {
        
            this.map.panTo(new google.maps.LatLng(myLatLng));
            this.map.setZoom(15);
            //marker.setAnimation(google.maps.Animation.BOUNCE);
          })

        }

      }
    })
  }
}
