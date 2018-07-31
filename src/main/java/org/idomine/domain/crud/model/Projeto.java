
/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.idomine.domain.crud.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @ManyToOne
    private Portal portal;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projeto")
    private List<Artefato> artefatos;

    public static Projeto getFake()
    {
        Projeto p = new Projeto.ProjetoBuilder()
                .id(1L)
                .nome("ngx-builder")
                .descricao("ngx-builder")
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
                .artefatos(Artefato.getFake())
                .build();

        p.artefatos.stream().forEach(a -> a.setProjeto(p));

        return p;
    }

}
