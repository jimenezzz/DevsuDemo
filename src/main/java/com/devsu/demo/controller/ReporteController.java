package com.devsu.demo.controller;

import com.devsu.demo.model.Movimiento;
import com.devsu.demo.model.Reporte;
import com.devsu.demo.service.MovimientoService;
import com.devsu.demo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> getReporte(@RequestParam Long id, @RequestParam String fechaInicial, @RequestParam String fechaFinal) {
        return reporteService.getReporte(id,fechaInicial,fechaFinal);
    }
}
