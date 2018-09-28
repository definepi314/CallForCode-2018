import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DisasterTrainingService {
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  constructor(private http: HttpClient) { }
  //private Url = "http://3.209.34.69:8081";
 private Url = "http://3.209.34.15:8080"
  //private Url = "https://callforcode-ms-prod.run.aws-usw02-pr.ice.predix.io";

  //private Url = "https://disaster-service.mybluemix.net";


  lookupDisaster() {
    const url = `${this.Url}/lookup/disaster`;
    return this.http.get(url);
  }
  addCheckList(body: any) {
    const url = `${this.Url}/volcano/addCheckList`;
    return this.http.post(url, JSON.stringify(body), this.httpOptions);
  }
  addTutorial(body: any) {
    const url = `${this.Url}/volcano/addTutorial`;
    return this.http.post(url, JSON.stringify(body), this.httpOptions);
  }
}
