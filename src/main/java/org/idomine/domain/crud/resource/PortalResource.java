package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Portal;
import org.idomine.domain.crud.reporitory.PortalRepository;
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
public class PortalResource
{
    @Autowired
    private PortalRepository portalRepository;
    @Autowired
    private GenerationService generationService;

    @GetMapping("/portais/build/{id}")
    public ResponseEntity<?> buildAll(@PathVariable Long id)
    {
        generationService.backendAllToOutput(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/portais/count")
    public Long count()
    {
        return portalRepository.count();
    }

    @GetMapping("/portais")
    public Iterable<Portal> listaAll()
    {
        return portalRepository.findAll();
    }
    
    @GetMapping("/portais/{id}")
    public Portal searchId(@PathVariable Long id)
    {
        return portalRepository.findById(id).get();
    }

    @PostMapping("/portais")
    @Transactional
    public ResponseEntity<Portal> add(@RequestBody Portal portal)
    {
        Portal newPortal = portalRepository.save(portal);
        return new ResponseEntity<>(newPortal, HttpStatus.OK);
    }

    @PutMapping("/portais")
    @Transactional
    public ResponseEntity<Portal> update(@RequestBody Portal portal)
    {
        Portal newPortal = portalRepository.save(portal);
        return new ResponseEntity<>(newPortal, HttpStatus.OK);
    }

    @DeleteMapping("/portais/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        portalRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/portais/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(portalRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/portais/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(portalRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/portais/search")
    public ResponseEntity<?> searchByParam(@RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome)
    {
        return new ResponseEntity<>(portalRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
