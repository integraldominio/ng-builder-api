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

import org.idomine.domain.crud.model.Artefato;
import org.idomine.domain.crud.repository.ArtefatoRepository;

import org.idomine.domain.crud.repository.ElementoRepository;
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
    public ResponseEntity<Elemento> add(@RequestBody Elemento obj)
    {
        if ( obj.getArtefato() != null )
        obj.setArtefato(artefatoRepository.findById(obj.getArtefato().getId()).get());
        Elemento newObj = elementoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/elementos")
    @Transactional
    public ResponseEntity<Elemento> update(@RequestBody Elemento obj)
    {
        if ( obj.getArtefato() != null )
        obj.setArtefato(artefatoRepository.findById(obj.getArtefato().getId()).get());
        Elemento newObj = elementoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/elementos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        elementoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/elementos/{id}")
    public Elemento searchId(@PathVariable Long id)
    {
        return elementoRepository.findById(id).get();
    }

    @GetMapping("/elementos/search/tipoElemento/{tipoElemento}")
    public ResponseEntity<?> searchPathVariableTipoElemento(@PathVariable  String  tipoElemento)
    {
        return new ResponseEntity<>(elementoRepository.findByTipoElemento(tipoElemento), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/tipoField/{tipoField}")
    public ResponseEntity<?> searchPathVariableTipoField(@PathVariable  String  tipoField)
    {
        return new ResponseEntity<>(elementoRepository.findByTipoField(tipoField), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariableNome(@PathVariable  String  nome)
    {
        return new ResponseEntity<>(elementoRepository.findByNome(nome), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/rotulo/{rotulo}")
    public ResponseEntity<?> searchPathVariableRotulo(@PathVariable  String  rotulo)
    {
        return new ResponseEntity<>(elementoRepository.findByRotulo(rotulo), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/inicial/{inicial}")
    public ResponseEntity<?> searchPathVariableInicial(@PathVariable  String  inicial)
    {
        return new ResponseEntity<>(elementoRepository.findByInicial(inicial), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/mascara/{mascara}")
    public ResponseEntity<?> searchPathVariableMascara(@PathVariable  String  mascara)
    {
        return new ResponseEntity<>(elementoRepository.findByMascara(mascara), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/pipe/{pipe}")
    public ResponseEntity<?> searchPathVariablePipe(@PathVariable  String  pipe)
    {
        return new ResponseEntity<>(elementoRepository.findByPipe(pipe), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/dica/{dica}")
    public ResponseEntity<?> searchPathVariableDica(@PathVariable  String  dica)
    {
        return new ResponseEntity<>(elementoRepository.findByDica(dica), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/tamanho/{tamanho}")
    public ResponseEntity<?> searchPathVariableTamanho(@PathVariable  Long  tamanho)
    {
        return new ResponseEntity<>(elementoRepository.findByTamanho(tamanho), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/min/{min}")
    public ResponseEntity<?> searchPathVariableMin(@PathVariable  Long  min)
    {
        return new ResponseEntity<>(elementoRepository.findByMin(min), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/max/{max}")
    public ResponseEntity<?> searchPathVariableMax(@PathVariable  Long  max)
    {
        return new ResponseEntity<>(elementoRepository.findByMax(max), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/linhas/{linhas}")
    public ResponseEntity<?> searchPathVariableLinhas(@PathVariable  Long  linhas)
    {
        return new ResponseEntity<>(elementoRepository.findByLinhas(linhas), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/ordenation/{ordenation}")
    public ResponseEntity<?> searchPathVariableOrdenation(@PathVariable  Long  ordenation)
    {
        return new ResponseEntity<>(elementoRepository.findByOrdenation(ordenation), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/persistence/{persistence}")
    public ResponseEntity<?> searchPathVariablePersistence(@PathVariable  Boolean  persistence)
    {
        return new ResponseEntity<>(elementoRepository.findByPersistence(persistence), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/requerido/{requerido}")
    public ResponseEntity<?> searchPathVariableRequerido(@PathVariable  Boolean  requerido)
    {
        return new ResponseEntity<>(elementoRepository.findByRequerido(requerido), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/showcolumn/{showcolumn}")
    public ResponseEntity<?> searchPathVariableShowcolumn(@PathVariable  Boolean  showcolumn)
    {
        return new ResponseEntity<>(elementoRepository.findByShowcolumn(showcolumn), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/uniqueKey/{uniqueKey}")
    public ResponseEntity<?> searchPathVariableUniqueKey(@PathVariable  Boolean  uniqueKey)
    {
        return new ResponseEntity<>(elementoRepository.findByUniqueKey(uniqueKey), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/valueProp/{valueProp}")
    public ResponseEntity<?> searchPathVariableValueProp(@PathVariable  String  valueProp)
    {
        return new ResponseEntity<>(elementoRepository.findByValueProp(valueProp), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/labelProp/{labelProp}")
    public ResponseEntity<?> searchPathVariableLabelProp(@PathVariable  String  labelProp)
    {
        return new ResponseEntity<>(elementoRepository.findByLabelProp(labelProp), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/options/{options}")
    public ResponseEntity<?> searchPathVariableOptions(@PathVariable  String  options)
    {
        return new ResponseEntity<>(elementoRepository.findByOptions(options), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/fieldGroup/{fieldGroup}")
    public ResponseEntity<?> searchPathVariableFieldGroup(@PathVariable  Long  fieldGroup)
    {
        return new ResponseEntity<>(elementoRepository.findByFieldGroup(fieldGroup), HttpStatus.OK);
    }
    @GetMapping("/elementos/search/fieldSize/{fieldSize}")
    public ResponseEntity<?> searchPathVariableFieldSize(@PathVariable  Long  fieldSize)
    {
        return new ResponseEntity<>(elementoRepository.findByFieldSize(fieldSize), HttpStatus.OK);
    }

}
