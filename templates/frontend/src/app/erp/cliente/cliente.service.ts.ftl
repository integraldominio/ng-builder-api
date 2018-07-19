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

// usando json-server
// npm install -g json-server
// json-server --watch db.json
