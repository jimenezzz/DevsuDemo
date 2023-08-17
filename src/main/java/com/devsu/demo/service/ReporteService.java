package com.devsu.demo.service;

import com.devsu.demo.model.Reporte;

import java.text.ParseException;
import java.util.List;

public interface ReporteService {

    List<Reporte> getReporte(Long id, String fechaInicial, String fechaFinal);
}
