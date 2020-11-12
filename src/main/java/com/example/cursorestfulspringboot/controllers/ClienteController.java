package com.example.cursorestfulspringboot.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.cursorestfulspringboot.dto.ClienteDTO;
import com.example.cursorestfulspringboot.dto.PedidoDTO;
import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.model.Pedido;
import com.example.cursorestfulspringboot.service.ClienteService;
import com.example.cursorestfulspringboot.service.PedidoService;

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
    private ClienteService servicoCliente;

    @Autowired
    private PedidoService servicoPedido;

    //puxa a chamada definida no RequestMapping.
    @GetMapping            
    public List<Cliente> getClientes(){
        return servicoCliente.getAllClientes();
    } 

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteByCodigo(@PathVariable final int id){
        final Cliente cli = servicoCliente.getClienteById(id);
        return ResponseEntity.ok(cli);
    } 

    //Adiciona um Cliente usando a chamada definida no RequestMapping e acrescentando um id único para ele.
    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody final ClienteDTO novoCliente, HttpServletRequest request, UriComponentsBuilder builder){
        Cliente cli = servicoCliente.salvar(servicoCliente.fromDTO(novoCliente));
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cli.getId()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    //Remover um Cliente @DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable final int id){
        servicoCliente.removeById(id);
        return ResponseEntity.noContent().build();
    }

    //Atualizar um Cliente @PutMapping
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable final int id, @RequestBody ClienteDTO novoCliente){
            Cliente cli = servicoCliente.fromDTO(novoCliente);
            cli.setId(id);
            cli = servicoCliente.update(cli);
            return ResponseEntity.ok(cli);
    }

    @PostMapping("/{id}/pedidos")
    public ResponseEntity<Void> salvar(@PathVariable int id,@RequestBody Pedido pedido, HttpServletRequest request, UriComponentsBuilder builder){
        pedido = servicoPedido.salvar(pedido, id);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + pedido.getNumero()).build();

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @GetMapping("{id}/pedidos")
    public List<PedidoDTO> getPedidosCliente(@PathVariable int id) {
        Cliente cliente = servicoCliente.getClienteById(id);
        return servicoPedido.toListDTO(cliente.getPedidos());
    }
}