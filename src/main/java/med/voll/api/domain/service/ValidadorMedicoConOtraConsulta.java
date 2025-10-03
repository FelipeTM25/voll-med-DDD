package med.voll.api.domain.service;

import med.voll.api.domain.model.shared.DomainException;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.model.gestion_pacientes.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorMedicoConOtraConsulta implements ValidadorReservaConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(Paciente paciente, Medico medico, LocalDateTime fecha) {
        if (consultaRepository.existsConsultaActivaMedicoEnFecha(medico.getId(), fecha)) {
            throw new DomainException("El médico ya tiene otra consulta programada en el mismo horario");
        }
    }
}
