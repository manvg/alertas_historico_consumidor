package com.historico_alertas.alertas_historico_consumidor.service;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.historico_alertas.alertas_historico_consumidor.model.AlertaMedica;
import com.historico_alertas.alertas_historico_consumidor.model.SignosVitales;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AlertaMedicaHistoricoService {

    private final ObjectMapper objectMapper;

    @Value("${historico.archivo.directorio}")
    private String directorioArchivos;

    public AlertaMedicaHistoricoService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void guardarRegistroHistorico(SignosVitales signos) throws IOException {
        AlertaMedica alerta = generarAlerta(signos);
        guardarEnArchivo(alerta);
        System.out.println("Registro histórico guardado en JSON para el paciente: " + signos.getNombrePaciente());
    }

    private AlertaMedica generarAlerta(SignosVitales signos) {
        return new AlertaMedica(
            0,
            signos.getNombrePaciente(),
            signos.getRitmoCardiaco(),
            signos.getTemperatura(),
            signos.getPresionSistolica(),
            signos.getPresionDiastolica(),
            generarObservacion(signos),
            LocalDateTime.now()
        );
    }

    private void guardarEnArchivo(AlertaMedica alerta) throws IOException {
        Path path = Path.of(directorioArchivos);
        Files.createDirectories(path); // 🔹 Manejo de directorios mejorado

        String nombreArchivo = String.format("historico_%s_%s.json",
            alerta.getFechaRegistro().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")),
            alerta.getNombrePaciente().replace(" ", "_"));

        File archivo = new File(directorioArchivos, nombreArchivo);
        objectMapper.writeValue(archivo, alerta);
    }

    private String generarObservacion(SignosVitales signos) {
        StringBuilder observacion = new StringBuilder("Signos vitales alterados: ");
        boolean alterado = false;

        if (signos.getRitmoCardiaco() < 50 || signos.getRitmoCardiaco() > 120) {
            observacion.append("Ritmo Cardíaco: ").append(signos.getRitmoCardiaco()).append(" bpm. ");
            alterado = true;
        }
        if (signos.getTemperatura() < 35.0 || signos.getTemperatura() > 38.0) {
            observacion.append("Temperatura: ").append(signos.getTemperatura()).append("°C. ");
            alterado = true;
        }
        if (signos.getPresionSistolica() < 90 || signos.getPresionSistolica() > 140) {
            observacion.append("Presión Sistólica: ").append(signos.getPresionSistolica()).append(" mmHg. ");
            alterado = true;
        }
        if (signos.getPresionDiastolica() < 60 || signos.getPresionDiastolica() > 90) {
            observacion.append("Presión Diastólica: ").append(signos.getPresionDiastolica()).append(" mmHg. ");
            alterado = true;
        }

        return alterado ? observacion.toString().trim() : "ℹ️ Todos los valores dentro del rango normal.";
    }
}