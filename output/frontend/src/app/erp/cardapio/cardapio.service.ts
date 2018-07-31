import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MessageService, ConfigService, ResourceService } from '../../infra/security';

@Injectable({
  providedIn: 'root'
})
export class CardapioService extends ResourceService<Cardapio> {

  constructor(
    httpClient: HttpClient,
    messageService: MessageService,
    configService: ConfigService
  ) {
      super(
      httpClient,
      configService.getApiUrl(),
      'cardapios',
      messageService);
  }
}

export class Cardapio {
  id: number;
  nome: string;
  categoria: string;
  descricao: string;
  image_source: string;
  valor: number;
}

export enum Situacao {
  Ativo,
  Inativo
}

// usando json-server
// npm install -g json-server
// json-server --watch db.json
