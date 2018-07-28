package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Configuracao;
import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.reporitory.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ConfiguracaooResource
{
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;

    @GetMapping("/configuracoes")
    public Iterable<Configuracao> listaAll()
    {
        return configuracaoRepository.findAll();
    }
 
    @PutMapping("/configuracoes")
    @Transactional
    public ResponseEntity<Configuracao> update(@RequestBody Configuracao configuracao) 
    {
        Configuracao c = configuracaoRepository.save(configuracao);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    
    @PostMapping("/configuracoes")
    @Transactional
    public ResponseEntity<Configuracao> add(@RequestBody Configuracao config)
    {
        Configuracao newConfig = configuracaoRepository.save(config);
        return new ResponseEntity<>(newConfig, HttpStatus.OK);
    }

    @DeleteMapping("/configuracoes/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        configuracaoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
