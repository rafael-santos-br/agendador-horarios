package com.aprendizagem.agendador_horarios.infraestructures.repository.entity;

import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="agendamento")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera id automaticamente 
    private Long id;

    private String profissional;
    private String produto;
    private LocalDateTime dataHoraAgendamento;
    private String cliente;
    private LocalDateTime dataInsercao = LocalDateTime.now();
    private String telefoneCliente;
    private String servico;



}
