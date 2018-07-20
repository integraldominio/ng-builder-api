package org.idomine.domain.crud.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter; 
import lombok.Setter;

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
    
    @Trasient
    private String situacaoToString;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private DateTime registerDate;
    
    
}
