import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SongService {

  constructor(
    private httpClient : HttpClient
  ) { }


  generateCofOrdered(input) : any {
    
    var songString : String;
    this.httpClient.post("http://localhost:9000/song/cofordered", input)
    .subscribe(x => {
      
      songString = x[0];
      

    });
    return songString;
  }


}
