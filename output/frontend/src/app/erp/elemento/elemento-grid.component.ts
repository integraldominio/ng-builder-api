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
import { ElementoService, Elemento } from './elemento.service';
import { MessageService } from '../../infra/security';
import {MatPaginator, MatTableDataSource, MatSort } from '@angular/material';
import * as jspdf from 'jspdf';
import html2canvas from 'html2canvas';

import { ArtefatoService } from '../artefato/artefato.service';

@Component({
  selector: 'app-elemento-grid',
  templateUrl: './elemento-grid.component.html',
  styleUrls: ['./elemento-grid.component.css']
})
export class ElementoGridComponent implements OnInit {

  dataSource: MatTableDataSource<Elemento>;
  displayedColumns = [
  'nome',
  'rotulo',
  'actions'
  ];

  @ViewChild('content') content: ElementRef;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor (
    private elementoService: ElementoService,
    private messageService: MessageService,
    private artefatoService: ArtefatoService,
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
    this.elementoService.listAll().subscribe(
      data => {
        this.dataSource = new MatTableDataSource<Elemento>(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    );
  }

  deleteItem(o: Elemento) {
    this.elementoService.delete(o.id)
    .subscribe( _ => this.listAll() );
  }

  print()  {
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
      pdf.save('Elementos.pdf');
  });
  }

  screenshot() {
    html2canvas(document.getElementById('convert')).then(function(canvas) {
      const generatedImage = canvas.toDataURL( 'image/png' ).replace( 'image/png', 'image/octet-stream');
      window.location.href = generatedImage;
    });
  }

}
