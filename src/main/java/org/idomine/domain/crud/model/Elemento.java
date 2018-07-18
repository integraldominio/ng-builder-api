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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Elemento
{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Artefato artefato;
    @Enumerated(EnumType.STRING)
    private TipoElemento tipoElemento;
    @Enumerated(EnumType.STRING)
    private TipoField tipoField;;
    
    private String name;
    private String label;
    private String initial;
    private String mask;
    private String pipe;
    private String hint;
    
}
