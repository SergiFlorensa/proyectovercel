package com.example.ProyectoFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.ProyectoFinal.entity.dto.TarjetaBancariaDTO;
import com.example.ProyectoFinal.service.TarjetaBancariaService;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaBancariaController {

    @Autowired
    private TarjetaBancariaService tarjetaService;

    @PostMapping("/registro/{idusuario}")
    public ResponseEntity<?> registrarTarjetaBancaria(@PathVariable Long idusuario, @RequestBody TarjetaBancariaDTO tarjetaBancariaDTO) {
        try {
            // Llamar al servicio para registrar la tarjeta bancaria asociada al usuario
            tarjetaService.guardarTarjetaBancaria(idusuario, tarjetaBancariaDTO);
            return ResponseEntity.ok("Tarjeta bancaria registrada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al registrar la tarjeta bancaria.");
        }
    }
}
