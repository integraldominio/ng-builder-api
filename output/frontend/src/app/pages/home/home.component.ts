/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

import { Component, OnInit } from '@angular/core';
import { VERSION } from '@angular/material/core';

import { PortalService } from '../../erp/portal/portal.service';
import { ProjetoService } from '../../erp/projeto/projeto.service';
import { ArtefatoService } from '../../erp/artefato/artefato.service';
import { ElementoService } from '../../erp/elemento/elemento.service';
import { ConfiguracaoService } from '../../erp/configuracao/configuracao.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  version = VERSION;
  caminho = 'Home';

  nPortal: any;
  nProjeto: any;
  nArtefato: any;
  nElemento: any;
  nConfiguracao: any;

  dashCard = [];

  constructor(
    private portalService: PortalService,
    private projetoService: ProjetoService,
    private artefatoService: ArtefatoService,
    private elementoService: ElementoService,
    private configuracaoService: ConfiguracaoService,
  ) {}

  ngOnInit() {
    this.countPortal();
    this.countProjeto();
    this.countArtefato();
    this.countElemento();
    this.countConfiguracao();
  }

  countPortal() {
    this.portalService.count().subscribe(
      data => {
        this.nPortal = data;
        this.updateDash();
      }
    );
  }
  countProjeto() {
    this.projetoService.count().subscribe(
      data => {
        this.nProjeto = data;
        this.updateDash();
      }
    );
  }
  countArtefato() {
    this.artefatoService.count().subscribe(
      data => {
        this.nArtefato = data;
        this.updateDash();
      }
    );
  }
  countElemento() {
    this.elementoService.count().subscribe(
      data => {
        this.nElemento = data;
        this.updateDash();
      }
    );
  }
  countConfiguracao() {
    this.configuracaoService.count().subscribe(
      data => {
        this.nConfiguracao = data;
        this.updateDash();
      }
    );
  }

  updateDash() {
    this.dashCard = [
      { colorDark: '#5C6BC0', colorLight: '#64B5F6', number: this.nPortal, title: 'Portais', icon: 'apps' },
      { colorDark: '#5C6BC0', colorLight: '#64B5F6', number: this.nProjeto, title: 'Projetos', icon: 'apps' },
      { colorDark: '#5C6BC0', colorLight: '#64B5F6', number: this.nArtefato, title: 'Artefatos', icon: 'apps' },
      { colorDark: '#5C6BC0', colorLight: '#64B5F6', number: this.nElemento, title: 'Elementos', icon: 'apps' },
      { colorDark: '#5C6BC0', colorLight: '#64B5F6', number: this.nConfiguracao, title: 'Configuração', icon: 'apps' },
    ];
  }

}

