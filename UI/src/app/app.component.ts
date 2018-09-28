import { Component , AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'app';

  ngAfterViewInit(): void {
    // Load googlel maps script after view init
    const DSLScript = document.createElement('script');
    DSLScript.src = 'https://maps.google.com/maps/api/js?key=AIzaSyDTnnlhDW71zaEyVichmDouZaH7p0_Of_g&libraries=places&sensor=false';
    DSLScript.type = 'text/javascript';
    document.body.appendChild(DSLScript);
    document.body.removeChild(DSLScript);
  }
}
