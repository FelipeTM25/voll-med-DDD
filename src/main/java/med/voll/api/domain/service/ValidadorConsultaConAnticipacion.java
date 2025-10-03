package med.voll.api.domain.service;

import med.voll.api.domain.model.shared.DomainException;
import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.model.gestion_pacientes.Paciente;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorConsultaConAnticipacion implements ValidadorReservaConsulta {

    @Override
    public void validar(Paciente paciente, Medico medico, LocalDateTime fecha) {
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fecha).toMinutes();

        if (diferenciaEnMinutos < 30) {
            throw new DomainException("Las consultas deben ser reservadas con al menos 30 minutos de anticipación");
        }
    }
}
