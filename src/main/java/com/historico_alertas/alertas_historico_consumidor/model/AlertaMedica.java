package com.historico_alertas.alertas_historico_consumidor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertaMedica {
    private int idAlertaMedica;

    private String nombrePaciente;

    private int ritmoCardiaco;

    private double temperatura;

    private int presionSistolica;

    private int presionDiastolica;

    private String observacion;

    private LocalDateTime fechaRegistro;

    // public AlertaMedica() {
    //     this.fechaRegistro = LocalDateTime.now();
    // }

    // public Integer getId() {
    //     return idAlertaMedica;
    // }

    // public String getNombrePaciente() {
    //     return nombrePaciente;
    // }

    // public void setNombrePaciente(String nombrePaciente) {
    //     this.nombrePaciente = nombrePaciente;
    // }

    // public int getRitmoCardiaco() {
    //     return ritmoCardiaco;
    // }

    // public void setRitmoCardiaco(int ritmoCardiaco) {
    //     this.ritmoCardiaco = ritmoCardiaco;
    // }

    // public double getTemperatura() {
    //     return temperatura;
    // }

    // public void setTemperatura(double temperatura) {
    //     this.temperatura = temperatura;
    // }

    // public int getPresionSistolica() {
    //     return presionSistolica;
    // }

    // public void setPresionSistolica(int presionSistolica) {
    //     this.presionSistolica = presionSistolica;
    // }

    // public int getPresionDiastolica() {
    //     return presionDiastolica;
    // }

    // public void setPresionDiastolica(int presionDiastolica) {
    //     this.presionDiastolica = presionDiastolica;
    // }

    // public String getObservacion() {
    //     return observacion;
    // }

    // public void setObservacion(String observacion) {
    //     this.observacion = observacion;
    // }

    // public LocalDateTime getFechaRegistro() {
    //     return fechaRegistro;
    // }
}

