# Angular Dashboard Material do Zero

## Pré-requisitos

* NodeJS
* Angular CLI

## (1) Criando app Angular

```
ng new ngmaterial
```

## (2) Instalando Componentes 

```
npm i @angular/material @angular/cdk @angular/animations @angular/flex-layout
npm i @ngx-formly/material @ngx-formly/core
npm i @ng-select/ng-select
npm i hammerjs
npm install --save-dev @compodoc/compodoc
```

## (3) Configurando App

```html
index.xhml

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">

  <style>
      body{
        background-color: #3f51b5;
      }
      .loader {
          position: absolute;
          height: 80px;
          width: 80px;
          left: calc(50% - 40px);
          top: calc(50% - 40px);
      }
  </style>

  <body>
    <app-base>
        <img src="assets/svg-loaders/puff.svg" class="loader">
      </app-base>
  </body>

```

```
main.js

import 'hammerjs';

```

```
style.css

@import "~@angular/material/prebuilt-themes/indigo-pink.css";
@import "~@ng-select/ng-select/themes/material.theme.css";

body{
  margin: 0;
}

/* Formulários */

:host {
  display: block;
  padding: 15px;
}

.form-check {
  display: block;
  margin: 16px 0 32px;
}

.display-block {
  display: block;
}

.divide {
  padding: 0 15px;
}

.form-group {
  margin-bottom: 15px;
  margin-top: 15px;
}

```

```
package.json

"scripts": {
  "compodoc": "./node_modules/.bin/compodoc -p src/tsconfig.app.json"
}
```

## (4) Criando componentes

```
ERP
ng g c erp/fatura --spec false
ng g c erp/cliente --spec false
ng g c erp/produto --spec false

PAGES
ng g c pages/base --spec false
ng g c pages/erro --spec false
ng g c pages/home --spec false
ng g c pages/login --spec false
ng g c pages/sidenav --spec false
ng g c pages/sobre --spec false

SHARED
ng g m shared/material --spec false --flat

INFRA
ng g c infra/security/adminGuard --spec false --flat
ng g c infra/security/authGuard --spec false --flat
ng g c infra/security/guestGuard --spec false --flat
ng g c infra/security/jwtInterceptor --spec false --flat
ng g c infra/security/messageService --spec false --flat
ng g c infra/security/resourceService --spec false --flat
ng g c infra/security/userService --spec false --flat

ROTAS
ng g m appRotas --spec false --flat

```

## (5) Estrutura da app

```
├───app
│   │   app-rotas.module.ts
│   │   app.module.ts
│   │
│   ├───erp
│   │   ├───cliente
│   │   │       cliente.component.css
│   │   │       cliente.component.html
│   │   │       cliente.component.spec.ts
│   │   │       cliente.component.ts
│   │   │       cliente.service.ts
│   │   │
│   │   ├───fatura
│   │   │       fatura.component.css
│   │   │       fatura.component.html
│   │   │       fatura.component.spec.ts
│   │   │       fatura.component.ts
│   │   │       fatura.service.ts
│   │   │       real-api.component.html
│   │   │       real-api.component.ts
│   │   │
│   │   └───produto
│   │           produto.component.css
│   │           produto.component.html
│   │           produto.component.spec.ts
│   │           produto.component.ts
│   │           produto.service.ts
│   │
│   ├───infra
│   │   ├───components
│   │   │   └───search-select
│   │   │           base.ts
│   │   │           index.ts
│   │   │           search-select.component.css
│   │   │           search-select.component.html
│   │   │           search-select.component.ts
│   │   │           search-select.module.ts
│   │   │           types.ts
│   │   │
│   │   ├───pipes
│   │   │       cpf.Pipe.ts
│   │   │       customCurrency.Pipe.ts
│   │   │       index.ts
│   │   │       preencheZero.Pipe.ts
│   │   │
│   │   └───security
│   │           admin.guard.ts
│   │           auth.guard.ts
│   │           authentication.service.ts
│   │           config.service.ts
│   │           guest.guard.ts
│   │           index.ts
│   │           jwt.interceptor.ts
│   │           message.service.ts
│   │           resource.service.ts
│   │           user.service.ts
│   │
│   ├───pages
│   │   ├───base
│   │   │       base.component.html
│   │   │       base.component.ts
│   │   │
│   │   ├───comps
│   │   │   └───breadcrumb
│   │   │           breadcrumb.component.css
│   │   │           breadcrumb.component.ts
│   │   │
│   │   ├───erro
│   │   │       erro.component.css
│   │   │       erro.component.html
│   │   │       erro.component.ts
│   │   │
│   │   ├───home
│   │   │       home.component.css
│   │   │       home.component.html
│   │   │       home.component.ts
│   │   │
│   │   ├───login
│   │   │       login.component.html
│   │   │       login.component.scss
│   │   │       login.component.ts
│   │   │
│   │   ├───sidenav
│   │   │       sidenav.component.css
│   │   │       sidenav.component.html
│   │   │       sidenav.component.ts
│   │   │       sidenav.service.ts
│   │   │
│   │   └───sobre
│   │           sobre.component.css
│   │           sobre.component.html
│   │           sobre.component.ts
│   │
│   └───shared
│           material.module.ts
│
├───assets
│   │   .gitkeep
│   │
│   ├───icon
│   │       auth.svg
│   │       facebook.svg
│   │       firebase.svg
│   │       github-logo.svg
│   │       github-plus.png
│   │       google-plus.svg
│   │
│   ├───images
│   │       face-7.jpg
│   │       logo.svg
│   │
│   └───svg-loaders
│           puff.svg
│
└───environments
        environment.prod.ts
        environment.ts
```

## (6) Crud Resource Service
A classe abaixo, realiza as operações de CRUD na API:

```javascript
resource.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MessageService } from './message.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

export class Resource {
  id: number;
  parentId?: number;
}

export interface Serializer {
  fromJson(json: any): Resource;
  toJson(resource: Resource): any;
}

export class ResourceService<T extends Resource> {

  constructor(
      private http: HttpClient,
      private url: string,
      private endpoint: string,
      private messageService: MessageService ) {
          console.log('ResourceService');
      }

    public create(item: T): Observable<T> {
        return this.http.post<T>(`${this.url}/${this.endpoint}`, item, httpOptions)
        .pipe(
          tap( (i: T) => this.log(`added id=${i.id}`)),
          catchError(this.handleError<T>('Erro criando...'))
        );
    }

    public read(id: number): Observable<T> {
      return this.http.get<T>(`${this.url}/${this.endpoint}/${id}`).pipe(
        tap( _ => this.log(`Carrefando id=${id}`)),
        catchError(this.handleError<any>(`${this.endpoint} id=${id}`))
      );
    }

    public update(item: T): Observable<T> {
        return this.http.put(`${this.url}/${this.endpoint}/${item.id}`, item, httpOptions)
        .pipe(
          tap(_ => this.log(`updated id=${item.id}`)),
          catchError(this.handleError<any>('Erro atualizando...'))
        );
    }

    public search(queryOptions: QueryOptions): Observable<T[]> {
        return this.http.get<T[]>(`${this.url}/${this.endpoint}?${queryOptions.toQueryString()}`)
        .pipe(
          tap(_ => this.log(`Encontrados...`)),
          catchError(this.handleError<T[]>('Erro Busando...', []))
        );
    }

    public searchParams(params: string): Observable<T[]> {
      return this.http.get<T[]>(`${this.url}/${this.endpoint}?${params}`)
      .pipe(
        tap( _ => (console.log(''))) /*tap(_ => this.log(`Encontrados...`))*/ ,
        catchError(this.handleError<T[]>('Erro Busando...', []))
      );
  }

    public listAll() {
      return this.http.get<T[]>(`${this.url}/${this.endpoint}`)
      .pipe(
        tap( _ => (console.log(''))) /* tap( _ => this.log(`List All...`)) */,
        catchError(this.handleError<T[]>('Erro Busando...', []))
      );
  }

    public delete(id: number) {
      if ( id as number > 0 ) {
      return this.http.delete<T>(`${this.url}/${this.endpoint}/${id}`, httpOptions)
      .pipe(
        tap(_ => this.log(`Deletado id=${id}`)),
        catchError(this.handleError<T>('Erro Deletando...'))
      );
      }
      this.log('Informe ID válido');
    }

    private log(message: string, acao?: string) {
      this.messageService.info( message, acao );
    }

    private handleError<S> (operation = 'operation', result?: S) {
      return (error: any): Observable<S> => {
        // TODO: send the error to remote logging infrastructure
        console.log('>>> Erro capturado...');
        console.error(error); // log to console instead
        // TODO: better job of transforming error for user consumption
        let msg: string;
        if ( error.status === 404) {
          msg = ' Não encontrado!';
        }
        this.log(`${operation.toUpperCase()}`, `${msg}`);
        // Let the app keep running by returning an empty result.
        return of(result as S);
      };
    }

  }

```

Abaixo exemplo de utilização:

```javascript
cliente.service.ts

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService, ConfigService, ResourceService } from '../../infra/security';

@Injectable({
  providedIn: 'root'
})
export class ClienteService extends ResourceService<Cliente> {

  constructor(
    httpClient: HttpClient,
    messageService: MessageService,
    configService: ConfigService
  ) {super( httpClient, configService.getApiUrl(), 'clientes', messageService ); }
}

export class Cliente {
  id: number;
  nome: string;
  endereco: string;
  cidade: string;
  telefone: string;
  email: string;
  situacao: Situacao;
  limiteSaldo: number;
}

export enum Situacao {
  Ativo,
  Inativo
}

```


## (7) Gerar Documentação

O comando abaixo gera documentação da app na pasta documentatioin.

```
npm run compodoc
```

<p align="center">
<img src="docs/doc01.png" width="800" alt="">
</p>


## Json-server
Json-server é servidor rest fake. Zero configuração.

```
npm install -g json-server
json-server --watch ./db.json
```

### Adicone as coleções em db.json

```javascript
  ...
  "clientes":[
    {"id":1,"nome":"cliente 001","endereco":"rua X","email":"email01@gmail.com","situcao":"Ativo","limiteSaldo":100000},
    {"id":2,"nome":"cliente 002","endereco":"rua Y","email":"email02@gmail.com","situcao":"Ativo","limiteSaldo":200000}
  ],
  "produtos":[
    {"id":1, "nome":"IPhone X", "preco":2000},
    {"id":1, "nome":"Galaxy 9", "preco":1000}
  ],
  "faturas":[
  ]
  ...
```

## Componentes

* https://material.angular.io/
* https://ng-select.github.io/ng-select#/data-sources
* https://formly-js.github.io/ngx-formly/

## Angular

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.0.8.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).
