package med.voll.api.domain.service;

import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.model.gestion_pacientes.Paciente;

import java.time.LocalDateTime;

public interface ValidadorReservaConsulta {
    void validar(Paciente paciente, Medico medico, LocalDateTime fecha);
}
