import { Component, OnInit } from '@angular/core';
import { HardCodedAuthenticationService } from 'src/app/services/hard-coded-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(public _authService : HardCodedAuthenticationService) { }
  sessionName = this._authService.getUsername()

  // isLoggedIn()
  // {
    
  //   return this._authService.isLoggedIn()
  // }

  ngOnInit() {
  }

}
