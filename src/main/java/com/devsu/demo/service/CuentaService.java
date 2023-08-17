package com.devsu.demo.service;

import com.devsu.demo.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface CuentaService {
    List<Cuenta> getAllCuentas();

    Optional<Cuenta> getCuentaById(Long id);

    Cuenta createCuenta(Cuenta cuenta);

    Cuenta updateCuenta(Long id, Cuenta cuentaDetails);

    void deleteCuenta(Long id);
}
