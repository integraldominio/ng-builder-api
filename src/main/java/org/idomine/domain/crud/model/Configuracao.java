package org.idomine.domain.crud.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
        return new Configuracao
                .ConfiguracaoBuilder()
                .nomeEmpresa("Integraldominio")
                .emailEmpresa("integraldominio@gmail.com")
                .outputDirectory("output/")
                .build();
    }
    
}
