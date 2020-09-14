package com.example.cursorestfulspringboot.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.cursorestfulspringboot.model.Cliente;

import org.springframework.stereotype.Component;

@Component
public class ClienteRepository {
    public List<Cliente> clientes;

    @PostConstruct
    public void init(){
        Cliente c1 = new Cliente();
        c1.setId(1);
        c1.setNome("Rafael");
        c1.setEndereco("Rua Guarda Civil, 182 - Vila Barão");
        c1.setSaldo(1000000.00);

        Cliente c2 = new Cliente();
        c2.setId(2);
        c2.setNome("Edson");
        c2.setEndereco("Rua Guarda Civil, 182 - Vila Barão");
        c2.setSaldo(500000.00);

        Cliente c3 = new Cliente();
        c3.setId(3);
        c3.setNome("Márcia");
        c3.setEndereco("Rua Guarda Civil, 182 - Vila Barão");
        c3.setSaldo(900000.00);

        clientes = new ArrayList<Cliente>();
        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);
    }

    public List<Cliente> getAllClientes(){
        return clientes;
    }

    public Cliente getClienteById(int id){
        for(Cliente aux : clientes){
            if(aux.getId() == id){
                return aux;
            }
        }

        return null;
    }

	public Cliente salvar(Cliente cliente) {
        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);
        return cliente;
	}
}
