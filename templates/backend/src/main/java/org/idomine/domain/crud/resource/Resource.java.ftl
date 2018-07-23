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

  <#list artefato.elementos as field >
  	<#if field.tipoElemento == "Field">
    @GetMapping("/${artefato.resourceName}/search/${field.nome}/{${field.nome}}")
    public ResponseEntity<?> searchPathVariable${field.nome?cap_first}(@PathVariable ${field.tipoField} ${field.nome})
    {
        return new ResponseEntity<>(${artefato.classFolder}Repository.findBy${field.nome?cap_first}(${field.nome}), HttpStatus.OK);
    }
    </#if>

  </#list>	

}
