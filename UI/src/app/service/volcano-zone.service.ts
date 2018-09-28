import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VolcanoZoneService {
  //Url = 'http://3.209.34.69:8081/volcano';
 private Url = "http://3.209.34.15:8080/volcano";
//  private Url = "https://disaster-service.mybluemix.net/volcano";
  //private posturl = "https://callforcode-ms-prod.run.aws-usw02-pr.ice.predix.io/volcano/addZone";
  //private posturl = "https://disaster-service.mybluemix.net/volcano/addZone";

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  addVolcano(body) {
    const url = `${this.Url}/addZone`;
    return this.http.post(url, body, this.httpOptions);
  }

  getAnalytics(body) {
    const url = `${this.Url}/getAnalytics`;
    return this.http.post(url, body, this.httpOptions);
  }
}
