package com.devsu.demo.service;

import com.devsu.demo.model.Cuenta;
import com.devsu.demo.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService{

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(id);

        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            // Actualizar los campos necesarios de acuerdo a cuentaDetails
            cuentaDetails.setId(id);
            return cuentaRepository.save(cuentaDetails);
        }

        return null; // Manejar si la cuenta no existe
    }

    @Override
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}
