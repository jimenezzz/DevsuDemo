package com.devsu.demo;

import com.devsu.demo.controller.CuentaController;
import com.devsu.demo.controller.MovimientoController;
import com.devsu.demo.model.Cuenta;
import com.devsu.demo.model.Movimiento;
import com.devsu.demo.service.CuentaService;
import com.devsu.demo.service.MovimientoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuentaController.class)
public class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuentaService cuentaService;

    @Test
    public void getAllShouldReturnEmptyJson() throws Exception {
        when(cuentaService.getAllCuentas()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get("/cuentas")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void getAllShouldReturnJson() throws Exception {

        Cuenta cuenta = new Cuenta();
        cuenta.setEstado(true);

        List<Cuenta> allCuentas = Arrays.asList(cuenta);

        given(cuentaService.getAllCuentas()).willReturn(allCuentas);

        mockMvc.perform(get("/cuentas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].estado", is(cuenta.isEstado())));
    }
}
