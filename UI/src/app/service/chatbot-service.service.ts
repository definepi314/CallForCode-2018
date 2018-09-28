import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ChatbotServiceService {

  private baseURL = 'https://disaster-service.mybluemix.net/ibmwatson/chatResponse?inputText=';
  private token = 'token';
  constructor(private http: HttpClient) { }

  //  getResponse(query: string) {
  //   const data = {
  //     query : query,
  //     lang: 'en',
  //     sessionId: '12345'
  //   };
  //   return this.http
  //     .post(`${this.baseURL}`, data, {headers: this.getHeaders()});
  // }

  getChatResponse(query: string) {
    if ( query === null) {
      return this.http.get(`${this.baseURL}`);
    } else {
      return this.http.get(`${this.baseURL}${query}`);
    }
  }
  getHeaders() {
    const headers = new HttpHeaders();
    headers.append('Authorization', `Bearer ${this.token}`);
    return headers;
  }
}
