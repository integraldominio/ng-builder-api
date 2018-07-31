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

import org.idomine.domain.crud.model.Configuracao;


import org.idomine.domain.crud.repository.ConfiguracaoRepository;
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
public class ConfiguracaoResource
{
    @Autowired
    private ConfiguracaoRepository configuracaoRepository;


    @GetMapping("/configuracoes")
    public Iterable<Configuracao> listaAll()
    {
        return configuracaoRepository.findAll();
    }

    @GetMapping("/configuracoes/count")
    public Long count()
    {
        return configuracaoRepository.count();
    }

    @PostMapping("/configuracoes")
    @Transactional
    public ResponseEntity<Configuracao> add(@RequestBody Configuracao obj)
    {
        Configuracao newObj = configuracaoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/configuracoes")
    @Transactional
    public ResponseEntity<Configuracao> update(@RequestBody Configuracao obj)
    {
        Configuracao newObj = configuracaoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/configuracoes/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        configuracaoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/configuracoes/{id}")
    public Configuracao searchId(@PathVariable Long id)
    {
        return configuracaoRepository.findById(id).get();
    }

    @GetMapping("/configuracoes/search/nomeEmpresa/{nomeEmpresa}")
    public ResponseEntity<?> searchPathVariableNomeEmpresa(@PathVariable  String  nomeEmpresa)
    {
        return new ResponseEntity<>(configuracaoRepository.findByNomeEmpresa(nomeEmpresa), HttpStatus.OK);
    }
    @GetMapping("/configuracoes/search/siteEmpresa/{siteEmpresa}")
    public ResponseEntity<?> searchPathVariableSiteEmpresa(@PathVariable  String  siteEmpresa)
    {
        return new ResponseEntity<>(configuracaoRepository.findBySiteEmpresa(siteEmpresa), HttpStatus.OK);
    }
    @GetMapping("/configuracoes/search/emailEmpresa/{emailEmpresa}")
    public ResponseEntity<?> searchPathVariableEmailEmpresa(@PathVariable  String  emailEmpresa)
    {
        return new ResponseEntity<>(configuracaoRepository.findByEmailEmpresa(emailEmpresa), HttpStatus.OK);
    }
    @GetMapping("/configuracoes/search/outputDirectory/{outputDirectory}")
    public ResponseEntity<?> searchPathVariableOutputDirectory(@PathVariable  String  outputDirectory)
    {
        return new ResponseEntity<>(configuracaoRepository.findByOutputDirectory(outputDirectory), HttpStatus.OK);
    }
    @GetMapping("/configuracoes/search/appProperties/{appProperties}")
    public ResponseEntity<?> searchPathVariableAppProperties(@PathVariable  String  appProperties)
    {
        return new ResponseEntity<>(configuracaoRepository.findByAppProperties(appProperties), HttpStatus.OK);
    }

}
