package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProjetoResource
{
    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping("/projetos")
    public Iterable<Projeto> listaAll()
    {
        return projetoRepository.findAll();
    }

    @PostMapping("/projetos/add")
    @Transactional
    public ResponseEntity<Projeto> add(@RequestBody Projeto projeto)
    {
        Projeto proj = projetoRepository.save(projeto);
        return new ResponseEntity<>(proj, HttpStatus.OK);
    }

    @PutMapping("/projetos/put")
    @Transactional
    public ResponseEntity<Projeto> update(@RequestBody Projeto projeto)
    {
        Projeto proj = projetoRepository.save(projeto);
        return new ResponseEntity<>(proj, HttpStatus.OK);
    }

    @DeleteMapping("/projetos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        projetoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/projetos/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(projetoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/projetos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(projetoRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/projetos/search")
    public ResponseEntity<?> searchByParam( @RequestParam(value="id" ,required=false) Long id, @RequestParam(value="nome" ,required=false) String nome)
    {
        return new ResponseEntity<>(projetoRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
