package med.voll.api.application.dto.request;

import med.voll.api.domain.model.gestion_medica.Especialidad;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idPaciente,
        Long idMedico,
        LocalDateTime fecha,
        Especialidad especialidad) {
}
