package org.idomine.domain.crud.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idomine.domain.crud.model.vo.TipoArtefato;
import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;
import org.idomine.domain.crud.service.helper.GeradorCrudHelper;
import org.idomine.domain.crud.service.helper.TemplateBackendHelper;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    private Projeto projeto;

    @Enumerated(EnumType.STRING)
    private TipoArtefato tipo;
    private String nome;

    private String resourceName;
    private String className;
    private String classFolder;

    private boolean paginaHome;

    private String templateTs;
    private String templateCss;
    private String templateHtml;

    private String templateBeforeInsert;
    private String templateBeforeUpdate;
    private String templateBeforeDelete;
    private String templateAfterInsert;
    private String templateAfterUpdate;
    private String templateAfterDelete;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "artefato")
    private List<Elemento> elementos;

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

        lista.add(
                Artefato.builder()
                        .id(1L)
                        .tipo(TipoArtefato.Crud)
                        .nome("Portais")
                        .resourceName("portais")
                        .className("Portal")
                        .classFolder("portal")
                        .elementos(Elemento.getFake0())
                        .build());

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
                        .resourceName("configuracoes")
                        .className("Configuracao")
                        .classFolder("configuracao")
                        .elementos(Elemento.getFake4())
                        .build());

        // lista.add(
        // Artefato.builder()
        // .id(4L)
        // .tipo(TipoArtefato.Crud)
        // .nome("formly-js.github.io")
        // .resourceName("formly")
        // .className("Formly")
        // .classFolder("formly")
        // .elementos(Elemento.getFake5())
        // .build());

        lista.add(
                Artefato.builder()
                        .id(5L)
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

        String o = "templates/" + TemplateBackendHelper.FRONTEND_SRC_APP_SHARED;

        return GeradorCrudHelper.lerTemplate("templates/example/buildapp.component.ts");
    }

}
