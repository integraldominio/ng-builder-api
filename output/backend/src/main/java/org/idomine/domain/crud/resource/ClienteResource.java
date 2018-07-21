package org.idomine.domain.crud.resource;

import javax.transaction.Transactional;

import org.idomine.domain.crud.model.Cliente;
import org.idomine.domain.crud.repository.ClienteRepository;
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
public class ClienteResource
{
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public Iterable<Cliente> listaAll()
    {
        return clienteRepository.findAll();
    }

    @PostMapping("/clientes")
    @Transactional
    public ResponseEntity<Cliente> add(@RequestBody Cliente obj)
    {
        Cliente newObj = clienteRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @PutMapping("/clientes")
    @Transactional
    public ResponseEntity<Cliente> update(@RequestBody Cliente obj)
    {
        Cliente newObj = clienteRepository.save(obj);
        return new ResponseEntity<>(newObj, HttpStatus.OK);
    }

    @DeleteMapping("/clientes/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        clienteRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/clientes/search/id/{id}")
    public ResponseEntity<?> searchPathVariable(@PathVariable Long id)
    {
        return new ResponseEntity<>(clienteRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/clientes/search/nome/{nome}")
    public ResponseEntity<?> searchPathVariable(@PathVariable String nome)
    {
        return new ResponseEntity<>(clienteRepository.findByNome(nome), HttpStatus.OK);
    }

    @GetMapping("/clientes/search")
    public ResponseEntity<?> searchByParam( @RequestParam(value="id" ,required=false) Long id, @RequestParam(value="nome" ,required=false) String nome)
    {
        return new ResponseEntity<>(clienteRepository.findByNomeIgnoreCaseOrId(nome, id), HttpStatus.OK);
    }

}
