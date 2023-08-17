package com.devsu.demo.service;

import com.devsu.demo.model.Cuenta;
import com.devsu.demo.model.Movimiento;
import com.devsu.demo.repository.CuentaRepository;
import com.devsu.demo.repository.MovimientoRepository;
import com.devsu.demo.utility.Constants;
import com.devsu.demo.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements MovimientoService{

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    @Override
    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    @Override
    public Movimiento createMovimiento(Movimiento movimiento) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(movimiento.getCuenta().getId());
        if(cuenta.isEmpty()){
            throw new IllegalArgumentException("Cuenta no existente");
        }

        double saldo = calcularSaldo(cuenta.get());
        double valorMovimiento = movimiento.getValor();

        if (valorMovimiento < 0) {
            if(saldo+valorMovimiento<0){
                throw new IllegalArgumentException("Saldo no disponible");
            }
            else if (calcularCupoDiarioDisponible(cuenta.get()) <= -Constants.LIMITE_DIARIO ||
                    valorMovimiento < -Constants.LIMITE_DIARIO) {
                throw new IllegalArgumentException("Cupo diario excedido");
            }
        }

        movimiento.setSaldo(saldo + valorMovimiento);
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Optional<Movimiento> movimientoOptional = movimientoRepository.findById(id);

        if (movimientoOptional.isPresent()) {
            Movimiento movimiento = movimientoOptional.get();
            // Actualizar los campos necesarios de acuerdo a movimientoDetails
            movimientoDetails.setId(id);
            return movimientoRepository.save(movimientoDetails);
        }

        return null; // Manejar si el movimiento no existe
    }

    @Override
    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    public double calcularCupoDiarioDisponible(Cuenta cuenta){
        LocalDate hoy = LocalDate.now();

        double result = cuenta.getMovimientos().stream()
                .filter(mov -> mov.getValor() < 0 && hoy.equals(Utility.convertToLocalDate(mov.getFecha())))
                .map(Movimiento::getValor)
                .reduce(0.0, Double::sum);

        return result;
    }

    public double calcularSaldo(Cuenta cuenta){
        double result = cuenta.getMovimientos().stream()
                .map(Movimiento::getValor)
                .reduce(0.0, Double::sum);
        return cuenta.getSaldoInicial()+result;
    }
}
