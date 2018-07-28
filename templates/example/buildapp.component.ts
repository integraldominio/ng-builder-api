import { ConfigService } from './../../infra/security/config.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
@Component({
  selector: 'app-buildapp',
  styleUrls: ['./buildapp.component.css'],
  template: `
  <div fxLayout="row wrap" class="form-group">
  <div fxFlex.gt-sm="100" class="divide">
    <mat-card>
      <mat-card-title> NG builder  </mat-card-title>
      <mat-card-content>
      <p> Gerador de app.Será criado estrutura de fontend e backend a partir da pasta outputDirectory, informada em Projetos: </p>
      <form class="example-form">
          <mat-form-field style="min-width: 150px; max-width: 500px;width: 100%;">
          <input matInput placeholder="Projeto Id" [(ngModel)]="id" [ngModelOptions]="{standalone: true}" >
      </mat-form-field>
      </form>

      <button mat-raised-button color="primary" (click)='build()'> Gerar App !</button> <br/> {{app}} 

  </mat-card-content>
  </mat-card>
  </div>
  </div>  `,
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
