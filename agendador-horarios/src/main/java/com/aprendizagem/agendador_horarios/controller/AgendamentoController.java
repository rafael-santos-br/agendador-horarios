package com.aprendizagem.agendador_horarios.controller;

import org.springframework.web.bind.annotation.RestController;

import com.aprendizagem.agendador_horarios.infraestructures.repository.entity.Agendamento;
import com.aprendizagem.agendador_horarios.services.AgendamentoServices;

import lombok.RequiredArgsConstructor;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {
    private final AgendamentoServices agendamentoServices;
    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento) {
        return ResponseEntity.accepted().body(agendamentoServices.salvarAgendamento(agendamento));
    }
    

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente,
                                                   @RequestParam LocalDateTime dataHoraAgendamento){
        agendamentoServices.deletarAgendamento( dataHoraAgendamento,cliente);
       return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> buscarAgendamentosDia(LocalDate data){
        return ResponseEntity.ok().body(agendamentoServices.buscarAgendamentosDia(data));
    }
    
    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamentos(
        @RequestBody Agendamento agendamento,
        @RequestParam String cliente,
        @RequestParam LocalDateTime dataHoraAgendamento) {
        return ResponseEntity.accepted().body(agendamentoServices.alterarAgendamento(agendamento,cliente, dataHoraAgendamento));
    }



}
