package org.idomine.domain.crud.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idomine.domain.crud.model.vo.TipoArtefato;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
public class Artefato
{
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Projeto projeto;

    @Enumerated(EnumType.STRING)
    private TipoArtefato tipo;

    private String nome;
    private String resourceName;
    private String className;
    private String classFolder;
    private String templateTs;
    private String templateCss;
    private String templateHtml;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "artefato")
    private List<Elemento> elementos;

    public static List<Artefato> getFake()
    {
        List<Artefato> lista = new ArrayList<>();
        lista.add(
                Artefato.builder()
                        .id(1L)
                        .tipo(TipoArtefato.Crud)
                        .nome("Projetos")
                        .resourceName("projetos")
                        .className("Projeto")
                        .classFolder("projeto")
                        .elementos(Elemento.getFake1())
                        .build());
        lista.add(
                Artefato.builder()
                        .id(2L)
                        .tipo(TipoArtefato.Crud)
                        .nome("Artefatos")
                        .resourceName("artefatos")
                        .className("Artefato")
                        .classFolder("artefato")
                        .elementos(Elemento.getFake2())
                        .build());
        lista.add(
                Artefato.builder()
                        .id(3L)
                        .tipo(TipoArtefato.Crud)
                        .nome("Elementos")
                        .resourceName("elementos")
                        .className("Elemento")
                        .classFolder("elemento")
                        .elementos(Elemento.getFake3())
                        .build());

        lista.add(
                Artefato.builder()
                        .id(3L)
                        .tipo(TipoArtefato.Crud)
                        .nome("Configuração") // não pode usar config, pois já declarado no template
                        .resourceName("configuracao")
                        .className("Configuracao")
                        .classFolder("configuracao")
                        .elementos(Elemento.getFake4())
                        .build());
        lista.add(
                Artefato.builder()
                        .id(3L)
                        .tipo(TipoArtefato.Template)
                        .nome("Gerar App!") // não pode usar config, pois já declarado no template
                        .className("BuildApp")
                        .classFolder("buildapp")
                        .templateTs(template())
                        .templateCss(templateCSS())
                        .elementos(new ArrayList<>())
                        .build());

        return lista;
    }

    private static String templateCSS()
    {
        return ".centro {\r\n" +
                "  vertical-align: middle;\r\n" +
                "  align-items: center;\r\n" +
                "\r\n" +
                "}\r\n" + "";
    }

    private static String template()
    {
        return "import { ConfigService } from './../../infra/security/config.service';                                " +
                "import { Component, OnInit } from '@angular/core';                                                    " +
                "import { HttpClient, HttpHeaders } from '@angular/common/http';                                       " +
                "@Component({                                                                                          " +
                "  selector: 'app-buildapp',                                                                           " +
                "  styleUrls: ['./buildapp.component.css'],                                                            " +
                "  template: `                                                                                         " +
                "  <h2> NG builder </h2>                                                                               " +
                "  <p> Gerador de app.Será criado estrutura de fontend e backend a partir da pasta output. </p>        " +
                "  <form class=\"example-form\">                                                                         " +
                "                                                                                                      " +
                "  <mat-form-field style=\"min-width: 150px; max-width: 500px;width: 100%;\">                            "  +
                "    <input matInput placeholder=\"Projeto Id\" [(ngModel)]=\"id\" [ngModelOptions]=\"{standalone: true}\" > " +
                "  </mat-form-field>                                                                                   "  +
                "  </form>                                                                                             "  +
                "                                                                                                      "  +
                "  <button mat-button (click)='build()'> Gerar App !</button> <br/> {{app}} `,                         "  +
                "})                                                                                                    "  +
                "export class BuildAppComponent implements OnInit {                                                    "  +
                "                                                                                                      "  +
                "    app = '';                                                                                         "  +
                "    id = '';                                                                                          "  +
                "                                                                                                      "  +
                "    constructor(private httpClient: HttpClient, private  configService: ConfigService) { }            "  +
                "                                                                                                      "  +
                "    ngOnInit() {                                                                                      "  +
                "    }                                                                                                 "  +
                "                                                                                                      "  +
                "    build() {                                                                                         "  +
                "    this.httpClient.get<any>(                                                                         "  +
                "        this.configService.getApiUrl() + '/projetos/build/' + this.id, { observe: 'response' })       "  +
                "        .subscribe(resp => {                                                                          "  +
                "            this.app = 'resp';                                                                        "  +
                "        });                                                                                           "  +
                "    }                                                                                                 "  +
                "}";

    }

}
