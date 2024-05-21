package com.example.ProyectoFinal.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaBancariaDTO {

    @NotBlank
    private String nombreTitular;

    @Pattern(regexp = "\\d{12}", message = "El número de tarjeta debe tener exactamente 12 dígitos")
    private String numeroTarjeta;

    @Pattern(regexp = "\\d{3}", message = "El CVV debe tener exactamente 3 dígitos")
    private String cvv;

    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaCaducidad;
}