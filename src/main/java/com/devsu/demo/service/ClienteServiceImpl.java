package com.devsu.demo.service;

import com.devsu.demo.model.Cliente;
import com.devsu.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            // Actualizar los campos necesarios de acuerdo a clienteDetails
            clienteDetails.setId(id);
            return clienteRepository.save(clienteDetails);
        }

        return null; // Manejar si la cliente no existe
    }

    public Cliente updateCliente2(Long id, Cliente clienteDetails) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            // Actualizar los campos necesarios de acuerdo a clienteDetails
            clienteDetails.setId(id);
            return clienteRepository.save(clienteDetails);
        }

        return null; // Manejar si la cliente no existe
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
