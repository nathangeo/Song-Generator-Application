import { Component, OnInit } from '@angular/core';
import { SongService } from '../song.service';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(
    private songService : SongService,
    private router : Router,
    private httpClient : HttpClient,
    private route : ActivatedRoute
  ) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"));
    let j = localStorage.getItem("usr");
    if((j == "null") || (j == null)) {
      this.router.navigate([''], {relativeTo : this.route});
    }
  }

  logout() {
    localStorage.setItem("usr", null);
    this.router.navigate([''], {relativeTo : this.route});
  }


}
