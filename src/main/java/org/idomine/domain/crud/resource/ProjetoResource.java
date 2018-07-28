package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.reporitory.PortalRepository;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
import org.idomine.domain.crud.service.GenerationService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ProjetoResource
{
    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private PortalRepository portalRepository;
    @Autowired
    private GenerationService generationService;

    
    @GetMapping("/projetos/build/{id}")
    public ResponseEntity<?> buildAll(@PathVariable Long id)
    {
        generationService.backendAllToOutput(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping("/projetos")
    public Iterable<Projeto> listaAll()
    {
        Iterable<Projeto> x =  projetoRepository.findAll();
        return x;
    }

    @PostMapping("/projetos")
    @Transactional
    public ResponseEntity<Projeto> add(@RequestBody Projeto projeto)
    {
        if (projeto.getPortal() != null)
            projeto.setPortal(portalRepository.findById(projeto.getPortal().getId()).get());
        Projeto proj = projetoRepository.save(projeto);
        return new ResponseEntity<>(proj, HttpStatus.OK);
    }

    @PutMapping("/projetos")
    @Transactional
    public ResponseEntity<Projeto> update(@RequestBody Projeto projeto)
    {
        if (projeto.getPortal() != null)
            projeto.setPortal(portalRepository.findById(projeto.getPortal().getId()).get());
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
    
    @GetMapping("/projetos/{id}")
    public ResponseEntity<?> searchId(@PathVariable Long id)
    {
        return new ResponseEntity<>(projetoRepository.findById(id), HttpStatus.OK);
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
