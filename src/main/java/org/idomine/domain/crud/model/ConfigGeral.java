package org.idomine.domain.crud.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ConfigGeral
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
    
}
