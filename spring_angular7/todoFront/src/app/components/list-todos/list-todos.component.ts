import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { DateFormatPipe } from 'src/app/pipes/date-format.pipe';

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {
constructor(private _dateFormatPipe : DateFormatPipe) { }
  todos = [
    // new Todo(1,'desc1',false,this._dateFormatPipe.transform(new Date())),
    // new Todo(2,'desc2',true,this._dateFormatPipe.transform(new Date())),
    // new Todo(3,'desc3',true,this._dateFormatPipe.transform(new Date()))
    new Todo(1,'desc1',false,new Date()),
    new Todo(2,'desc2',true,new Date()),
    new Todo(3,'desc3',true,new Date())
      ]
  

  ngOnInit() {
  }

}
