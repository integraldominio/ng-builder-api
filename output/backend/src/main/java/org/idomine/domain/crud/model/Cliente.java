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
public class Cliente
{
    @Id
    @GeneratedValue
    private Long id;
    
    @Comlumn(length=100)
    private String nome;
    
    @Comlumn(length=50)
    private String email;
    
    @Comlumn(length=20)
    private String telefone;
    
    @Comlumn(length=1)
    private String situacao;
    
    @Trasient
    private String situacaoToString;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Comlumn
    private DateTime registerDate;
    
    
}
