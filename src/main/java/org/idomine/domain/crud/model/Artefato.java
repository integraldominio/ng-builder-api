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
                        .nome("Cad.Clientes")
                        .resourceName("clientes")
                        .className("Cliente")
                        .classFolder("cliente")
                        .elementos( Elemento.getFake())
                        .build());
        return lista;
    }

}
