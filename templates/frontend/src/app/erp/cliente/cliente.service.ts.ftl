import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService, ConfigService, ResourceService } from '../../infra/security';

@Injectable({
  providedIn: 'root'
})
export class ${artefato.className}Service extends ResourceService<${artefato.className}> {

  constructor(
    httpClient: HttpClient,
    messageService: MessageService,
    configService: ConfigService
  ) {
      super(
      httpClient,
      configService.getApiUrl(),
      '${artefato.resourceName}',
      messageService);
  }
}

export class ${artefato.className} {
  id: number;
  <#list artefato.elementos as e >
  <#if e.tipoElemento == "Field" >
  ${e.nome}: ${e.tipoAngular()};
  </#if>
  </#list>
}

 

// usando json-server
// npm install -g json-server
// json-server --watch db.json
