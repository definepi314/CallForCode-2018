import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CheckListService {
  //checkListUrl = 'assets/checklist.json';
  // private Url = "http://3.209.34.69:8081";
 private Url = "http://3.209.34.15:8080";
 // private Url = "https://disaster-service.mybluemix.net";
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getCheckList(username:string) {
    const url = `${this.Url}/volcano/getCheckList?username=${username}`;
    return this.http.get(url);
  }
  getTutorial(username:string) {
    const url = `${this.Url}/volcano/getTutorial?username=${username}`;
    return this.http.get(url);
  }
  saveCheckList(body){
    const url = `${this.Url}/volcano/savecheckListForm`;
    return this.http.post(url,body,this.httpOptions);
  }
}
