import { HardcodedAuthenticationService } from 'src/app/services/hardcoded-authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  message = 'You have been logged out thank you for your visit';

  constructor(private _authService : HardcodedAuthenticationService) { }

  ngOnInit() {
    this._authService.logout();
  }

}
