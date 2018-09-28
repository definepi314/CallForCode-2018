import { Component, OnInit, Output, EventEmitter, Inject } from '@angular/core';
import { MatInput, MatFormField, MatSelect, MatAutocomplete, MatAutocompleteSelectedEvent } from '@angular/material';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog, MatDialogConfig, MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { SelectLatLongComponent } from '../dialog/select-lat-long/select-lat-long.component';
import { RegistrationServiceService } from '../service/registration-service.service';
import { ToastrService } from 'ngx-toastr';

export class Country {
  id: number;
  name: string;
}
export class States {
  id: number;
  name: string;
}
export class City {
  id: number;
  name: string;
}
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  isNotLoading: Boolean = false;
  userRegister: Boolean = true;
  regForm: FormGroup;
  isPhysicallyChallenged: Boolean = false;
  isConfirm: Boolean = false;
  isLocationSelected: Boolean = false;
  countries: Country[];
  states: States[];
  cities: City[];
  lat: String;
  lng: String;
  userName:String = "";

  constructor(private register: RegistrationServiceService,
    private formBuilder: FormBuilder,
    public router: Router,
    private toastr: ToastrService,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.register.getContries().subscribe((res: Country[]) => {
      console.log(res.length);
      this.countries = res;
    })
    this.isNotLoading = true;
    this.regForm = this.formBuilder.group({
      firstName: [null, Validators.compose([Validators.required, Validators.minLength(3)])],
      middleName: [null],
      lastName: [null, Validators.compose([Validators.required, Validators.minLength(3)])],
      email: [null, Validators.compose([Validators.required, Validators.email])],
      password: [null, Validators.compose([Validators.required, Validators.minLength(6)])],
      confirmPassword: [null, Validators.compose([Validators.required, Validators.minLength(6)])],
      country: [null, [Validators.required]],
      state: [null, [Validators.required]],
      mobile: [null],
      city: [null, [Validators.required]],
      landmark: [null],
      latitude: [null],
      longitude: [null]
    });
    this.regForm.get("latitude").disable();
    this.regForm.get("longitude").disable();

    this.regForm.get("confirmPassword").valueChanges.subscribe(res => {
      if (res === this.regForm.get("password").value) {
        console.log("matched");
        this.isConfirm = true;
      } else {
        this.isConfirm = false;
      }
    });

    this.regForm.get("password").valueChanges.subscribe(res => {
      if (res === this.regForm.get("confirmPassword").value) {
        console.log("matched");
        this.isConfirm = true;
      } else {
        this.isConfirm = false;
      }
    });

    // setTimeout(() => {
    //   this.userRegister = false;
    // }, 5000);
  }

  changeCountry(country) {
    this.register.getStates(country.value.id).subscribe((res: States[]) => {
      this.states = res;
    })
  }

  changeState(state) {
    this.register.getCity(state.value.id).subscribe((res: City[]) => {
      this.cities = res;
    });
  }

  changeCity(city) {
    console.log(city.value);
  }

  selectLocation() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '850px';
    dialogConfig.height = '600px';
    dialogConfig.data = {
    };
    let dialogRef = this.dialog.open(SelectLatLongComponent, dialogConfig);
    dialogRef.componentInstance.emmittedLatLong.subscribe(res => {
      console.log('emmitted response', res);
      this.lat = res.lat;
      this.lng = res.lng;
      if (this.lat !== null && this.lng !== null || this.lat !== undefined && this.lng !== undefined) {
        this.isLocationSelected = true;
      }
      console.log('emmitted response', this.lat, this.lng);
      this.regForm.patchValue({
        latitude: [this.lat],
        longitude: [this.lng]
      })
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  reset() {
    this.regForm.reset();
    this.isPhysicallyChallenged = false;
  }

  registerUser() {
    console.log(this.regForm);
    let postObject = {
      "firstName": this.regForm.value.firstName,
      "middleName": this.regForm.value.middleName,
      "lastName": this.regForm.value.lastName,
      "email": this.regForm.value.email,
      "password": this.regForm.value.password,
      "mobile": Number(this.regForm.value.mobile),
      "isPhysicallyChallenged": this.isPhysicallyChallenged,
      "cityId": this.regForm.value.city.id,
      "landmark": this.regForm.value.landmark,
      "latitude": this.lat,
      "longitude": this.lng
    }
    console.log(postObject);
    this.register.registerUser(postObject).subscribe((res) => {
      console.log(res);
      if (res["response"] !== null) {
        this.userName = res['response'];
        //this.router.navigate(['/login']);
        this.userRegister = false;
        this.toastr.success('', 'User Registered Successfully');
      } else {
        this.toastr.error('', res["exception"]);
      }
    });
  }
  backButton() {
    this.router.navigate(['/']);
  }
}
