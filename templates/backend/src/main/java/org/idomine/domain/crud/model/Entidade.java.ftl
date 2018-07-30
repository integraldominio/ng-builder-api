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
<#if artefato.hasDateType() >
import java.util.Date;
</#if>

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<#if artefato.hasDateType() >
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
</#if>
<#if artefato.hasTransientType()>
import javax.persistence.Transient;
</#if>

<#if artefato.hasSelectDB() >
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    <#list artefato.elementos as el>
    <#if el.tipoElemento == "SelectDB">
    @JsonManagedReference
    @ManyToOne
    </#if>
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
    private ${el.tipoJava()} ${el.nome};
    
	</#list>
    
}
