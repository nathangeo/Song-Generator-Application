import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { CofToolComponent } from './cof-tool/cof-tool.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SongService } from './song.service';
import { PolyrythmComponent } from './polyrythm/polyrythm.component';
import { HomeComponent } from './home/home.component';
import { SongPlayerComponent } from './song-player/song-player.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { RandomSongComponent } from './random-song/random-song.component';

@NgModule({
  declarations: [
    AppComponent,
    CofToolComponent,
    LoginComponent,
    PolyrythmComponent,
    HomeComponent,
    SongPlayerComponent,
    RandomSongComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {
        path : '',
        component : LoginComponent
      },
      {
        path : 'coftool',
        component : CofToolComponent
      },
      {
        path : 'poly',
        component : PolyrythmComponent
      },
      {
        path : 'home',
        component : HomeComponent
      },
      {
        path : 'songplayer',
        component : SongPlayerComponent
      },
      {
        path : 'rs',
        component : RandomSongComponent
      }
    ]),
    NoopAnimationsModule
  ],
  providers: [
    SongService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
