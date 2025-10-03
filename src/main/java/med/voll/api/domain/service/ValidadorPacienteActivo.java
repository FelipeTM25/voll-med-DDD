package med.voll.api.domain.service;

import med.voll.api.domain.model.shared.DomainException;
import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.model.gestion_pacientes.Paciente;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteActivo implements ValidadorReservaConsulta {

    @Override
    public void validar(Paciente paciente, Medico medico, LocalDateTime fecha) {
        if (!paciente.estaActivo()) {
            throw new DomainException("El paciente no está activo");
        }
    }
}
