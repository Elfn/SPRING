import { HardcodedAuthenticationService } from 'src/app/services/hardcoded-authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private _authService : HardcodedAuthenticationService) { }
  sessionName = this._authService.getUsername()

  isLoggedIn()
  {
    
    return this._authService.isUserLoggedIn()
  }

  ngOnInit() {
  }

}
