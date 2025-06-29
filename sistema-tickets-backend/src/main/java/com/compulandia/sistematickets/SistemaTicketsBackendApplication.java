package com.compulandia.sistematickets;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.compulandia.sistematickets.entities.Tecnico;
import com.compulandia.sistematickets.entities.Ticket;
import com.compulandia.sistematickets.enums.TicketStatus;
import com.compulandia.sistematickets.enums.TypeTicket;
import com.compulandia.sistematickets.repository.TecnicoRepository;
import com.compulandia.sistematickets.repository.TicketRepository;

import jakarta.transaction.Transactional;

@SpringBootApplication
public class SistemaTicketsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaTicketsBackendApplication.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner initData(TecnicoRepository tecnicoRepository, TicketRepository ticketRepository) {
        return args -> {
            // Verificar si ya existen datos para no duplicar
            if (tecnicoRepository.count() == 0) {
                List<Tecnico> tecnicos = Arrays.asList(
                    Tecnico.builder()
                        .id("550e8400-e29b-41d4-a716-446655440000")
                        .nombre("Alexis")
                        .apellido("Gonzalez")
                        .codigo("TE-001")
                        .especialidad("Desarrollo Backend")
                        .build()
                    // ... otros técnicos
                );
                
                tecnicoRepository.saveAll(tecnicos);

                // Insertar tickets solo si no existen
                if (ticketRepository.count() == 0) {
                    TypeTicket[] tiposTicket = TypeTicket.values();
                    Random random = new Random();
                    List<Ticket> tickets = new ArrayList<>();
                    
                    for (Tecnico tecnico : tecnicoRepository.findAll()) {
                        for (int i = 0; i < 10; i++) {
                            tickets.add(Ticket.builder()
                                .cantidad(1000 + random.nextInt(20000))
                                .type(tiposTicket[random.nextInt(tiposTicket.length)])
                                .status(TicketStatus.PENDIENTE)
                                .fecha(LocalDate.now())
                                .tecnico(tecnico)
                                .build());
                        }
                    }
                    ticketRepository.saveAll(tickets);
                }
            }
        };
    }
}
