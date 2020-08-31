package com.example.cursorestfulspringboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public String getClientes(){
        return "Vai retornar todos os clientes da base";
    } 

    @GetMapping("/clientes/{codigo}")
    public String getClienteByCodigo(@PathVariable int codigo){
        return "Vai retornar o cliente de código: " + codigo;
    } 

}