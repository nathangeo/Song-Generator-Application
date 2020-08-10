import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SongService } from '../song.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-polyrythm',
  templateUrl: './polyrythm.component.html',
  styleUrls: ['./polyrythm.component.css']
})
export class PolyrythmComponent implements OnInit {

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
