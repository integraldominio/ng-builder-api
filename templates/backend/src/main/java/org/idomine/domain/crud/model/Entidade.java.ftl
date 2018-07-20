package org.idomine.domain.crud.model;

<#if import.ilist >
import java.util.List;
</#if>
<#if import.idate >
import java.util.Date;
</#if>

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
<#if import.idate >
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient; 
</#if>

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
public class ${artefato.className}
{
    @Id
    @GeneratedValue
    private Long id;
    
    <#list artefato.elementos as el>
    <#if el.tipoField == "DateTime">
    @Temporal(TemporalType.TIMESTAMP)
    </#if>
    <#if el.tipoField == "Date">
    @Temporal(TemporalType.DATE)
    </#if>
    <#if el.tipoField == "Time">
    @Temporal(TemporalType.TIME)
    </#if>
    <#if el.tipoElemento == "Transient">
    @Transient
    </#if>
    <#if el.tipoElemento == "Field">
    @Column<#if el.tamanho != 0 >(length=${el.tamanho})</#if>
    </#if>
    private ${el.tipoField} ${el.nome};
    
	</#list>
    
}
