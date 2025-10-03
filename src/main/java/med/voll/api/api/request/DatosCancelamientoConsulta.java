package med.voll.api.api.request;

import med.voll.api.domain.model.consultas_medicas.MotivoCancelamiento;

public record DatosCancelamientoConsulta(
        Long idConsulta,
        MotivoCancelamiento motivo) {
}
