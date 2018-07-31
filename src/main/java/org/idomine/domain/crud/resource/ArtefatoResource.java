
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
import org.idomine.domain.crud.reporitory.ArtefatoRepository;
import org.idomine.domain.crud.reporitory.ProjetoRepository;
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

    @GetMapping("/artefatos/{id}")
    public Artefato searchId(@PathVariable Long id)
    {
        return artefatoRepository.findById(id).get();
    }

    @PostMapping("/artefatos")
    @Transactional
    public ResponseEntity<Artefato> add(@RequestBody Artefato artefato)
    {
        if (artefato.getProjeto() != null)
            artefato.setProjeto(projetoRepository.findById(artefato.getProjeto().getId()).get());
        Artefato a = artefatoRepository.save(artefato);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @PutMapping("/artefatos")
    @Transactional
    public ResponseEntity<Artefato> update(@RequestBody Artefato artefato)
    {
        if (artefato.getProjeto() != null)
            artefato.setProjeto(projetoRepository.findById(artefato.getProjeto().getId()).get());
        Artefato a = artefatoRepository.save(artefato);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }

    @DeleteMapping("/artefatos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        artefatoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/artefatos/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(artefatoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/artefatos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(artefatoRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/artefatos/search")
    public ResponseEntity<?> searchByParam(@RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "nome", required = false) String nome)
    {
        return new ResponseEntity<>(artefatoRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
