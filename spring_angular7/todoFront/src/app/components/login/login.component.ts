import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HardcodedAuthenticationService } from 'src/app/services/hardcoded-authentication.service';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage : string = 'Invalid credentials' ;
  isInvalid  = false;
  username : string ;
  password : string ;
  

  constructor(private _router : Router, private _authService : HardcodedAuthenticationService) { }

  ngOnInit() {
  }

  login()
  {
    
      //console.log(this.username)
     
    this.isInvalid = this._authService.authenticate(this.username,this.password) ? false  : true;
    if(this.isInvalid === true)
      this._router.navigate(['login'])
    else
     this._router.navigate(['welcome',this.username])//will consider  welcome/:(name) <- params
  }

}
