package org.idomine.domain.crud.model;

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
    private TipoField tipoField;;
    
    private String nome;
    private String rotulo;
    private String inicial;
    private String mascara;
    private String pipe;
    private String dica;
    
    private boolean requerido;
    
}
