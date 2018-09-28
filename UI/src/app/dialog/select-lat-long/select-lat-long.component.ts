import { Component, OnInit, Inject, ElementRef, ViewChild,OnChanges,EventEmitter } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog } from "@angular/material";
declare var google: any;
@Component({
  selector: 'app-select-lat-long',
  templateUrl: './select-lat-long.component.html',
  styleUrls: ['./select-lat-long.component.css']
})
export class SelectLatLongComponent implements OnInit, OnChanges {
  emmittedLatLong = new EventEmitter<Marker>();
  @ViewChild('searchBoxInput') searchBoxInput: ElementRef;
  @ViewChild('map') mapElement: ElementRef;
  @ViewChild('form') infoWindow: ElementRef;
  volcanoMarkers = [];
  map: any;
  searchBox;
  zoneArray: Array<ZoneDetails> = [];
  showWindow = true;
  openWindow = null;

  constructor(private dialog: MatDialog, private dialogRef: MatDialogRef<SelectLatLongComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit() {
    const latLng = new google.maps.LatLng(-34.9290, 138.6010);
    const mapOptions = {
      center: new google.maps.LatLng(-34.9290, 138.6010),
      zoom: 7,
      mapTypeId: google.maps.MapTypeId.TERRAIN
    };
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    this.searchBox = new google.maps.places.SearchBox(this.searchBoxInput.nativeElement);
    this.map.controls[google.maps.ControlPosition.TOP_LEFT].push(this.searchBoxInput.nativeElement);
    this.showWindow = true;
    this.linkBox();
    this.markZone()
  }
  ngOnChanges() {
    // this.showWindow = true;
    // this.linkBox();
    // this.markZone()
  }

  markZone() {
    google.maps.event.addListener(this.map, 'click',
      (e) => {
        this.showWindow = false;
        console.log(e.latLng.lat());
         console.log(e.latLng.lng());
        const marker: Marker = { lat: e.latLng.lat(), lng: e.latLng.lng() };
          this.dialogRef.close();
        this.addMarker(marker);
        this.emmittedLatLong.emit({
          lat: e.latLng.lat(), 
          lng: e.latLng.lng() 
        })
      });
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
     this.volcanoMarkers.forEach((marker) => {
        marker.setMap(null);
      });
      this.volcanoMarkers = [];

      const bounds = new google.maps.LatLngBounds();
      places.forEach( (place) => {
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


  addMarker(markerLocation: Marker) {
    const marker = new google.maps.Marker({
      position: markerLocation,
      map: this.map,
      draggable: true,
      animation: google.maps.Animation.DROP,
      title: 'new Marker'
    });
  }  // end of add marker


  close() {
    this.dialogRef.close();
  }

}


export class Marker {
  lat: string;
  lng: string;

}

export class ZoneDetails {
  volcanoId: Number;
  lat: string;
  lng: string;
  zoneType: string;
}

