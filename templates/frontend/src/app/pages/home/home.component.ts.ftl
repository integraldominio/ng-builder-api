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
import { VERSION } from '@angular/material';

<#list projeto.artefatos as a >
<#if a.tipo =='Crud' >
import { ${a.className}Service } from '../../erp/${a.classFolder}/${a.classFolder}.service';
</#if>
</#list>

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  version = VERSION;
  caminho = 'Home';

  <#list projeto.artefatos as a >
  <#if a.tipo =='Crud' > 
  n${a.className}: any;
  </#if>
  </#list>

  dashCard = [];

  constructor(
    <#list projeto.artefatos as a >
    <#if a.tipo =='Crud' > 
    private ${a.className?uncap_first}Service: ${a.className}Service,
    </#if>
    </#list>
  ) {}

  ngOnInit() {
    <#list projeto.artefatos as a >
    <#if a.tipo =='Crud' > 
    this.count${a.className}();
    </#if>
    </#list>
  }

  <#list projeto.artefatos as a >
  <#if a.tipo =='Crud' >
  count${a.className}() {
    this.${a.className?uncap_first}Service.count().subscribe(
      data => {
        this.n${a.className} = data;
        <#if a.lastCrud() >
        this.updateDash();
        </#if>
      }
    );
  }
  </#if>
  </#list>

  updateDash() {
    this.dashCard = [
    <#list projeto.artefatos as a >
    <#if a.tipo =='Crud' > 
      { colorDark: '#5C6BC0', colorLight: '#64B5F6', number: this.n${a.className}, title: '${a.nome}', icon: 'apps' },
    </#if>
    </#list>
    ];
  }

}

<#-- 

  updateDash() {
    this.dashCard = [
      { colorDark: '#5C6BC0', colorLight: '#7986CB', number: this.nPortal, title: 'Portais', icon: 'apps' },
      { colorDark: '#42A5F5', colorLight: '#64B5F6', number: this.nProjeto , title: 'Projetos', icon: 'apps' },
      { colorDark: '#E91E63', colorLight: '#FF4081', number: this.nArtefato, title: 'Artefatos', icon: 'apps' }
    ];
  }

 --> 
