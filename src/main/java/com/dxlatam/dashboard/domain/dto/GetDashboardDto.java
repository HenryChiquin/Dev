package com.dxlatam.dashboard.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class GetDashboardDto {

    private String estado;
    private int codigo;
}
