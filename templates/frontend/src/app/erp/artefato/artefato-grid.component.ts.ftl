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

import { Component, OnInit, AfterViewInit,  ViewChild, ElementRef } from '@angular/core';
import { ${artefato.className}Service, ${artefato.className} } from './${artefato.classFolder}.service';
import { MessageService } from '../../infra/security';
import {MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import * as jspdf from 'jspdf';
import html2canvas from 'html2canvas';

<#list artefato.elementos as e >
<#if e.selectDB() >
import { ${e.nome}Service } from '../${e.nome?lower_case}/${e.nome?lower_case}.service';
</#if>
</#list>

@Component({
  selector: 'app-${artefato.classFolder}-grid',
  templateUrl: './${artefato.classFolder}-grid.component.html',
  styleUrls: ['./${artefato.classFolder}-grid.component.css']
})
export class ${artefato.className}GridComponent implements OnInit {

  dataSource: MatTableDataSource<${artefato.className}>;
  displayedColumns = [
  <#list artefato.elementos as e >
  <#if e.showcolumn>
  '${e.nome?uncap_first}',
  </#if>
  </#list> 
  'actions'
  ];

  @ViewChild('content') content: ElementRef;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor (
    private ${artefato.classFolder}Service: ${artefato.className}Service,
    private messageService: MessageService,
	<#list artefato.elementos as e >
    <#if e.selectDB() >
    private ${e.nome?lower_case}Service: ${e.nome}Service,
	</#if>
	</#list>    
  ) { }

  ngOnInit() {
    this.listAll();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  listAll() {
    this.${artefato.classFolder}Service.listAll().subscribe(
      data => {
        this.dataSource = new MatTableDataSource<${artefato.className}>(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    );
  }

  deleteItem(o: ${artefato.className}) {
    this.${artefato.classFolder}Service.delete(o.id)
    .subscribe( _ => this.listAll() );
  }
  
  public print()  {
    const data = document.getElementById('convert');
    html2canvas(data).then( canvas => {
      const imgWidth = 208;
      const pageHeight = 295;
      const  imgHeight = canvas.height * imgWidth / canvas.width;
      const  heightLeft = imgHeight;
      const  contentDataURL = canvas.toDataURL('image/png');
      const  pdf = new jspdf('p', 'mm', 'a4');
      const  position = 0;
      pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
      pdf.save('MYPdf.pdf');
  });
  }
  
}
