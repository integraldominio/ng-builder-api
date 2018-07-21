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
  ) {
      super(
      httpClient,
      configService.getApiUrl(),
      'clientes',
      messageService);
  }
}

export class Cliente {
  id: number;
  nome: string;
  email: string;
  telefone: string;
  situacao: string;
  registerDate: date;
}

 

// usando json-server
// npm install -g json-server
// json-server --watch db.json
