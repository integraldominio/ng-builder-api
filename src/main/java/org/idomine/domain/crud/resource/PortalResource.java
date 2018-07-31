
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
