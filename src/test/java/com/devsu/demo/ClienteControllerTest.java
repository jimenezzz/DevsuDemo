package com.devsu.demo;

import com.devsu.demo.controller.ClienteController;
import com.devsu.demo.model.Cliente;
import com.devsu.demo.repository.ClienteRepository;
import com.devsu.demo.service.ClienteService;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    public void getAllShouldReturnEmptyJson() throws Exception {
        when(clienteService.getAllClientes()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/clientes")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAllShouldReturnJson() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setClienteId("1234");
        cliente.setNombre("Jesus");

        List<Cliente> allClientes = Arrays.asList(cliente);

        given(clienteService.getAllClientes()).willReturn(allClientes);

        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is(cliente.getNombre())));
    }
}
