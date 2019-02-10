import { WelcomeDataService } from './../../services/data/welcome-data.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
name : string = ''
message : string;
errorMessage : string;


  constructor(private route : ActivatedRoute, private welcomeService: WelcomeDataService) { }

  ngOnInit() {
    //Will capture localhost:4200/welcome/(name) <- params in the url bar
  this.name = this.route.snapshot.params['name'];
    
  }

  getWelcomeMessage()
  {
    console.log(this.welcomeService.messageService());
    this.welcomeService.messageServiceVariable(this.name).subscribe(

        response => this.message = this.handleSuccessFullResponse(response),
        error => this.errorMessage = this.handleError(error)

    );

    console.log('Message sent!');
  }

  handleSuccessFullResponse(response)
  {
    return response.message;

  }

  handleError(error)
  {
   return error.error.message;
  }

}
