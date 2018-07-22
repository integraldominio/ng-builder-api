package org.idomine.domain.crud.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.idomine.domain.crud.model.vo.TipoElemento;
import org.idomine.domain.crud.model.vo.TipoField;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
public class Elemento
{
    @Id
    @GeneratedValue
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Artefato artefato;

    @Enumerated(EnumType.STRING)
    private TipoElemento tipoElemento;

    @Enumerated(EnumType.STRING)
    private TipoField tipoField;
    private long tamanho;
    private long decimais;

    private String nome;
    private String rotulo;
    private String inicial;
    private String mascara;
    private String pipe;
    private String dica;

    private boolean requerido;

    public String tipoAngular()
    {
        return TipoField.angular(getTipoField());
    }

    public static List<Elemento> getFake1()
    {
        List<Elemento> lista = new ArrayList<>();

        // projeto
        lista.add(
                Elemento.builder()
                        .id(1L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .rotulo("Nome")
                        .tamanho(100L)
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(2L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("descricao")
                        .tamanho(100L)
                        .rotulo("Descrição")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(3L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("serverHost")
                        .tamanho(100L)
                        .rotulo("Server Host")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(4L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.Long)
                        .nome("serverPort")
                        .tamanho(4L)
                        .rotulo("Server port")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(4L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("frontHost")
                        .tamanho(100L)
                        .rotulo("front Host")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(5L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.Long)
                        .nome("frontPort")
                        .tamanho(4L)
                        .rotulo("Front port")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(6L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nomeBackendApp")
                        .tamanho(100L)
                        .rotulo("Nome Backend App")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(8L)
                        .artefato(Artefato.builder().id(1L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nomeFrontEndApp")
                        .tamanho(100L)
                        .rotulo("Nome Frontend App")
                        .requerido(true)
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
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .tamanho(50L)
                        .rotulo("Nome")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(10L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("resourceName")
                        .tamanho(50L)
                        .rotulo("Resource Name")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(11L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("className")
                        .tamanho(50L)
                        .rotulo("Class Name")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("classFolder")
                        .tamanho(50L)
                        .rotulo("Class Folder")
                        .requerido(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("template")
                        .tamanho(1000L)
                        .rotulo("TemplateTs")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("template")
                        .tamanho(1000L)
                        .rotulo("TemplateHtml")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(12L)
                        .artefato(Artefato.builder().id(2L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("template")
                        .tamanho(1000L)
                        .rotulo("TemplateCss")
                        .requerido(true)
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
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .tamanho(50L)
                        .rotulo("Nome")
                        .requerido(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(14L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("rotulo")
                        .tamanho(50L)
                        .rotulo("Rótulo")
                        .requerido(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(15L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("inicial")
                        .tamanho(50L)
                        .rotulo("Valor Inicial")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(16L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("mascara")
                        .tamanho(100L)
                        .rotulo("Máscara Edição")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(17L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("pipe")
                        .tamanho(50L)
                        .rotulo("Máscara Display")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(18L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("dica")
                        .tamanho(200L)
                        .rotulo("Hint(dica)")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(19L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.Long)
                        .nome("tamanho")
                        .tamanho(20L)
                        .rotulo("Tamanho")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(20L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.Long)
                        .nome("Decimais")
                        .tamanho(20L)
                        .rotulo("Deciamais")
                        .requerido(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(21L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("tipoElemento")
                        .tamanho(50L)
                        .rotulo("Tipo Elemento")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .id(22L)
                        .artefato(Artefato.builder().id(3L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("tipoField")
                        .tamanho(50L)
                        .rotulo("Tipo Field")
                        .requerido(true)
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
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nomeEmpresa")
                        .tamanho(100L)
                        .rotulo("Nome Empresa")
                        .requerido(true)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(23L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("siteEmpresa")
                        .tamanho(100L)
                        .rotulo("Site Empresa")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(25L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("emailEmpresa")
                        .tamanho(100L)
                        .rotulo("Email Empresa")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(26L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("outputDirectory")
                        .tamanho(100L)
                        .rotulo("Output Directory")
                        .requerido(false)
                        .build());

        lista.add(
                Elemento.builder()
                        .id(27L)
                        .artefato(Artefato.builder().id(4L).build())
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("appProperties")
                        .tamanho(100L)
                        .rotulo("Application Properties")
                        .requerido(false)
                        .build());

        return lista;
    }
    
}
