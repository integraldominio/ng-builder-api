package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Elemento;
import org.idomine.domain.crud.reporitory.ArtefatoRepository;
import org.idomine.domain.crud.reporitory.ElementoRepository;
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
public class ElementoResource
{
    @Autowired
    private ElementoRepository elementoRepository;
    @Autowired
    private ArtefatoRepository artefatoRepository;

    @GetMapping("/elementos")
    public Iterable<Elemento> listaAll()
    {
        return elementoRepository.findAll();
    }

    @PostMapping("/elementos")
    @Transactional
    public ResponseEntity<Elemento> add(@RequestBody Elemento elemento)
    {
        if ( elemento.getArtefato() != null )
        elemento.setArtefato(artefatoRepository.findById(elemento.getArtefato().getId()).get());
        Elemento e = elementoRepository.save(elemento);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @PutMapping("/elementos")
    @Transactional
    public ResponseEntity<Elemento> update(@RequestBody Elemento elemento)
    {
        if ( elemento.getArtefato() != null )
        elemento.setArtefato(artefatoRepository.findById(elemento.getArtefato().getId()).get());
        Elemento e = elementoRepository.save(elemento);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @DeleteMapping("/elementos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        elementoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/elementos/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(elementoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/elementos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(elementoRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/elementos/search")
    public ResponseEntity<?> searchByParam(@RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome)
    {
        return new ResponseEntity<>(elementoRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
