package org.idomine.domain.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Projeto
{
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;
    private String localGeracaoApp;
    private String nomeApp;
    private String nomeReduzidoApp;
    private String iconeApp;
    private String backgroudApp;
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projeto")
    private List<Artefato> artefatos;
    private boolean useLogin;
    private boolean useRoles;
    
    private String serverPort;
    private String frontPort;
  
    
}
