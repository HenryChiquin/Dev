package com.dxlatam.dashboard.domain.dto;

import lombok.Data;

@Data
public class Response {
    private String mensaje;
    private int exito;


    public Response(String mensaje) {
        this.mensaje = mensaje;
    }
}
