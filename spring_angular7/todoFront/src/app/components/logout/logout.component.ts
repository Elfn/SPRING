import { Component, OnInit } from '@angular/core';
import { HardCodedAuthenticationService } from 'src/app/services/hard-coded-authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  message = 'You have been logged out thank you for your visit';

  constructor(private _authService : HardCodedAuthenticationService) { }

  ngOnInit() {
    this._authService.logout();
  }

}
