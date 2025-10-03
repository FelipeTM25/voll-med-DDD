package med.voll.api.domain.service;

import med.voll.api.domain.model.consultas_medicas.Consulta;
import med.voll.api.domain.model.consultas_medicas.MotivoCancelamiento;

public interface ValidadorCancelacionConsulta {
    void validar(Consulta consulta, MotivoCancelamiento motivo);
}
