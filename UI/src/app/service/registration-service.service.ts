import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, Subject, asapScheduler, pipe, of, from, interval, merge, fromEvent, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class RegistrationServiceService {
   // private configUrl = "http://3.209.34.69:8081";
  private configUrl = "http://3.209.34.15:8080"
    private isUserLoggedIn;
 //private configUrl = "https://callforcode-ms-prod.run.aws-usw02-pr.ice.predix.io";
// private configUrl = "https://disaster-service.mybluemix.net";

  constructor(private http: HttpClient) { 
    this.isUserLoggedIn = localStorage.getItem("LoggedIn");
    console.log("LoggedIn Status is ", this.isUserLoggedIn);
  }
  serUserLoggedIn(value: Boolean) {
    this.isUserLoggedIn = value;
  }
  getUserLoggedIn() {
    return this.isUserLoggedIn;
  }
  getContries() {
    return this.http.get(this.configUrl + '/lookup/country');
  }

  getStates(countryId) {
    return this.http.get(this.configUrl + '/lookup/state?countryId=' + countryId);
  }
  getCity(stateId) {
    return this.http.get(this.configUrl + '/lookup/city?stateId=' + stateId);
  }

  registerUser(registrationBody) {
    return this.http.post(this.configUrl + '/role/register', registrationBody, httpOptions);
  }

  validateUser(userBody) {
    return this.http.post(this.configUrl + '/role/login', userBody, httpOptions);
  }
}

