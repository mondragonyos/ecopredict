// ReciboController.java
package com.ecopredict.controller;

import com.ecopredict.model.ReciboCFE;
import com.ecopredict.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recibos")
@CrossOrigin(origins = "*")
public class ReciboController {

    @Autowired
    private ReciboRepository reciboRepository;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarRecibo(@RequestBody ReciboCFE recibo) {
        try {
            if (recibo.getIdUsuario() == null || recibo.getKwh() <= 0 || recibo.getCosto() <= 0) {
                return ResponseEntity.badRequest().body("Error: Datos del recibo inválidos.");
            }
            ReciboCFE nuevoRecibo = reciboRepository.save(recibo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRecibo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el recibo.");
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> obtenerHistorial(@PathVariable Long idUsuario) {
        try {
            List<ReciboCFE> historial = reciboRepository.findByIdUsuarioOrderByFechaRegistroAsc(idUsuario);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el historial.");
        }
    }
}