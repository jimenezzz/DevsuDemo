package com.devsu.demo.service;

import com.devsu.demo.model.Movimiento;

import java.util.List;
import java.util.Optional;

public interface MovimientoService {
    List<Movimiento> getAllMovimientos();

    Optional<Movimiento> getMovimientoById(Long id);

    Movimiento createMovimiento(Movimiento movimiento);

    Movimiento updateMovimiento(Long id, Movimiento movimientoDetails);

    void deleteMovimiento(Long id);
}
