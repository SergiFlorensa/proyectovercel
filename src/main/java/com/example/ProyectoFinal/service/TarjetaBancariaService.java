package com.example.ProyectoFinal.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoFinal.entity.TarjetaBancaria;
import com.example.ProyectoFinal.entity.Usuario;
import com.example.ProyectoFinal.entity.dto.TarjetaBancariaDTO;
import com.example.ProyectoFinal.repository.TarjetaBancariaRepository;
import com.example.ProyectoFinal.repository.UsuarioRepository;

@Service
public class TarjetaBancariaService {

    @Autowired
    private TarjetaBancariaRepository tarjetaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TarjetaBancaria guardarTarjetaBancaria(Long idusuario, TarjetaBancariaDTO tarjetaBancariaDTO) {
        // Validar el número de tarjeta y el CVV utilizando expresiones regulares
        if (!tarjetaBancariaDTO.getNumeroTarjeta().matches("\\d{12}")) {
            throw new RuntimeException("El número de tarjeta debe tener exactamente 12 dígitos.");
        }
        if (!tarjetaBancariaDTO.getCvv().matches("\\d{3}")) {
            throw new RuntimeException("El CVV debe tener exactamente 3 dígitos.");
        }

        // Verificar si la tarjeta ya existe en la base de datos
        Optional<TarjetaBancaria> tarjetaExistente = tarjetaRepository.findByNumerotarjeta(tarjetaBancariaDTO.getNumeroTarjeta());
        if (tarjetaExistente.isPresent()) {
            // Si la tarjeta ya existe, retornarla sin guardar una nueva entrada
            return tarjetaExistente.get();
        } else {
            // Obtener el usuario por su ID
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(idusuario);
            if (!usuarioOptional.isPresent()) {
                throw new RuntimeException("No se encontró el usuario con el ID proporcionado.");
            }
            
            // Si el usuario existe, asociar la tarjeta a ese usuario y guardarla en la base de datos
            Usuario usuario = usuarioOptional.get();
            TarjetaBancaria nuevaTarjeta = new TarjetaBancaria();
            nuevaTarjeta.setNombretitular(tarjetaBancariaDTO.getNombreTitular());
            nuevaTarjeta.setNumerotarjeta(tarjetaBancariaDTO.getNumeroTarjeta());
            nuevaTarjeta.setCvv(tarjetaBancariaDTO.getCvv());
            nuevaTarjeta.setFechacaducidad(tarjetaBancariaDTO.getFechaCaducidad());
            nuevaTarjeta.setUsuario(usuario);

            return tarjetaRepository.save(nuevaTarjeta);
        }
    }
}
