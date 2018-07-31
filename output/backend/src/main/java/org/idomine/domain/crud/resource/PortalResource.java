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

import org.idomine.domain.crud.model.Portal;


import org.idomine.domain.crud.repository.PortalRepository;
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


    @GetMapping("/portais")
    public Iterable<Portal> listaAll()
    {
        return portalRepository.findAll();
    }

    @GetMapping("/portais/count")
    public Long count()
    {
        return portalRepository.count();
    }

    @PostMapping("/portais")
    @Transactional
    public ResponseEntity<Portal> add(@RequestBody Portal obj)
    {
        Portal newObj = portalRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/portais")
    @Transactional
    public ResponseEntity<Portal> update(@RequestBody Portal obj)
    {
        Portal newObj = portalRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/portais/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        portalRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/portais/{id}")
    public Portal searchId(@PathVariable Long id)
    {
        return portalRepository.findById(id).get();
    }

    @GetMapping("/portais/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariableNome(@PathVariable  String  nome)
    {
        return new ResponseEntity<>(portalRepository.findByNome(nome), HttpStatus.OK);
    }
    @GetMapping("/portais/search/descricao/{descricao}")
    public ResponseEntity<?> searchPathVariableDescricao(@PathVariable  String  descricao)
    {
        return new ResponseEntity<>(portalRepository.findByDescricao(descricao), HttpStatus.OK);
    }
    @GetMapping("/portais/search/templateTs/{templateTs}")
    public ResponseEntity<?> searchPathVariableTemplateTs(@PathVariable  String  templateTs)
    {
        return new ResponseEntity<>(portalRepository.findByTemplateTs(templateTs), HttpStatus.OK);
    }
    @GetMapping("/portais/search/templateCss/{templateCss}")
    public ResponseEntity<?> searchPathVariableTemplateCss(@PathVariable  String  templateCss)
    {
        return new ResponseEntity<>(portalRepository.findByTemplateCss(templateCss), HttpStatus.OK);
    }
    @GetMapping("/portais/search/templateHtml/{templateHtml}")
    public ResponseEntity<?> searchPathVariableTemplateHtml(@PathVariable  String  templateHtml)
    {
        return new ResponseEntity<>(portalRepository.findByTemplateHtml(templateHtml), HttpStatus.OK);
    }

}
