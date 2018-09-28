import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  userData = new Subject<any>();
  constructor() { }
  private SubcheckList = new BehaviorSubject<any>('');
  currentSubCheckList = this.SubcheckList.asObservable();

  setSubCheckList(value: Object) {
    this.SubcheckList.next(value);
  }
}
