package med.voll.api.domain.service;

import med.voll.api.domain.aggregates.Consulta;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.shared.DomainException;
import med.voll.api.domain.value_objects.MotivoCancelamiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelacionConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private List<ValidadorCancelacionConsulta> validadores;

    public void cancelar(Long idConsulta, MotivoCancelamiento motivo) {
        Consulta consulta = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new DomainException("El Id informado de la consulta no existe"));

        // Ejecutar todas las validaciones de cancelación
        validadores.forEach(validador -> validador.validar(consulta, motivo));

        // Cancelar la consulta
        consulta.cancelar(motivo);
        consultaRepository.save(consulta);
    }
}
