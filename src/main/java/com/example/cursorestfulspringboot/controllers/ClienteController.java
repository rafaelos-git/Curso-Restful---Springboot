package com.example.cursorestfulspringboot.controllers;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    public List<Cliente> clientes;

    @PostConstruct
    public void init(){
        Cliente c1 = new Cliente();
        c1.id = 1;
        c1.nome = "Rafael";
        c1.endereco = "Rua Guarda Civil, 182 - Vila Barão";
        c1.saldo = 1000000.00;

        Cliente c2 = new Cliente();
        c2.id = 2;
        c2.nome = "Edson";
        c2.endereco = "Rua Guarda Civil, 182 - Vila Barão";
        c2.saldo = 500000.00;

        Cliente c3 = new Cliente();
        c3.id = 3;
        c3.nome = "Márcia";
        c3.endereco = "Rua Guarda Civil, 182 - Vila Barão";
        c3.saldo = 900000.00;

        clientes = Arrays.asList(c1,c2,c3);
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return clientes;
    } 

    @GetMapping("/clientes/{id}")
    public Cliente getClienteByCodigo(@PathVariable int id){
        Cliente cliente = null;

        for(Cliente aux : clientes){
            if(aux.id == id){
                cliente = aux;
                break;
            }
        }
        return cliente;
    } 

}