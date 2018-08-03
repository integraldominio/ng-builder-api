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

import org.idomine.domain.crud.model.Projeto;

import org.idomine.domain.crud.model.Portal;
import org.idomine.domain.crud.repository.PortalRepository;

import org.idomine.domain.crud.repository.ProjetoRepository;
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
public class ProjetoResource
{
    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PortalRepository portalRepository;

    @GetMapping("/projetos")
    public Iterable<Projeto> listaAll()
    {
        return projetoRepository.findAll();
    }

    @GetMapping("/projetos/count")
    public Long count()
    {
        return projetoRepository.count();
    }

    @PostMapping("/projetos")
    @Transactional
    public ResponseEntity<Projeto> add(@RequestBody Projeto obj)
    {
        if ( obj.getPortal() != null )
        obj.setPortal(portalRepository.findById(obj.getPortal().getId()).get());
        Projeto newObj = projetoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/projetos")
    @Transactional
    public ResponseEntity<Projeto> update(@RequestBody Projeto obj)
    {
        if ( obj.getPortal() != null )
        obj.setPortal(portalRepository.findById(obj.getPortal().getId()).get());
        Projeto newObj = projetoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/projetos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        projetoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/projetos/{id}")
    public Projeto searchId(@PathVariable Long id)
    {
        return projetoRepository.findById(id).get();
    }

    @GetMapping("/projetos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariableNome(@PathVariable  String  nome)
    {
        return new ResponseEntity<>(projetoRepository.findByNome(nome), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/descricao/{descricao}")
    public ResponseEntity<?> searchPathVariableDescricao(@PathVariable  String  descricao)
    {
        return new ResponseEntity<>(projetoRepository.findByDescricao(descricao), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/nomeBackendApp/{nomeBackendApp}")
    public ResponseEntity<?> searchPathVariableNomeBackendApp(@PathVariable  String  nomeBackendApp)
    {
        return new ResponseEntity<>(projetoRepository.findByNomeBackendApp(nomeBackendApp), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/nomeFrontEndApp/{nomeFrontEndApp}")
    public ResponseEntity<?> searchPathVariableNomeFrontEndApp(@PathVariable  String  nomeFrontEndApp)
    {
        return new ResponseEntity<>(projetoRepository.findByNomeFrontEndApp(nomeFrontEndApp), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/iconeApp/{iconeApp}")
    public ResponseEntity<?> searchPathVariableIconeApp(@PathVariable  String  iconeApp)
    {
        return new ResponseEntity<>(projetoRepository.findByIconeApp(iconeApp), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/imageApp/{imageApp}")
    public ResponseEntity<?> searchPathVariableImageApp(@PathVariable  String  imageApp)
    {
        return new ResponseEntity<>(projetoRepository.findByImageApp(imageApp), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/serverLang/{serverLang}")
    public ResponseEntity<?> searchPathVariableServerLang(@PathVariable  String  serverLang)
    {
        return new ResponseEntity<>(projetoRepository.findByServerLang(serverLang), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/serverHost/{serverHost}")
    public ResponseEntity<?> searchPathVariableServerHost(@PathVariable  String  serverHost)
    {
        return new ResponseEntity<>(projetoRepository.findByServerHost(serverHost), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/serverPort/{serverPort}")
    public ResponseEntity<?> searchPathVariableServerPort(@PathVariable  Long  serverPort)
    {
        return new ResponseEntity<>(projetoRepository.findByServerPort(serverPort), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/frontHost/{frontHost}")
    public ResponseEntity<?> searchPathVariableFrontHost(@PathVariable  String  frontHost)
    {
        return new ResponseEntity<>(projetoRepository.findByFrontHost(frontHost), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/frontPort/{frontPort}")
    public ResponseEntity<?> searchPathVariableFrontPort(@PathVariable  Long  frontPort)
    {
        return new ResponseEntity<>(projetoRepository.findByFrontPort(frontPort), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/useLogin/{useLogin}")
    public ResponseEntity<?> searchPathVariableUseLogin(@PathVariable  Boolean  useLogin)
    {
        return new ResponseEntity<>(projetoRepository.findByUseLogin(useLogin), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/useRoles/{useRoles}")
    public ResponseEntity<?> searchPathVariableUseRoles(@PathVariable  Boolean  useRoles)
    {
        return new ResponseEntity<>(projetoRepository.findByUseRoles(useRoles), HttpStatus.OK);
    }
    @GetMapping("/projetos/search/outputDirectory/{outputDirectory}")
    public ResponseEntity<?> searchPathVariableOutputDirectory(@PathVariable  String  outputDirectory)
    {
        return new ResponseEntity<>(projetoRepository.findByOutputDirectory(outputDirectory), HttpStatus.OK);
    }

}
