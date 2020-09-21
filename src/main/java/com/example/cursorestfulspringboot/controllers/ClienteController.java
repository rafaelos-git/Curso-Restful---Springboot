package com.example.cursorestfulspringboot.controllers;

import java.net.URI;
import java.util.List;

import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> getClientes(){
        return repository.getAllClientes();
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable final int id){
        final Cliente cli = repository.getClienteById(id);
        
        if(cli != null){
            return ResponseEntity.ok(cli);
        } else {
            return ResponseEntity.notFound().build();
        }
    } 

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody final Cliente cliente){
        final Cliente cli = repository.salvar(cliente);
        final URI uri = URI.create("http://localhost:8081/clientes/" + cli.getId());
        return ResponseEntity.created(uri).build();
    }

    //Remover um Cliente @DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable final int id){
        final Cliente cli = repository.getClienteById(id);

        if(cli != null){
            repository.remove(cli);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Atualizar um Cliente @PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable final int id, @RequestBody Cliente cliente){
        if(repository.getClienteById(id) != null){
            cliente.setId(id);
            cliente = repository.update(cliente);
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}