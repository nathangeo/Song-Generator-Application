import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  show : boolean = false;

  constructor(
    private httpClient : HttpClient,
    private router : Router,
    private route : ActivatedRoute
  ) { }

  ngOnInit(): void {
  }

  password() {
    this.show = !this.show;
  }

  onClick(e : Event): void {
    var usr = ((document.getElementById("usr") as HTMLInputElement).value);
    var pass = ((document.getElementById("pass") as HTMLInputElement).value);
    localStorage.setItem("usr", usr);
    var message = document.getElementById("msg");
    var user = {
      "username": usr,
      "password": pass
    }
    this.httpClient.post("http://localhost:9000/user/login", user)
    .subscribe(x => {
      if(x != null) {
        this.router.navigate(['/home'], {relativeTo : this.route});
        console.log("login success");
      } else {
        this.router.navigate([''],{relativeTo : this.route});
        console.log("login failed");
        message.innerHTML = "Login Failed, try again.";
      }
    });
  }

  createUser() {
    console.log("wordks");
  }

}
