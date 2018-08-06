package org.idomine.domain.crud.model;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idomine.domain.crud.model.vo.TipoArtefato;
import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;
import org.idomine.domain.crud.service.helper.FolderHelper;
import org.idomine.domain.crud.service.helper.TemplatePathHelper;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Projeto projeto;

    @Enumerated(EnumType.STRING)
    private TipoArtefato tipo;
    private String nome;

    private String resourceName;
    private String className;
    private String classFolder;

    private boolean paginaHome;
    private String crudEstilo;

    private String templateTs;
    private String templateCss;
    private String templateHtml;

    private String templateBeforeInsert;
    private String templateBeforeUpdate;
    private String templateBeforeDelete;
    private String templateAfterInsert;
    private String templateAfterUpdate;
    private String templateAfterDelete;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "artefato")
    private List<Elemento> elementos;

    public boolean sigleColumn()
    {
        return "single".equals(crudEstilo);
    }

    public boolean multiColumn()
    {
        return "multi".equals(crudEstilo);
    }

    public boolean hasDateType()
    {
        boolean iDate = false;

        if (getElementos() != null)
            for (Elemento e : getElementos())
            {
                iDate = (e.getTipoField() == TipoField.Date || e.getTipoField() == TipoField.Time
                        || e.getTipoField() == TipoField.DateTime);
                if (iDate)
                    break;
            }
        return iDate;
    }

    public boolean hasTransientType()
    {
        boolean transientField = false;

        if (getElementos() != null)
            for (Elemento e : getElementos())
            {
                transientField = !e.isPersistence();
                if (transientField)
                    break;
            }
        return transientField;
    }

    public boolean hasSelectDB()
    {
        boolean selDb = false;

        if (getElementos() != null)
            for (Elemento e : getElementos())
            {
                selDb = TipoElemento.SelectDB.equals(e.getTipoElemento());
                if (selDb)
                    break;
            }
        return selDb;
    }

    public boolean lastCrud()
    {
        boolean res = false;
        if (projeto.getArtefatos() != null)
        {
            List<Artefato> lista = projeto.getArtefatos()
                    .stream()
                    .filter(a -> a.tipo.equals(TipoArtefato.Crud))
                    .collect(Collectors.toList());
            if (lista != null)
                return this.nome.equals(lista.stream().skip(lista.size() - 1).findFirst().get().getNome());
        }
        return res;
    }

    public static List<Artefato> getFake()
    {
        List<Artefato> lista = new ArrayList<>();

        // portais
        Artefato portais = Artefato.builder()
                .id(1L)
                .tipo(TipoArtefato.Crud)
                .nome("Portais")
                .resourceName("portais")
                .className("Portal")
                .classFolder("portal")
                .elementos(Elemento.getFake0())
                .crudEstilo("multi")
                .build();
        portais.getElementos().forEach(e -> e.setArtefato(portais));
        lista.add(portais);

        // projetos
        Artefato projs = Artefato.builder()
                .id(1L)
                .tipo(TipoArtefato.Crud)
                .nome("Projetos")
                .resourceName("projetos")
                .className("Projeto")
                .classFolder("projeto")
                .elementos(Elemento.getFake1())
                .crudEstilo("multi")
                .build();
        projs.getElementos().forEach(e -> e.setArtefato(projs));
        lista.add(projs);

        // artafatos
        Artefato art = Artefato.builder()
                .id(2L)
                .tipo(TipoArtefato.Crud)
                .nome("Artefatos")
                .resourceName("artefatos")
                .className("Artefato")
                .classFolder("artefato")
                .elementos(Elemento.getFake2())
                .crudEstilo("multi")
                .build();
        art.getElementos().forEach(e -> e.setArtefato(art));
        lista.add(art);

        // elementos
        Artefato els = Artefato.builder()
                .id(3L)
                .tipo(TipoArtefato.Crud)
                .nome("Elementos")
                .resourceName("elementos")
                .className("Elemento")
                .classFolder("elemento")
                .elementos(Elemento.getFake3())
                .build();
        els.getElementos().forEach(e -> e.setArtefato(els));
        lista.add(els);

        // config
        Artefato c = Artefato.builder()
                .id(3L)
                .tipo(TipoArtefato.Crud)
                .nome("Configuração") // não pode usar config, pois já declarado no template
                .resourceName("configuracoes")
                .className("Configuracao")
                .classFolder("configuracao")
                .elementos(Elemento.getFake4())
                .build();
        c.getElementos().forEach(e -> e.setArtefato(c));
        lista.add(c);

        // template
        Artefato t = Artefato.builder()
                .id(5L)
                .tipo(TipoArtefato.Template)
                .nome("Gerar App!") // não pode usar config, pois já declarado no template
                .className("BuildApp")
                .classFolder("buildapp")
                .templateTs(template())
                .templateCss(templateCSS())
                .elementos(new ArrayList<>())
                .build();
        t.getElementos().forEach(e -> e.setArtefato(t));
        lista.add(t);

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
        String o = "templates/" + TemplatePathHelper.FRONTEND_SRC_APP_SHARED;
        return FolderHelper.lerTemplate("templates/example/buildapp.component.ts");
    }

}
