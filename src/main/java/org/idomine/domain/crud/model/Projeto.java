package org.idomine.domain.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
    @GeneratedValue
    private Long id;
    private String nome;
    private String descricao;
    private String localGeracaoApp;
    private String nomeBackendApp;
    private String nomeFrontendApp;
    private String iconeApp;
    private String backgroudApp;
    private String serverPort;
    private String serverHost;
    private String frontPort;
    private String frontHost;
    private boolean useLogin;
    private boolean useRoles;
    private String outputDirectory;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projeto")
    private List<Artefato> artefatos;

    
    public static Projeto getFake()
    {
        return new Projeto
                    .ProjetoBuilder()
                    .id(1L)
                    .nome("ngbuilder")
                    .descricao("NG Builder")
                    .localGeracaoApp("C:\\temp\\ng-builder")
                    .nomeBackendApp("app-back")
                    .nomeFrontendApp("app-front")
                    .iconeApp("icone.icon")
                    .backgroudApp("back.png")
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
