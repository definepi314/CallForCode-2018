import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatInput, MatFormField, MatProgressSpinner } from '@angular/material';
import { Observable, Subject, throwError } from 'rxjs';
import { map } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { RegistrationServiceService } from '../service/registration-service.service';
import { DataService } from '../service/data.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: []
})
export class LoginComponent implements OnInit {
  firstName: string;
  lastName: string;
  color = 'primary';
  mode = 'indeterminate';
  value = 50;
  isNotLoading: any = true;
  subAccountList = [];
  isSuccess: any;
  spiner: Boolean = true;
  form: FormGroup;
  userName: string;
  password: string;
  userRole: string;
  accountId: string;

  constructor(private formBuilder: FormBuilder,
    private register: RegistrationServiceService,
    private data: DataService,
    private toastr: ToastrService,
    private router: Router) {
    this.form = this.formBuilder.group({
      userName: [null, Validators.compose([Validators.required, Validators.minLength(5)])],
      password: [null, Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(15)])],
    });
  }

  ngOnInit() {
    setTimeout(() => {
      this.spiner = false;
    }, 2500)
  }

  addPost(post) {
    const that = this;
    this.isNotLoading = false;

    this.userName = post.userName;
    this.password = post.password;
    let loginReqJsonBody = {
      "username": this.userName,
      "password": this.password,
    }
    console.log(loginReqJsonBody);
    this.register.validateUser(loginReqJsonBody).subscribe(res => {
      console.log("aeg", res);
      if (res["response"] !== null && res["response"] !== undefined) {
        if (res["response"].loginStatus) {
          this.router.navigate(['dashboard']);
          this.data.userData.next(res['response']);
          localStorage.setItem("LoggedIn", 'true');
          localStorage.setItem("Username", loginReqJsonBody.username);
          // localStorage.setItem("Role", "");roleName
          for(let i=0;i<res["response"]["roleName"].length;i++)
          {
            console.log(res["response"]["roleName"][i]);
            this.userRole=res["response"]["roleName"][i];
          }
          localStorage.setItem("Role", this.userRole);
          this.toastr.success('', 'Successfully Logged In');
        } else {
          this.toastr.error('', res['exception']);
        }
      }
      else {
        this.toastr.error('', res['exception']);
      }
    })
    // if (this.userName === 'admin' && this.password === 'admin') {
    //   console.log("reached here");
    //   this.router.navigate(['dashboard']);
    // } else {
    //   //this.toastr.error("invalid credentials");
    // }
  }
  registration() {
    this.router.navigate(['registration']);
  }
  reset() {
    this.isNotLoading = true;
    this.form.reset();
  }

}



