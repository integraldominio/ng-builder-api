package org.idomine.domain.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Projeto
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private String nomeBackendApp;
    private String nomeFrontendApp;
    private String iconeApp;
    private String imageApp;
    private String serverPort;
    private String serverHost;
    private String frontPort;
    private String frontHost;
    private boolean useLogin;
    private boolean useRoles;
    private String outputDirectory;
    
    @JsonBackReference
    @ManyToOne
    private Portal portal;
    
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projeto")
    private List<Artefato> artefatos;
    
    public static Projeto getFake()
    {
        return new Projeto
                    .ProjetoBuilder()
                    .id(1L)
                    .nome("ngbuilder")
                    .descricao("NG Builder")
                    .nomeBackendApp("app-back")
                    .nomeFrontendApp("app-front")
                    .iconeApp("icone.icon")
                    .imageApp("back.png")
                    .serverPort("3000")
                    .serverHost("http://localhost")
                    .frontPort("5000")
                    .frontHost("http://localhost")
                    .useLogin(false)
                    .useRoles(false)
                    .outputDirectory("output")
                    .artefatos( Artefato.getFake())
                    .build();
    }
    
}
