package med.voll.api.domain.service;

import med.voll.api.domain.aggregates.Consulta;
import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.entities.Paciente;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.shared.DomainException;
import med.voll.api.domain.value_objects.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorReservaConsulta> validadores;

    public Consulta reservar(Long idPaciente, Long idMedico, Especialidad especialidad, LocalDateTime fecha) {
        // Verificar existencia del paciente
        Paciente paciente = pacienteRepository.findById(idPaciente)
                .orElseThrow(() -> new DomainException("Paciente no encontrado"));

        // Elegir médico
        Medico medico = elegirMedico(idMedico, especialidad, fecha);
        if (medico == null) {
            throw new DomainException("No existe un medico disponible para la especialidad y fecha informada");
        }

        // Ejecutar todas las validaciones
        validadores.forEach(validador -> validador.validar(paciente, medico, fecha));

        // Crear y guardar la consulta
        Consulta consulta = new Consulta(medico, paciente, fecha);
        return consultaRepository.save(consulta);
    }

    private Medico elegirMedico(Long idMedico, Especialidad especialidad, LocalDateTime fecha) {
        if (idMedico != null) {
            return medicoRepository.findById(idMedico)
                    .orElseThrow(() -> new DomainException("Medico no encontrado"));
        }

        if (especialidad == null) {
            throw new DomainException("Especialidad requerida cuando no se especifica un medico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(especialidad, fecha);
    }
}
