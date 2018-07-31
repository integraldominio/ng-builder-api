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

import java.util.Date;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Formly;


import org.idomine.domain.crud.repository.FormlyRepository;
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
public class FormlyResource
{
    @Autowired
    private FormlyRepository formlyRepository;

    @GetMapping("/formly")
    public Iterable<Formly> listaAll()
    {
        return formlyRepository.findAll();
    }

    @PostMapping("/formly")
    @Transactional
    public ResponseEntity<Formly> add(@RequestBody Formly obj)
    {
        Formly newObj = formlyRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/formly")
    @Transactional
    public ResponseEntity<Formly> update(@RequestBody Formly obj)
    {
        Formly newObj = formlyRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/formly/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        formlyRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/formly/search/campoText/{campoText}")
    public ResponseEntity<?> searchPathVariableCampoText(@PathVariable  String  campoText)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoText(campoText), HttpStatus.OK);
    }
    @GetMapping("/formly/search/campoTextArea/{campoTextArea}")
    public ResponseEntity<?> searchPathVariableCampoTextArea(@PathVariable  String  campoTextArea)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoTextArea(campoTextArea), HttpStatus.OK);
    }
    @GetMapping("/formly/search/campoData/{campoData}")
    public ResponseEntity<?> searchPathVariableCampoData(@PathVariable  Date  campoData)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoData(campoData), HttpStatus.OK);
    }
    @GetMapping("/formly/search/campoCheckbox/{campoCheckbox}")
    public ResponseEntity<?> searchPathVariableCampoCheckbox(@PathVariable  Boolean  campoCheckbox)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoCheckbox(campoCheckbox), HttpStatus.OK);
    }
    @GetMapping("/formly/search/campoSelectOne/{campoSelectOne}")
    public ResponseEntity<?> searchPathVariableCampoSelectOne(@PathVariable  String  campoSelectOne)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoSelectOne(campoSelectOne), HttpStatus.OK);
    }
    @GetMapping("/formly/search/campoRadioGroup/{campoRadioGroup}")
    public ResponseEntity<?> searchPathVariableCampoRadioGroup(@PathVariable  String  campoRadioGroup)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoRadioGroup(campoRadioGroup), HttpStatus.OK);
    }
    @GetMapping("/formly/search/campoToggle/{campoToggle}")
    public ResponseEntity<?> searchPathVariableCampoToggle(@PathVariable  String  campoToggle)
    {
        return new ResponseEntity<>(formlyRepository.findByCampoToggle(campoToggle), HttpStatus.OK);
    }

}
