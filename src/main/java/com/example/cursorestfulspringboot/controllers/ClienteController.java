package com.example.cursorestfulspringboot.controllers;

import java.util.List;

import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private ClienteRepository repository;

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return repository.getAllClientes();
    } 

    @GetMapping("/clientes/{id}")
    public Cliente getClienteByCodigo(@PathVariable int id){
        return repository.getClienteById(id);
    } 

    @PostMapping("/clientes")
    public Cliente salvar(@RequestBody Cliente cliente){
        return repository.salvar(cliente);
    }

    //Remover um Cliente @DeleteMapping
    //Atualizar um Cliente @PutMapping
}