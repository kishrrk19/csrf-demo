import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, HttpClientModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;

  private baseUrl = environment.gatewayUrl;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(20)
      ]]
    })
  }

  login() {

    if (this.loginForm.valid) {
      const formData = this.loginForm.value;
      this.http.post(`${this.baseUrl}/projects/login`, formData, {
        withCredentials: true
      }).subscribe({
        next: () => {
          this.router.navigate(['/home']);
        },
        error: err => {
          alert("Input not valid")
        }
      })
    }

  }

}
