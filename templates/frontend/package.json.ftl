{
  "name": "${projeto.nome}",
  "descriptioin": "${projeto.descricao}",
  "version": "${ngxbuilder}",
  "scripts": {
    "ng": "ng",
    "start": "ng serve",
    "build": "ng build",
    "test": "ng test",
    "lint": "ng lint",
    "e2e": "ng e2e",
    "compodoc": "./node_modules/.bin/compodoc -p src/tsconfig.app.json"
  },
  "private": true,
  "dependencies": {
    "@angular/animations": "^6.1.0",
    "@angular/cdk": "^6.4.1",
    "@angular/common": "^6.1.1",
    "@angular/compiler": "^6.1.1",
    "@angular/core": "^6.1.1",
    "@angular/flex-layout": "^6.0.0-beta.16",
    "@angular/forms": "^6.1.1",
    "@angular/http": "^6.1.1",
    "@angular/material": "^6.4.1",
    "@angular/platform-browser": "^6.1.1",
    "@angular/platform-browser-dynamic": "^6.1.1",
    "@angular/router": "^6.1.1",
    "@ngx-formly/core": "^4.6.6",
    "@ngx-formly/material": "^4.6.6",
    "@ngx-progressbar/core": "^5.0.1",
    "@ngx-progressbar/http": "^5.0.1",
    "bcrypt": "^3.0.0",
    "core-js": "^2.5.4",
    "hammerjs": "^2.0.8",
    "html2canvas": "^1.0.0-alpha.12",
    "jspdf": "^1.4.1",
    "ng2-odometer": "^1.1.3",
    "ngx-image-cropper": "^1.0.1",
    "ngx-img-cropper": "^0.10.4",
    "rxjs": "^6.2.2",
    "zone.js": "~0.8.26"
  },
  "devDependencies": {
    "@angular-devkit/build-angular": "~0.7.0",
    "@angular/cli": "~6.1.1",
    "@angular/compiler-cli": "^6.1.0",
    "@angular/language-service": "^6.1.0",
    "@types/jasmine": "~2.8.6",
    "@types/jasminewd2": "~2.0.3",
    "@types/node": "~8.9.4",
    "codelyzer": "~4.2.1",
    "compodoc": "0.0.41",
    "jasmine-core": "~2.99.1",
    "jasmine-spec-reporter": "~4.2.1",
    "karma": "~1.7.1",
    "karma-chrome-launcher": "~2.2.0",
    "karma-coverage-istanbul-reporter": "~2.0.0",
    "karma-jasmine": "~1.1.1",
    "karma-jasmine-html-reporter": "^0.2.2",
    "protractor": "~5.3.0",
    "ts-node": "~5.0.1",
    "tslint": "~5.9.1",
    "typescript": "~2.7.2"
  }
}
