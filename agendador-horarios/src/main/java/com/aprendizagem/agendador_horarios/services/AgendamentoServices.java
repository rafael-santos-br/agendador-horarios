package com.aprendizagem.agendador_horarios.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.List;
import org.springframework.stereotype.Service;

import com.aprendizagem.agendador_horarios.infraestructures.repository.AgendamentoRepository;
import com.aprendizagem.agendador_horarios.infraestructures.repository.entity.Agendamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  //injeção de depêndencia
public class AgendamentoServices {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento){
    
    LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
    LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

    Agendamento agendados =  agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),horaAgendamento, horaFim );

    if(Objects.nonNull(agendados)){
        throw new RuntimeException("Este horário não está disponível");
    }
    return agendamentoRepository.save(agendamento);
        
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente){

        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

    }

    public List<Agendamento> buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia =  data.atTime(23,59,59);
         
        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento){
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

    if(Objects.nonNull(agenda)){
        throw new RuntimeException("Este horário está disponível");
    }

    agendamento.setId(agenda.getId());
    return agendamentoRepository.save(agendamento);

    }

}


