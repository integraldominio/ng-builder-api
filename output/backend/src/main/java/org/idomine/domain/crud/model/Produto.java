package org.idomine.domain.crud.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient; 

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
public class Produto
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(length=100)
    private String nome;
    
    @Column(length=50)
    private String email;
    
    @Column(length=20)
    private String telefone;
    
    @Column(length=1)
    private String situacao;
    
    @Transient
    private String situacaoToString;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date registerDate;
    
    
}
