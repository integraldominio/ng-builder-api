import { Component, OnInit } from '@angular/core';
import { VERSION } from '@angular/material';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  version = VERSION;
  caminho = 'Home';
  constructor() {}

  ngOnInit() {
  }

}
