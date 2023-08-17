package com.devsu.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Reporte {
    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private double saldoInicial;
    private boolean estado;
    private double movimiento;
    private double saldoDisponible;
}
