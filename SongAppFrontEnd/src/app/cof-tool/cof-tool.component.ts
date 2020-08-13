import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SongService } from '../song.service'
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cof-tool',
  templateUrl: './cof-tool.component.html',
  styleUrls: ['./cof-tool.component.css']
})
export class CofToolComponent implements OnInit {

  theFile : string;
  constructor(
    private songService : SongService,
    private router : Router,
    private httpClient : HttpClient,
    private route : ActivatedRoute
  ) { }

  ngOnInit(): void {
    document.getElementById("saveSong").style.visibility = "hidden";
    console.log(localStorage.getItem("usr"));
    let j = localStorage.getItem("usr");
    if((j == "null") || (j == null)) {
      this.router.navigate([''], {relativeTo : this.route});
    }
  }

  base64ToArrayBuffer(base64) {
    var binary_string = window.atob(base64);
    var len = binary_string.length;
    var bytes = new Uint8Array(len);
    for (var i = 0; i < len; i++) {
        bytes[i] = binary_string.charCodeAt(i);
    }
    return bytes;
}

  cofgenerate() {
    document.getElementById("saveSong").style.visibility = "visible";
    console.log("hello");
    let input = {
      "tonic" : ((document.getElementById("tonic") as HTMLInputElement).value),
      "mode" : ((document.getElementById("mode") as HTMLInputElement).value),
      "noteLength" : ((document.getElementById("notelength") as HTMLInputElement).value),
    }
   
    this.httpClient.post("http://localhost:9000/song/cofordered", input)
    .subscribe(x => {
      this.theFile = x[0];
      let d = document.getElementById("songPlayback");
      d.innerHTML = '<audio controls="controls" autobuffer="autobuffer" autoplay="autoplay"><source src="data:audio/midi;base64,' + x[0] + '"/></audio>';
    });
  }

  saveSong() {
    let hh = this.theFile;
    var byteArray = this.base64ToArrayBuffer(hh);
    let gg = Array.from(byteArray);
    let input = {
      "title" : ((document.getElementById("songTitle") as HTMLInputElement).value),
      "songFile" : gg,
      "username" : localStorage.getItem("usr")
    }
    console.log(input);
    this.httpClient.post("http://localhost:9000/song/save", input)
    .subscribe(x => {
      console.log(x);
        let d = document.getElementById("saveMsg");
        d.innerHTML = "Saved file to profile.";

    });
  }

}
