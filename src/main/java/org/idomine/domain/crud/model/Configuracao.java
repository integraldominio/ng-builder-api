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

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = { "id" })
public class Configuracao
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpresa;
    private String siteEmpresa;
    private String emailEmpresa;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] backTemplatePom;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] backTemplateEntity;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] backTemplateVo;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] backTemplateRepository;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] backTemplateResource;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] frontTemplateModel;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] frontTemplateCrud;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] frontTemplatCrudeService;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] frontTemplateMenu;

    private String backClassesHelper;
    private String frontClassesHelper;

    private String appProperties;
    private String outputDirectory;

    public static Configuracao getFake()
    {
        return new Configuracao.ConfiguracaoBuilder()
                .nomeEmpresa("Integraldominio")
                .emailEmpresa("integraldominio@gmail.com")
                .outputDirectory("output/")
                .build();
    }

}
