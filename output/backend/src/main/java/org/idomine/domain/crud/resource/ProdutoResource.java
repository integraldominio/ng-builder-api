package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Produto;
import org.idomine.domain.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProdutoResource
{
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/produtos")
    public Iterable<Produto> listaAll()
    {
        return produtoRepository.findAll();
    }

    @PostMapping("/produtos/add")
    @Transactional
    public ResponseEntity<Produto> add(@RequestBody Produto obj)
    {
        Produto newObj = produtoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/produtos/put")
    @Transactional
    public ResponseEntity<Produto> update(@RequestBody Produto obj)
    {
        Produto newObj = produtoRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/produtos/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        produtoRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/produtos/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(produtoRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/produtos/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(produtoRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/produtos/search")
    public ResponseEntity<?> searchByParam( @RequestParam(value="id" ,required=false) Long id, @RequestParam(value="nome" ,required=false) String nome)
    {
        return new ResponseEntity<>(produtoRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
