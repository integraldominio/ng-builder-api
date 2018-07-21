package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.${artefato.className};
import org.idomine.domain.crud.repository.${artefato.className}Repository;
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
public class ${artefato.className}Resource
{
    @Autowired
    private ${artefato.className}Repository ${artefato.classFolder}Repository;

    @GetMapping("/${artefato.resourceName}")
    public Iterable<${artefato.className}> listaAll()
    {
        return ${artefato.classFolder}Repository.findAll();
    }

    @PostMapping("/${artefato.resourceName}")
    @Transactional
    public ResponseEntity<${artefato.className}> add(@RequestBody ${artefato.className} obj)
    {
        ${artefato.className} newObj = ${artefato.classFolder}Repository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/${artefato.resourceName}")
    @Transactional
    public ResponseEntity<${artefato.className}> update(@RequestBody ${artefato.className} obj)
    {
        ${artefato.className} newObj = ${artefato.classFolder}Repository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/${artefato.resourceName}/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        ${artefato.classFolder}Repository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/${artefato.resourceName}/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(${artefato.classFolder}Repository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/${artefato.resourceName}/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(${artefato.classFolder}Repository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/${artefato.resourceName}/search")
    public ResponseEntity<?> searchByParam( @RequestParam(value="id" ,required=false) Long id, @RequestParam(value="nome" ,required=false) String nome)
    {
        return new ResponseEntity<>(${artefato.classFolder}Repository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
