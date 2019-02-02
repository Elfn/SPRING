import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
name : string = ''


  constructor(private route : ActivatedRoute) { }

  ngOnInit() {
    //Will capture localhost:4200/welcome/(name) <- params in the url bar
  this.name = this.route.snapshot.params['name'];
    
  }

}
