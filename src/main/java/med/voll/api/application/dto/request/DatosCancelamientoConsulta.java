package med.voll.api.application.dto.request;

import med.voll.api.domain.model.consultas_medicas.MotivoCancelamiento;

public record DatosCancelamientoConsulta(
        Long idConsulta,
        MotivoCancelamiento motivo) {
}
