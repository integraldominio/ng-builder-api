
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

package org.idomine.domain.crud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;
import org.idomine.domain.crud.service.helper.FormlyHelper;

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
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"artefato_id","nome"})})
public class Elemento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Artefato artefato;

    @Enumerated(EnumType.STRING)
    private TipoElemento tipoElemento;

    @Enumerated(EnumType.STRING)
    private TipoField tipoField;

    private boolean persistence;
    private String columnName;

    private boolean requerido;
    private Long minimo;
    private Long maximo;
    private Long linhas;

    private String artefatoFK;
    private String options;
    private String valueProp;
    private String labelProp;
    private String urlProp;
    private String resourceProp;

    private Long tamanho;
    private Long decimais;

    private String nome;
    private String rotulo;
    private String inicial;
    private String mascara;
    private String pipe;
    private String dica;

    private boolean showcolumn;
    private Long ordenation;

    private Long fieldGroup;
    private Long fieldSize;
    
    private boolean uniqueKey;
    private Long uniqueGroup;

    public boolean newGroup()
    {
        boolean multi = "multi".equals(artefato.getCrudEstilo());
        if (multi)
        {
            Elemento el = artefato.getElementos().stream().filter(e -> e.getFieldGroup().equals(this.getFieldGroup())).findFirst().get();
            return el.getNome().equals(this.getNome());
        }
        return false;
    }

    public boolean lastGroup()
    {
        boolean multi = "multi".equals(artefato.getCrudEstilo());
        if (multi)
        {
            Elemento el = artefato.getElementos().stream().filter(e -> e.getFieldGroup().equals(this.getFieldGroup())).reduce((a, b) -> b).get();
            return el.getNome().equals(this.getNome());
        }
        return false;
    }

    public boolean isNotFirstElemento()
    {
        return !artefato.getElementos().get(0).getNome().equals(this.getNome());
    }

    public boolean isTipoString()
    {
        return TipoField.String.equals(tipoField);
    }

    public String restricoes()
    {
        String restricoes = "";
        if (requerido)
        {
            if (minimo != null)
            {
                restricoes = isTipoString() ? "minLength: " + minimo : "min: " + minimo;
                restricoes = restricoes + ",";
            }

            if (maximo != null)
            {
                restricoes = restricoes.equals("") ? "" : "; ";
                restricoes = isTipoString() ? "maxLength: " + maximo : "max: " + maximo;
                restricoes = restricoes + ",";
            }
        }
        return restricoes;
    }

    public String tipoAngular()
    {
        return TipoElemento.SelectDB.equals(tipoElemento) ? nome : TipoField.angular(getTipoField()).toString();
    }

    public String tipoJava()
    {
        return TipoElemento.SelectDB.equals(tipoElemento) ? nome : tipoField.toString();
    }

    public boolean toForm()
    {
        return TipoElemento.Input.equals(tipoElemento) ||
                TipoElemento.TextArea.equals(tipoElemento) ||
                TipoElemento.Autocomplete.equals(tipoElemento) ||
                TipoElemento.Checkbox.equals(tipoElemento) ||
                TipoElemento.ButtonToggle.equals(tipoElemento) ||
                TipoElemento.Select.equals(tipoElemento) ||
                TipoElemento.SelectDB.equals(tipoElemento) ||
                TipoElemento.Datepicker.equals(tipoElemento) ||
                TipoElemento.RadioButton.equals(tipoElemento);
    }

    public String toFormly()
    {
        return FormlyHelper.toFormly(this);
    }

    public String requiredToString()
    {
        return requerido ? "true" : "false";
    }

    public boolean notSelectDB()
    {
        return !selectDB();
    }

    public boolean selectDB()
    {
        return TipoElemento.SelectDB.equals(tipoElemento);
    }

    public boolean hasOptions()
    {
        return options == null || options.trim().length() == 0 ? false : true;
    }

    public Artefato artfatoIrmao()
    {
        Artefato irmao = null;
        if (artefato.getProjeto() != null && artefato.getProjeto().getArtefatos() != null)
            artefato.getProjeto()
                    .getArtefatos()
                    .stream()
                    .filter(a -> a.getNome().equals(this.getNome()) && !a.getId().equals(this.getId()))
                    .collect(Collectors.toList());
        return irmao;
    }

    public String getDefault()
    {
        if (TipoField.BigDecimal.equals(tipoField) || TipoField.BigInteger.equals(tipoField)
                || TipoField.Integer.equals(tipoField) || TipoField.Long.equals(tipoField))
        {
            return inicial;
        }
        else if (TipoElemento.Input.equals(tipoElemento) || TipoElemento.TextArea.equals(tipoElemento)
                || TipoElemento.Select.equals(tipoElemento))
        {
            return "'" + inicial + "'";
        }
        return inicial;
    }

    public static List<Elemento> getFake0()
    {
        List<Elemento> lista = new ArrayList<>();

        // portal
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .rotulo("Nome")
                        .requerido(true)
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());
        // portal
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("descricao")
                        .rotulo("descricao")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());
        // portal
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("templateTs")
                        .rotulo("template Ts")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(200L)
                        .linhas(3L)
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(false)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        // portal
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("templateCss")
                        .rotulo("template Css")
                        .tamanho(200L)
                        .minimo(1L)
                        .maximo(200L)
                        .linhas(3L)
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(false)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());
        // portal
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("templateHtml")
                        .rotulo("template Html")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(200L)
                        .linhas(3L)
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(false)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());

        return lista;
    }

    public static List<Elemento> getFake1()
    {
        List<Elemento> lista = new ArrayList<>();

        // projeto
        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.SelectDB)
                        .valueProp("id")
                        .labelProp("nome")
                        .options("portais")
                        .tipoField(TipoField.String)
                        .nome("Portal")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Portal")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .rotulo("Nome")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(2L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("descricao")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Descrição")
                        .inicial("Sistema muito legal, gerado com o ngx-buider")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(6L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nomeBackendApp")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Nome Backend App")
                        .inicial("app-backend")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(8L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nomeFrontEndApp")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Nome Frontend App")
                        .inicial("app-frontend")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(8L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("iconeApp")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Ícone App")
                        .inicial("/assets/icon.svg")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(8L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("imageApp")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Image App")
                        .inicial("assets/back-image.svg")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("serverLang")
                        .rotulo("Server Language")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .requerido(true)
                        .inicial("java")
                        .persistence(true)
                        .showcolumn(true)
                        .options(
                                "[{ value: 'java', label: 'java'}, {value: 'js', label: 'js'}]")
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(3L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("serverHost")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Server Host")
                        .inicial("localhost")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(4L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("serverPort")
                        .tamanho(4L)
                        .minimo(999L)
                        .maximo(9999L)
                        .rotulo("Server port")
                        .inicial("3000")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(4L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("frontHost")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Front Host")
                        .inicial("localhost")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(5L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("frontPort")
                        .tamanho(4L)
                        .minimo(999L)
                        .maximo(99990L)
                        .rotulo("Front port")
                        .inicial("5000")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(5L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("useLogin")
                        .tamanho(1L)
                        .rotulo("Use Login")
                        .inicial("true")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(5L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(5L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("useRoles")
                        .tamanho(1L)
                        .inicial("true")
                        .rotulo("Use Roles")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(5L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(5L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("outputDirectory")
                        .rotulo("Output Directory")
                        .inicial("sistemax")
                        .requerido(true)
                        .minimo(1L)
                        .maximo(100L)
                        .persistence(true)
                        .tamanho(100L)
                        .showcolumn(true)
                        .fieldGroup(6L)
                        .fieldSize(1L)
                        .build());

        return lista;
    }

    // Artefato
    public static List<Elemento> getFake2()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.SelectDB)
                        .valueProp("id")
                        .labelProp("nome")
                        .options("artefatos")
                        .tipoField(TipoField.String)
                        .nome("Projeto")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Projeto")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(false)
                        .fieldGroup(1L)
                        .fieldSize(1L)

                        .build());
        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("tipo")
                        .rotulo("Tipo")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .options(
                                "[{ value: 'Crud', label: 'Crud'}, {value: 'MasterDetail', label: 'MasterDetail'}, {value: 'Template', label: 'Template'}, {value: 'Dialogo', label: 'Dialogo'}, {value: 'Report', label: 'Report'}, {value: 'Grafico', label: 'Grafico'}]")
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Nome")
                        .inicial("Catálogo de Peças")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(10L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("resourceName")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(501L)
                        .rotulo("Resource Name")
                        .inicial("catalogos")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        
        lista.add(
                Elemento.builder()
                        .id(11L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("className")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Class Name")
                        .inicial("Catalogo")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("classFolder")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Class Folder")
                        .inicial("catalogo")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(9L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("crudEstilo")
                        .rotulo("Estilo Entrada de Dados")
                        .tamanho(50L)
                        .inicial("single")
                        .requerido(true)
                        .persistence(true)
                        .options(
                                "[{ value: 'single', label: 'Coluna Simples'}, {value: 'multi', label: 'Coluna Múltipla'}]")
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("paginaHome")
                        .tamanho(1L)
                        .rotulo("Página Home")
                        .requerido(false)
                        .persistence(true)
                        .showcolumn(false)
                        .inicial("false")
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("templateTs")
                        .tamanho(999L)
                        .minimo(1L)
                        .maximo(999L)
                        .rotulo("Template Ts")
                        .requerido(false)
                        .persistence(true)
                        .linhas(3L)
                        .fieldGroup(5L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("templateHtml")
                        .tamanho(999L)
                        .minimo(1L)
                        .maximo(999L)
                        .rotulo("Template Html")
                        .requerido(false)
                        .persistence(true)
                        .linhas(3L)
                        .fieldGroup(6L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("templateCss")
                        .tamanho(999L)
                        .minimo(1L)
                        .maximo(999L)
                        .rotulo("Template Css")
                        .requerido(false)
                        .persistence(true)
                        .linhas(3L)
                        .fieldGroup(7L)
                        .fieldSize(1L)
                        .build());

        return lista;
    }

    // Elemento
    public static List<Elemento> getFake3()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.SelectDB)
                        .tipoField(TipoField.String)
                        .valueProp("id")
                        .labelProp("nome")
                        .options("artefatos")
                        .nome("Artefato")
                        .tamanho(50L)
                        .rotulo("Artefato")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("tipoElemento")
                        .tamanho(10L)
                        .rotulo("Tipo Elemento")
                        .requerido(true)
                        .persistence(true)
                        .options(
                                "[{ value: 'Autocomplete', label: 'Autocomplete'} , {value: 'Checkbox', label: 'Checkbox'}, {value: 'Chips', label: 'Chips'} ,"
                                        +
                                        " { value: 'Datepicker', label: 'Datepicker'} , {value: 'Input', label: 'Input'} , {value: 'RadioButton', label: 'RadioButton'} ,"
                                        +
                                        " { value: 'RadioButton', label: 'RadioButton'} , { value: 'Select', label: 'Select'} , {value: 'SelectMultiple', label: 'SelectMultiple'} , {value:'SelectDB', label: 'SelectDB' } ,"
                                        +
                                        " { value: 'Slidetoggle', label: 'Slidetoggle'} , {value: 'TextArea', label: 'TextArea'} , {value: 'Tooltip', label: 'Tooltip' }] ")

                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("tipoField")
                        .tamanho(10L)
                        .rotulo("Tipo Field")
                        .requerido(true)
                        .persistence(true)
                        .options(
                                // "[{ value: 'BigDecimal', label: 'BigDecimal'}, {value: 'BigInteger', label:
                                // 'BigInteger'}, {value: 'Binario', label: 'Binario'}, {value: 'Boolean', label:
                                // 'Boolean'},"
                                "[ {value: 'Boolean', label: 'Boolean'},"
                                        +
                                        " {value: 'Date', label: 'Date'} , {value: 'DateTime', label: 'DateTime'} ,  {value: 5, label: 'Decimal'} ,  {value: 'Integer', label: 'Integer'} ,"
                                        +
                                        " {value: 'Long', label: 'Long'} , {value: 'NotAvailable', label: 'NotAvailable'} , {value: 'String', label: 'String'} , {value: 'Time', label: 'Time'}]")
                        .fieldGroup(1L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Nome")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(14L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("rotulo")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Rótulo")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(15L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("inicial")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Valor Inicial")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(2L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(16L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("mascara")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Máscara Edição")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(17L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("pipe")
                        .tamanho(50L)
                        .minimo(1L)
                        .maximo(50L)
                        .rotulo("Máscara Display")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(18L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("dica")
                        .tamanho(200L)
                        .maximo(200L)
                        .rotulo("Hint(dica)")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(3L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("tamanho")
                        .tamanho(20L)
                        .rotulo("Tamanho")
                        .requerido(true)
                        .persistence(true)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("min")
                        .tamanho(20L)
                        .rotulo("Mínimo")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("max")
                        .tamanho(20L)
                        .rotulo("Máximo")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(4L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("linhas")
                        .tamanho(20L)
                        .rotulo("Número Linhas")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(5L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.Long)
                        .nome("ordenation")
                        .tamanho(10L)
                        .rotulo("Ordem")
                        .persistence(true)
                        .requerido(false)
                        .fieldGroup(5L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("persistence")
                        .tamanho(1L)
                        .rotulo("Persistence")
                        .requerido(true)
                        .persistence(true)
                        .inicial("true")
                        .fieldGroup(6L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("requerido")
                        .tamanho(1L)
                        .rotulo("Requerido")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(false)
                        .inicial("false")
                        .fieldGroup(6L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("showcolumn")
                        .tamanho(20L)
                        .rotulo("Show Column")
                        .requerido(false)
                        .persistence(true)
                        .inicial("false")
                        .fieldGroup(6L)
                        .fieldSize(1L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("uniqueKey")
                        .tamanho(20L)
                        .rotulo("Chave Única (UK)")
                        .requerido(false)
                        .persistence(true)
                        .inicial("false")
                        .fieldGroup(6L)
                        .fieldSize(1L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("valueProp")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Value Prop")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(7L)
                        .fieldSize(1L)
                        .linhas(3L)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("labelProp")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Label Prop")
                        .requerido(false)
                        .persistence(true)
                        .fieldGroup(8L)
                        .fieldSize(1L)
                        .linhas(3L)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(13L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("options")
                        .tamanho(999L)
                        .minimo(1L)
                        .maximo(999L)
                        .rotulo("Opções")
                        .persistence(true)
                        .requerido(false)
                        .fieldGroup(9L)
                        .fieldSize(1L)
                        .linhas(3L)
                        .build());


        return lista;

    }

    // Config
    public static List<Elemento> getFake4()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(23L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("nomeEmpresa")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Nome Empresa")
                        .requerido(true)
                        .persistence(true)
                        .showcolumn(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(23L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("siteEmpresa")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Site Empresa")
                        .requerido(false)
                        .persistence(true)
                        .showcolumn(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(25L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("emailEmpresa")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Email Empresa")
                        .requerido(false)
                        .persistence(true)
                        .showcolumn(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(26L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("outputDirectory")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Output Directory")
                        .requerido(false)
                        .persistence(true)
                        .showcolumn(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(27L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("appProperties")
                        .tamanho(100L)
                        .minimo(1L)
                        .maximo(100L)
                        .rotulo("Application Properties")
                        .requerido(false)
                        .persistence(true)
                        .showcolumn(true)
                        .build());

        return lista;
    }

    // Tipo Elemento Component Formly
    public static List<Elemento> getFake5()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .id(28L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Input)
                        .tipoField(TipoField.String)
                        .nome("campoText")
                        .tamanho(100L)
                        .showcolumn(true)
                        .rotulo("Campo Texto")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(28L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.TextArea)
                        .tipoField(TipoField.String)
                        .nome("campoTextArea")
                        .tamanho(100L)
                        .showcolumn(true)
                        .rotulo("Campo Text Area")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(29L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Datepicker)
                        .tipoField(TipoField.Date)
                        .nome("campoData")
                        .tamanho(100L)
                        .showcolumn(true)
                        .rotulo("Campo Data")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(29L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Checkbox)
                        .tipoField(TipoField.Boolean)
                        .nome("campoCheckbox")
                        .tamanho(100L)
                        .rotulo("Campo Checkbox")
                        .persistence(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(30L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.Select)
                        .tipoField(TipoField.String)
                        .nome("campoSelectOne")
                        .tamanho(100L)
                        .rotulo("Campo SelectOne")
                        .options(
                                "[{ value: 1, label: 'Option 1'}, {value: 2, label: 'Option 2'}, {value: 3, label: 'Option 3'}]")
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(31L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.RadioButton)
                        .tipoField(TipoField.String)
                        .nome("campoRadioGroup")
                        .tamanho(100L)
                        .rotulo("Campo RadioGroup")
                        .options(
                                "[{ value: 1, label: 'Option 1'}, {value: 2, label: 'Option 2'}, {value: 3, label: 'Option 3'}]")
                        .persistence(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(32L)
                        .artefato(Artefato.builder().id(5L).build())
                        .tipoElemento(TipoElemento.ButtonToggle)
                        .tipoField(TipoField.String)
                        .nome("campoToggle")
                        .tamanho(100L)
                        .rotulo("Campo Toggle")
                        .persistence(true)
                        .build());

        return lista;
    }

}
