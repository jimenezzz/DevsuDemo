package com.devsu.demo.service;

import com.devsu.demo.model.Cuenta;
import com.devsu.demo.model.Movimiento;
import com.devsu.demo.model.Reporte;
import com.devsu.demo.repository.ClienteRepository;
import com.devsu.demo.repository.CuentaRepository;
import com.devsu.demo.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService{
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Reporte> getReporte(Long id, String fechaInicial, String fechaFinal) {

        Optional<Cuenta> cuentaOpcional = cuentaRepository.findById(id);

        Date dateInicial;
        Date dateFinal;
        try{
            dateInicial=new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicial);
            dateFinal=new SimpleDateFormat("yyyy-MM-dd").parse(fechaFinal);
        }
        catch(ParseException e){
            throw new IllegalArgumentException("Error de sintaxis en fecha");
        }

        if (cuentaOpcional.isPresent()) {
            Cuenta cuenta = cuentaOpcional.get();

            List<Reporte> reportes = new ArrayList<>();
            cuenta.getMovimientos().stream()
                    .filter(mov -> mov.getFecha().after(dateInicial) && mov.getFecha().before(dateFinal))
                    .forEach(mov->{
                        Reporte reporte = new Reporte();
                        reporte.setCliente(cuenta.getCliente().getNombre());
                        reporte.setFecha(mov.getFecha());
                        reporte.setEstado(cuenta.isEstado());
                        reporte.setTipo(mov.getTipoMovimiento());
                        reporte.setMovimiento(mov.getValor());
                        reporte.setNumeroCuenta(cuenta.getNumeroCuenta());
                        reporte.setSaldoDisponible(mov.getSaldo());
                        reporte.setSaldoInicial(cuenta.getSaldoInicial());
                        reportes.add(reporte);
                    });

            return reportes;
        }

        return null;
    }
}
