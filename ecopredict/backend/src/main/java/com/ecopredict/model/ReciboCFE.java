// ReciboCFE.java
package com.ecopredict.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recibos_cfe")
public class ReciboCFE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recibo")
    private Long idRecibo;

    @Column(name = "id_usuario", nullable = false)
    private Long idUsuario;

    @Column(nullable = false)
    private int kwh;

    @Column(nullable = false, precision = 10, scale = 2)
    private double costo;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
    }

    public ReciboCFE() {}

    // Getters y Setters
    public Long getIdRecibo() { return idRecibo; }
    public void setIdRecibo(Long idRecibo) { this.idRecibo = idRecibo; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public int getKwh() { return kwh; }
    public void setKwh(int kwh) { this.kwh = kwh; }

    public double getCosto() { return costo; }
    public void setCosto(double costo) { this.costo = costo; }

    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
}