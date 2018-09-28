import { Injectable } from '@angular/core';
import  {  HttpClient,  HttpHeaders  }  from  '@angular/common/http';
import  {  Observable,  throwError  }  from  'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MapViewService {
  constructor(private http:  HttpClient) { }
  
 //private Url = "http://3.209.34.69:8081";
 private Url = "http://3.209.34.15:8080"
  //private Url = "https://callforcode-ms-prod.run.aws-usw02-pr.ice.predix.io";
  // private Url = "https://disaster-service.mybluemix.net";

  getSiteView(){
    const url = `${this.Url}/volcano/getVolcanoCentre`;
    return this.http.get(url);
  }
  getPopullationView(){
    const url = `${this.Url}/person/populationView`;
    return this.http.get(url);
  }
}
