package com.dxlatam.dashboard.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity @Data @NoArgsConstructor
public class GetDashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String estado;
    private int codigo;
    private int invocado;
    private int excepcion;
    private int exito;

    public GetDashboard(String estado, int codigo, int invocado, int excepcion, int exito) {
        this.estado = estado;
        this.codigo = codigo;
        this.invocado = invocado;
        this.excepcion = excepcion;
        this.exito = exito;
    }
}
