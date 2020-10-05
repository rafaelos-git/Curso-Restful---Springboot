package com.example.cursorestfulspringboot.controllers;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.cursorestfulspringboot.dto.ClienteDTO;
import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;
import com.example.cursorestfulspringboot.service.ClienteService;

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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//retorna o objeto e os dados do objeto são gravados diretamente na resposta HTTP como JSON ou XML.
@RestController 
//define uma chamada para acesso.
@RequestMapping("/clientes")       
public class ClienteController {
    //fornece controle sobre onde e como a ligação entre os beans deve ser realizada.
    @Autowired
    private ClienteService servico;

    @Autowired          
    private ClienteRepository repository;

    //puxa a chamada definida no RequestMapping.
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

    //Adiciona um Cliente usando a chamada definida no RequestMapping e acrescentando um id único para ele.
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody final ClienteDTO novoCliente, HttpServletRequest request, UriComponentsBuilder builder){
        Cliente cli = repository.salvar(servico.fromDTO(novoCliente));
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cli.getId()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
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
    public ResponseEntity<Cliente> atualizar(@PathVariable final int id, @RequestBody ClienteDTO novoCliente){
        if(repository.getClienteById(id) != null){
            Cliente cli = servico.fromDTO(novoCliente);
            cli.setId(id);
            cli = repository.update(cli);
            return ResponseEntity.ok(cli);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}