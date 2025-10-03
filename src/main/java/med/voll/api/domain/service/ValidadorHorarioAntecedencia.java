package med.voll.api.domain.service;

import med.voll.api.domain.model.consultas_medicas.Consulta;
import med.voll.api.domain.model.consultas_medicas.MotivoCancelamiento;
import med.voll.api.domain.model.shared.DomainException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorCancelacionConsulta {

    public void validar(Consulta consulta, MotivoCancelamiento motivo) {
        var ahora = LocalDateTime.now();
        var diferenciasEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciasEnHoras < 24) {
            throw new DomainException("La consulta solamente puede ser cancelada con antecedencia mínima de 24h!");
        }
    }
}
