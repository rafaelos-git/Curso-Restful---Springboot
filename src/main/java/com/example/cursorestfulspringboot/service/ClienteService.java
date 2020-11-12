package com.example.cursorestfulspringboot.service;

import java.util.List;
import java.util.Optional;

import com.example.cursorestfulspringboot.dto.ClienteDTO;
import com.example.cursorestfulspringboot.model.Cliente;
import com.example.cursorestfulspringboot.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

//O proprio Spring inicia e gerencia objeto.
@Service
public class ClienteService {
        @Autowired
        private ClienteRepository repository;

        public Cliente fromDTO(ClienteDTO dto){
                Cliente cliente = new Cliente();

                cliente.setEndereco(dto.getEndereco());
                cliente.setNome(dto.getNome());

                return cliente;
        }

        public List<Cliente> getAllClientes() {
                return repository.getAllClientes();
        }

        public Cliente getClienteById(int id) {
                Optional <Cliente> op = repository.getClienteById(id);
                return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não cadastrado"));
        }

        public Cliente salvar(Cliente cliente) {
                //Possíveis regras de negócio devem ser implementadas aqui
                return repository.salvar(cliente);
        }

        public void removeById(int id) {
                repository.remove(getClienteById(id));
        }

        public Cliente update(Cliente cliente) {
                getClienteById(cliente.getId());
                return repository.update(cliente);
        }
}