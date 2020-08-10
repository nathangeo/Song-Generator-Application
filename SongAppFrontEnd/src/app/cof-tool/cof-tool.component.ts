import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SongService } from '../song.service'
import { Router } from '@angular/router';

@Component({
  selector: 'app-cof-tool',
  templateUrl: './cof-tool.component.html',
  styleUrls: ['./cof-tool.component.css']
})
export class CofToolComponent implements OnInit {

  constructor(
    private songService : SongService,
    private router : Router,
    private httpClient : HttpClient
  ) { }

  ngOnInit(): void {
  }

  cofgenerate() {
    console.log("hello");
    let input = {
      "tonic" : ((document.getElementById("tonic") as HTMLInputElement).value),
      "mode" : ((document.getElementById("mode") as HTMLInputElement).value),
      "noteLength" : ((document.getElementById("notelength") as HTMLInputElement).value),
    }
   
    this.httpClient.post("http://localhost:9000/song/cofordered", input)
    .subscribe(x => {
      
      let d = document.getElementById("songPlayback");
      d.innerHTML = '<audio controls="controls" autobuffer="autobuffer" autoplay="autoplay"><source src="data:audio/midi;base64,' + x[0] + '"/></audio>';
    });
  }

  polyrythmGenerate() {
    let input = {
      "left" : ((document.getElementById("left") as HTMLInputElement).value),
      "right" : ((document.getElementById("right") as HTMLInputElement).value),
      "beatLength" : ((document.getElementById("beatLength") as HTMLInputElement).value)
    }

    this.httpClient.post("http://localhost:9000/song/polyrythm", input)
    .subscribe(x => {
      let d = document.getElementById("polyPlayback");
      d.innerHTML = '<audio controls="controls" autobuffer="autobuffer" autoplay="autoplay"><source src="data:audio/midi;base64,' + x[0] + '"/></audio>';
    });
  }

}
