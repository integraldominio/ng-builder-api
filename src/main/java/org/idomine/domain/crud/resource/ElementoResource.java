
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

    @GetMapping("/elementos/count")
    public Long count()
    {
        return elementoRepository.count();
    }

    @PostMapping("/elementos")
    @Transactional
    public ResponseEntity<Elemento> add(@RequestBody Elemento elemento)
    {
        if (elemento.getArtefato() != null)
            elemento.setArtefato(artefatoRepository.findById(elemento.getArtefato().getId()).get());
        Elemento e = elementoRepository.save(elemento);
        return new ResponseEntity<>(e, HttpStatus.OK);
    }

    @GetMapping("/elementos/{id}")
    public Elemento searchId(@PathVariable Long id)
    {
        return elementoRepository.findById(id).get();
    }

    @PutMapping("/elementos")
    @Transactional
    public ResponseEntity<Elemento> update(@RequestBody Elemento elemento)
    {
        if (elemento.getArtefato() != null)
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
