import { ConfigService } from './../../infra/security/config.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-buildapp',
  styleUrls: ['./buildapp.component.css'],
  template: `
  <h2> NG builder </h2>
  <p> Gerador de app.Será criado estrutura de fontend e backend a partir da pasta output. </p>
  <form class="example-form">

  <mat-form-field style="min-width: 150px; max-width: 500px;width: 100%;">
    <input matInput placeholder="Projeto Id" [(ngModel)]="id" [ngModelOptions]="{standalone: true}" >
  </mat-form-field>
  </form>

  <button mat-button (click)='build()'> Gerar App !</button> <br/> {{app}} `,
})
export class BuildAppComponent implements OnInit {

    app = '';
    id = '';

    constructor(private httpClient: HttpClient, private  configService: ConfigService) { }

    ngOnInit() {
    }

    build() {
    this.httpClient.get<any>(
        this.configService.getApiUrl() + '/projetos/build/' + this.id, { observe: 'response' })
        .subscribe(resp => {
            this.app = 'resp';
        });
    }
}
