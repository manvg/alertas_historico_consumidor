package com.historico_alertas.alertas_historico_consumidor.service;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.historico_alertas.alertas_historico_consumidor.model.SignosVitales;

@Service
public class RabbitMQConsumer {
    private final AlertaMedicaHistoricoService alertaMedicaService;

    public RabbitMQConsumer(AlertaMedicaHistoricoService alertaMedicaService) {
        this.alertaMedicaService = alertaMedicaService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.alertas_historico}")
    public void recibirAlerta(SignosVitales signos) {
        System.out.println("RabbitMQ => Alerta crítica recibida para el paciente: " + signos.getNombrePaciente());
        
        try {
            alertaMedicaService.guardarRegistroHistorico(signos);
            System.out.println("RabbitMQ => Registro histórico guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo JSON: " + e.getMessage());
        }
    }
}
