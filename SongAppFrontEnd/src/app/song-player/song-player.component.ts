import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SongService } from '../song.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-song-player',
  templateUrl: './song-player.component.html',
  styleUrls: ['./song-player.component.css']
})
export class SongPlayerComponent implements OnInit {

  theIndex = 0;
  songArray : any = [];
  songFileArray : any = [];
  constructor(
    private httpClient : HttpClient,
    private songService : SongService,
    private router : Router,
    private route : ActivatedRoute
  ) { }

  ngOnInit(): void {
    console.log(localStorage.getItem("usr"));
    let j = localStorage.getItem("usr");
    if((j == "null") || (j == null)) {
      this.router.navigate([''], {relativeTo : this.route});
    }
    let input = {
      "username" : localStorage.getItem("usr")
    }
    this.httpClient.post("http://localhost:9000/song/getusersongs", input)
    .subscribe(x => {
      this.songArray = x;
      for(let i = 0; i < this.songArray.length; i++) {
        this.songFileArray[i] = x[i].songFile;
      }
      console.log(this.songArray);
      console.log(this.songFileArray);
      let d = document.getElementById("controllerz");
      d.innerHTML = 'Title: ' + this.songArray[0].title + '<br><audio controls="controls" autobuffer="autobuffer" autoplay="autoplay"><source src="data:audio/midi;base64,' + this.songFileArray[0] + '"/></audio>';
    });
  }

  playSong(i : number) {
    this.theIndex = this.theIndex + 1;
    let d = document.getElementById("controllerz");
    if(this.theIndex > this.songArray.length - 1) {
      this.theIndex = 0;
    }

    d.innerHTML = 'Title: ' + this.songArray[i].title + '<br><audio controls="controls" autobuffer="autobuffer" autoplay="autoplay"><source src="data:audio/midi;base64,' + this.songFileArray[i] + '"/></audio>';
  }

}
