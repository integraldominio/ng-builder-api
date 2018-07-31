/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
 
package org.idomine.domain.crud.resource;


import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Artefato;

import org.idomine.domain.crud.model.Projeto;
import org.idomine.domain.crud.repository.ProjetoRepository;

import org.idomine.domain.crud.repository.ArtefatoRepository;
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
public class ArtefatoResource
{
    @Autowired
    private ArtefatoRepository artefatoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @GetMapping("/artefatos")
    public Iterable<Artefato> listaAll()
    {
        return artefatoRepository.findAll();
    }

    @GetMapping("/artefatos/count")
    public Long count()
    {
        return artefatoRepository.count();
    }

    @PostMapping("/artefatos")
    @Transactional
    public ResponseEntity<Artefato> add(@RequestBody Artefato obj)
    {
        if ( obj.getProjeto() != null )
        obj.setProjeto(projetoRepository.findById(obj.getProjeto().getId()).get());
        Artefato newObj = artefatoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/artefatos")
    @Transactional
    public ResponseEntity<Artefato> update(@RequestBody Artefato obj)
    {
        if ( obj.getProjeto() != null )
        obj.setProjeto(projetoRepository.findById(obj.getProjeto().getId()).get());
        Artefato newObj = artefatoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/artefatos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        artefatoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/artefatos/{id}")
    public Artefato searchId(@PathVariable Long id)
    {
        return artefatoRepository.findById(id).get();
    }

    @GetMapping("/artefatos/search/tipo/{tipo}")
    public ResponseEntity<?> searchPathVariableTipo(@PathVariable  String  tipo)
    {
        return new ResponseEntity<>(artefatoRepository.findByTipo(tipo), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariableNome(@PathVariable  String  nome)
    {
        return new ResponseEntity<>(artefatoRepository.findByNome(nome), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/resourceName/{resourceName}")
    public ResponseEntity<?> searchPathVariableResourceName(@PathVariable  String  resourceName)
    {
        return new ResponseEntity<>(artefatoRepository.findByResourceName(resourceName), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/className/{className}")
    public ResponseEntity<?> searchPathVariableClassName(@PathVariable  String  className)
    {
        return new ResponseEntity<>(artefatoRepository.findByClassName(className), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/classFolder/{classFolder}")
    public ResponseEntity<?> searchPathVariableClassFolder(@PathVariable  String  classFolder)
    {
        return new ResponseEntity<>(artefatoRepository.findByClassFolder(classFolder), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/paginaHome/{paginaHome}")
    public ResponseEntity<?> searchPathVariablePaginaHome(@PathVariable  Boolean  paginaHome)
    {
        return new ResponseEntity<>(artefatoRepository.findByPaginaHome(paginaHome), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/templateTs/{templateTs}")
    public ResponseEntity<?> searchPathVariableTemplateTs(@PathVariable  String  templateTs)
    {
        return new ResponseEntity<>(artefatoRepository.findByTemplateTs(templateTs), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/templateHtml/{templateHtml}")
    public ResponseEntity<?> searchPathVariableTemplateHtml(@PathVariable  String  templateHtml)
    {
        return new ResponseEntity<>(artefatoRepository.findByTemplateHtml(templateHtml), HttpStatus.OK);
    }
    @GetMapping("/artefatos/search/templateCss/{templateCss}")
    public ResponseEntity<?> searchPathVariableTemplateCss(@PathVariable  String  templateCss)
    {
        return new ResponseEntity<>(artefatoRepository.findByTemplateCss(templateCss), HttpStatus.OK);
    }

}
