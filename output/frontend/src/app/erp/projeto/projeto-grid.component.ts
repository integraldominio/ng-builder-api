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

import {
  Component,
  OnInit,
  AfterViewInit,
  ViewChild,
  ElementRef
} from "@angular/core";
import { ProjetoService, Projeto } from "./projeto.service";
import { MessageService } from "../../infra/security";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { MatTableDataSource } from "@angular/material/table";
import { FormBuilder, FormControl } from "@angular/forms";
import {
  QueryBuilderClassNames,
  QueryBuilderConfig
} from "angular2-query-builder";
import html2canvas from "html2canvas";
import * as jspdf from "jspdf";

import { PortalService } from "../portal/portal.service";

@Component({
  selector: "app-projeto-grid",
  templateUrl: "./projeto-grid.component.html",
  styleUrls: ["./projeto-grid.component.css"]
})
export class ProjetoGridComponent implements OnInit {
  public queryCtrl: FormControl;

  public bootstrapClassNames: QueryBuilderClassNames = {
    removeIcon: "fa fa-minus",
    addIcon: "fa fa-plus",
    button: "btn",
    buttonGroup: "btn-group",
    rightAlign: "order-12 ml-auto",
    switchRow: "d-flex px-2",
    switchGroup: "d-flex align-items-center",
    switchRadio: "custom-control-input",
    switchLabel: "custom-control-label",
    switchControl: "custom-control custom-radio custom-control-inline",
    row: "row p-2 m-1",
    rule: "border",
    ruleSet: "border",
    invalidRuleSet: "alert alert-danger",
    emptyWarning: "text-danger mx-auto",
    operatorControl: "form-control",
    operatorControlSize: "col-auto pr-0",
    fieldControl: "form-control",
    fieldControlSize: "col-auto pr-0",
    entityControl: "form-control",
    entityControlSize: "col-auto pr-0",
    inputControl: "form-control",
    inputControlSize: "col-auto"
  };

  public query = {
    condition: "and",
    rules: []
  };

  public config: QueryBuilderConfig = {
    fields: {
      age: { name: "Age", type: "number" },
      gender: {
        name: "Gender",
        type: "category",
        options: [{ name: "Male", value: "m" }, { name: "Female", value: "f" }]
      },
      name: { name: "Name", type: "string" },
      notes: { name: "Notes", type: "textarea", operators: ["=", "!="] },
      educated: { name: "College Degree?", type: "boolean" },
      birthday: {
        name: "Birthday",
        type: "date",
        operators: ["=", "<=", ">"],
        defaultValue: () => new Date()
      },
      school: { name: "School", type: "string", nullable: true },
      occupation: {
        name: "Occupation",
        type: "category",
        options: [
          { name: "Student", value: "student" },
          { name: "Teacher", value: "teacher" },
          { name: "Unemployed", value: "unemployed" },
          { name: "Scientist", value: "scientist" }
        ]
      }
    }
  };

  public currentConfig: QueryBuilderConfig;

  dataSource: MatTableDataSource<Projeto>;
  displayedColumns = [
    "id",
    "portal",
    "nome",
    "descricao",
    "serverLang",
    "databaseFlavor",
    "outputDirectory",
    "actions"
  ];

  @ViewChild("content", { static: false }) content: ElementRef;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  constructor(
    private formBuilder: FormBuilder,
    private projetoService: ProjetoService,
    private messageService: MessageService,
    private portalService: PortalService
  ) {
    this.queryCtrl = this.formBuilder.control(this.query);
    this.currentConfig = this.config;
  }

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
    this.projetoService.listAll().subscribe(data => {
      this.dataSource = new MatTableDataSource<Projeto>(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  deleteItem(o: Projeto) {
    this.projetoService.delete(o.id).subscribe(_ => this.listAll());
  }

  print() {
    const data = document.getElementById("convert");
    html2canvas(data).then(canvas => {
      const imgWidth = 208;
      const pageHeight = 295;
      const imgHeight = (canvas.height * imgWidth) / canvas.width;
      const heightLeft = imgHeight;
      const contentDataURL = canvas.toDataURL("image/png");
      const pdf = new jspdf("p", "mm", "a4");
      const position = 0;
      pdf.addImage(contentDataURL, "PNG", 0, position, imgWidth, imgHeight);
      pdf.save("Projetos.pdf");
    });
  }

  screenshot() {
    html2canvas(document.getElementById("convert")).then(function(canvas) {
      const generatedImage = canvas
        .toDataURL("image/png")
        .replace("image/png", "image/octet-stream");
      window.location.href = generatedImage;
    });
  }

  switchModes(event: Event) {}

  changeDisabled(event: Event) {
    (<HTMLInputElement>event.target).checked
      ? this.queryCtrl.disable()
      : this.queryCtrl.enable();
  }
}
