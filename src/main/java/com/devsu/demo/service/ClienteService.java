package com.devsu.demo.service;

import com.devsu.demo.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> getAllClientes();

    Optional<Cliente> getClienteById(Long id);

    Cliente createCliente(Cliente cliente);

    Cliente updateCliente(Long id, Cliente clienteDetails);

    void deleteCliente(Long id);
}
