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

    public static List<Elemento> getFake()
    {
        List<Elemento> lista = new ArrayList<>();

        lista.add(
                Elemento.builder()
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("nome")
                        .rotulo("Nome")
                        .tamanho(100L)
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("email")
                        .tamanho(50L)
                        .rotulo("e-mail")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("telefone")
                        .tamanho(20L)
                        .rotulo("Telefone")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.String)
                        .nome("situacao")
                        .tamanho(1L)
                        .rotulo("Situacao")
                        .inicial("A")
                        .requerido(true)
                        .build());
        lista.add(
                Elemento.builder()
                        .tipoElemento(TipoElemento.Transient)
                        .tipoField(TipoField.String)
                        .nome("situacaoToString")
                        .tamanho(15L)
                        .rotulo("Situacao")
                        .inicial("")
                        .requerido(false)
                        .build());
        lista.add(
                Elemento.builder()
                        .tipoElemento(TipoElemento.Field)
                        .tipoField(TipoField.Date)
                        .nome("registerDate")
                        .tamanho(0L)
                        .rotulo("Data Registro")
                        .inicial("")
                        .requerido(false)
                        .build());        
        return lista;
    }

}
