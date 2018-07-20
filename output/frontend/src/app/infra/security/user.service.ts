import { Injectable } from '@angular/core';
import { MessageService } from './message.service';
import { ResourceService } from './resource.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConfigService } from './config.service';

@Injectable({
  providedIn: 'root'
})
export class UserService extends ResourceService<Post> {

  constructor(
    httpClient: HttpClient,
    messageService: MessageService,
    configService: ConfigService
  ) {
      super(
      httpClient,
      configService.getApiUrl(),
      'posts',
      messageService);
  }
}

export class Post {
  id: number;
  title: string;
  author: string;
}

// usando json-server
// npm install -g json-server
// json-server --watch db.json
