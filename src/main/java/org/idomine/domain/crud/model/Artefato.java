package org.idomine.domain.crud.model;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Artefato
{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Projeto projeto;
    @Enumerated(EnumType.STRING)
    private TipoArtefato tipo;
    private String nome;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "artefato")
    private List<Elemento> elementos;
}
