import { MapViewService } from './../../service/map-view.service';
import { Component, OnInit, ViewChild, ElementRef, OnChanges, AfterViewInit } from '@angular/core';
import { MatDialog, ErrorStateMatcher } from '@angular/material';
import { FormControl, FormGroupDirective, NgForm, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { VolcanoZoneService } from '../../service/volcano-zone.service';
declare var google: any;
@Component({
  selector: 'app-zonemark',
  templateUrl: './zonemark.component.html',
  styleUrls: ['./zonemark.component.css']
})
export class ZonemarkComponent implements OnInit, OnChanges, AfterViewInit {
  showInfoWindow: Boolean = true;
  showSubmitWindow: Boolean = true;
  isSaveDisable: Boolean = false
  @ViewChild('map') mapElement: ElementRef;
  @ViewChild('form2') infoWindow: ElementRef;
  @ViewChild('form1') infoWindow1: ElementRef;
  @ViewChild('searchBoxInput') searchBoxInput: ElementRef;
  infoWindowFlag1: boolean = false;
  volcanoName = '';
  volcanoMarkers = [];
  allMarkerArray = [];
  map: any;
  currentVolcanoId: number;
  searchBox;
  zoneArray: Array<ZoneDetails> = [];
  zoneMarkers = [];
  openWindow = null;
  addEditFlag = '';
  mappedMarker = [];
  // openWindow;
  zone: String = "";
  outerCoords = [];
  outerCoordsTemp = [];
  innerYCoords = [];
  innerYCoordsTemp = [];
  innerRCoords = [];
  innerRCoordsTemp = [];
  outerCoords2 = [];
  innerCoords2 = [];
  /****from ryb view */
  siteInfo: any;
  /***/
  constructor(private formBuilder: FormBuilder, private volcanoZoneService: VolcanoZoneService, public mapService: MapViewService) {

  }
  ngOnInit() {
    let mapOptions = {
      center: new google.maps.LatLng(parseFloat("-35.11793454997535"), parseFloat("139.1667958984375")),
      zoom: 14,
      mapTypeId: google.maps.MapTypeId.SATELLITE
    }
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);
    console.log(this.searchBoxInput.nativeElement);
    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
    this.linkBox();
    this.loadmap();
  }
  ngAfterViewInit() {
  }
  ngOnChanges() {
  }
  commonFunction(res) {
    console.log(res);
    this.siteInfo = res;
    let mapOptions = {
      center: new google.maps.LatLng("-35.11793454997535", "139.1667958984375"),
      zoom: 14,
      mapTypeId: google.maps.MapTypeId.SATELLITE
    }
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);
    console.log(this.searchBoxInput.nativeElement);
    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
    this.linkBox();

    for (let i = 0; i < this.siteInfo.response.length; i++) {
      console.log(this.siteInfo.response[i]);
      console.log("my", this.siteInfo.response[i])
      if (this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone != null) {
        console.log("i came in")
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
        //console.log("akshat",outerCoordsTemp);
        for (var j = 0; j < this.outerCoordsTemp.length; j++) {
          let lat = parseFloat(this.outerCoordsTemp[j].split(",")[0]);
          let lng = parseFloat(this.outerCoordsTemp[j].split(",")[1]);
          console.log("check", lat, lng)
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
          paths: [this.innerYCoords, this.innerRCoords],
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
      console.log(this.innerRCoords);
      let marker = new google.maps.Marker({
        position: new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)),
        map: this.map,
        animation: google.maps.Animation.DROP,
        icon: "assets/Images/volcanoImg.png",
        title: this.siteInfo.response[i].volcanoName,
        volcanoId: this.siteInfo.response[i].volcanoId
      });
      this.volcanoMarkers.push(marker);
      this.allMarkerArray.push(marker);
    }
  }


  volcanoView(res) {
    console.log(res);

    this.siteInfo = res;
    for (let i = 0; i < this.siteInfo.response.length; i++) {
      console.log(this.siteInfo.response[i]);
      console.log("my", this.siteInfo.response[i])
      if (this.siteInfo.response[i].blueZone != null && this.siteInfo.response[i].yellowZone != null && this.siteInfo.response[i].redZone != null) {
        this.outerCoordsTemp = [];
        this.innerYCoordsTemp = [];
        this.innerRCoordsTemp = [];
        this.outerCoords = [];
        this.innerYCoords = [];
        this.innerRCoords = [];

        console.log("i came in")
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
        //console.log("akshat",outerCoordsTemp);
        for (var j = 0; j < this.outerCoordsTemp.length; j++) {
          let lat = parseFloat(this.outerCoordsTemp[j].split(",")[0]);
          let lng = parseFloat(this.outerCoordsTemp[j].split(",")[1]);
          console.log("check", lat, lng)
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
          console.log("check", lat, lng)
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
      console.log("fuc")
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
            console.log("fuc again",this.outerCoords2, this.innerCoords2)
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
            });}
      }
      console.log(this.innerRCoords);
      let marker = new google.maps.Marker({
        position: new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)),
        map: this.map,
        animation: google.maps.Animation.DROP,
        icon: "assets/Images/volcanoImg.png",
        title: this.siteInfo.response[i].volcanoName,
        volcanoId: this.siteInfo.response[i].volcanoId
      });
      marker.addListener('click', (e) => {
        console.log(e.latLng.lat());
        this.map.panTo(new google.maps.LatLng(parseFloat(this.siteInfo.response[i].latitude), parseFloat(this.siteInfo.response[i].longitude)));
        this.map.setZoom(15);
        //marker.setAnimation(google.maps.Animation.BOUNCE);
      });
      this.volcanoMarkers.push(marker);
      this.allMarkerArray.push(marker);
    }
  }


  loadmap() {
    this.mapService.getSiteView().subscribe(res => {
      //this.commonFunction(res);
      console.log('in loadmap',res)
      this.volcanoView(res);
    })
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


  addVolcano() {
    this.emptyMap();
    google.maps.event.addListener(this.map, 'click',
      (e) => {
        this.showSubmitWindow = false;
        this.emptyMap();
        console.log(e.latLng.lat());
        const position = { lat: e.latLng.lat(), lng: e.latLng.lng() };
        const marker = new google.maps.Marker({
          position: position,
          map: this.map,
          draggable: true,
          animation: google.maps.Animation.DROP,
          title: 'new Marker'
        });
        this.mappedMarker.push(marker);
        const infowindow = new google.maps.InfoWindow({
          content: this.infoWindow1.nativeElement,
        });
        infowindow.open(this.map, marker);
        google.maps.event.addListener(infowindow, 'closeclick', (eevnt) => {
          this.emptyMap(); // removes the marker
          // then, remove the infowindows name from the array
        });
      });
  }

  editZone() {
    google.maps.event.clearListeners(this.map, 'click');
    this.mappedMarker = [];
    this.allMarkerArray.forEach((marker) => {
      this.currentVolcanoId = marker.volcanoId;
      marker.addListener('click', (e) => {
        // attach click event to every marker
        console.log("e", e);
        this.showInfoWindow = false;
        this.openWindow = new google.maps.InfoWindow({
          content: this.infoWindow.nativeElement
        });
        this.openWindow.open(this.map, marker);
        this.mappedMarker.push(marker);
        google.maps.event.addListener(this.openWindow, 'closeclick', (evnt) => {
          this.loadPreviousMarkers();
        });
        this.removeOtherMarkers(marker);
      });
    });
  }

  saveVolcano(value) {
    const toSave = {
      latitude: this.mappedMarker[0].position.lat(),
      longitude: this.mappedMarker[0].position.lng(),
      volcanoName: value.volcanoName,
      radius:Number(value.volcanoRadius),
      volcanoId: 0,
      zoneId: 0,
      zone: ''
    };
    this.volcanoZoneService.addVolcano(toSave).subscribe(response => {
      console.log(response);
      this.showSubmitWindow = true;
      const infowindow = new google.maps.InfoWindow({
        content: this.infoWindow1.nativeElement,
      });
      this.emptyMap();
      this.loadmap()
    });
    console.log('toSave', toSave);
    // save the marker from here and render the markers that willl come in response from backend
  }


  //edit zone


  loadPreviousMarkers() {
    this.volcanoMarkers.forEach((arrayMarker) => {
      arrayMarker.setMap(this.map);

    });
    if (this.zoneMarkers.length !== 0) {
      this.zoneMarkers.forEach((marker) => {
        marker.setMap(null);
      });
    }
    this.zoneMarkers = [];
  }
  removeOtherMarkers(marker) {
    this.volcanoMarkers.forEach((arrayMarker) => {
      if (marker !== arrayMarker) {
        arrayMarker.setMap(null);
      }
    });
  }

  radioButtonChanged(event) {
    console.log(event);
    if (event.value) {
      this.openWindow.close();
      this.isSaveDisable = true;
    }

    // now add zone 
    google.maps.event.addListener(this.map, 'click',
      (e) => {
        console.log(e.latLng.lat());
        const position = { lat: e.latLng.lat(), lng: e.latLng.lng() };
        let icon = '';
        if (this.zone === 'blue') {
          icon = 'https://maps.google.com/mapfiles/ms/icons/blue-dot.png';
        } else if (this.zone === 'red') {
          icon = 'https://maps.google.com/mapfiles/ms/icons/red-dot.png';
        } else if (this.zone === 'yellow') {
          icon = 'https://maps.google.com/mapfiles/ms/icons/yellow-dot.png';
        }

        const marker = new google.maps.Marker({
          position: position,
          icon: icon,
          map: this.map,
          // draggable: true,
          animation: google.maps.Animation.DROP,
          title: this.zone
        });
        this.zoneMarkers.push(marker);
      });
  }

  saveZone() {
    let zone = "";
    this.zoneMarkers.forEach((marker) => {
      console.log("zoneMarkers", marker);
      this.zoneArray.push({
        volcanoId: this.mappedMarker[0].id,
        lat: marker.position.lat(),
        lng: marker.position.lng(),
        zoneType: marker.title
      });
      if (zone !== "")
        zone = zone + ";" + marker.position.lat() + "," + marker.position.lng();
      else {
        zone = zone + marker.position.lat() + "," + marker.position.lng();
      }
    });
    // zone.substr(0,zone.length-3);
    console.log("zones", zone);
    console.log(this.zoneArray);
    let zoneId;
    if (this.zoneMarkers['0'].title == 'red') {
      zoneId = 1;
    } else if (this.zoneMarkers['0'].title == 'yellow') {
      zoneId = 2;
    } else if (this.zoneMarkers['0'].title == 'blue') {
      zoneId = 3;
    }

    // save zone markers here then empty the map and bind the response to  this.volcanoMarkers[];
    const toSave = {
      volcanoId: this.currentVolcanoId,
      zoneId: zoneId,
      zone: zone,
      latitude:"",
      longitude:"",
      volcanoName:""
    };
    console.log(toSave);
    this.volcanoZoneService.addVolcano(toSave).subscribe(res => {
      console.log(res);
    })
    this.emptyMap();
    this.zoneArray = [];
    google.maps.event.clearListeners(this.map, 'click'); // unbind the event
    this.loadmap();
  }

  emptyMap() {
 console.log('in empty map')
    if (this.volcanoMarkers.length !== 0) {
      this.volcanoMarkers.forEach((marker) => {
        marker.setMap(null);
      });
    }
    //this.volcanoMarkers = [];
    if (this.mappedMarker.length !== 0) {
      this.mappedMarker.forEach((marker) => {
        marker.setMap(null);
      });
    }
    this.mappedMarker = [];

    if (this.zoneMarkers.length !== 0) {
      this.zoneMarkers.forEach((marker) => {
        marker.setMap(null);
      });
    }
    this.zoneMarkers = [];
  }

}

export class Marker {
  lat: string;
  lng: string;

}

export interface AddMarkerJson {
  position: Object;
  name: string;
}


export class ZoneDetails {
  volcanoId: number;
  lat: string;
  lng: string;
  zoneType: string;
}
