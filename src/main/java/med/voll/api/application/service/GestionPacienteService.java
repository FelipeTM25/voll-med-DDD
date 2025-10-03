package med.voll.api.application.service;

import med.voll.api.api.request.DatosActualizacionPaciente;
import med.voll.api.api.request.DatosRegistroPaciente;
import med.voll.api.domain.model.gestion_pacientes.Paciente;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.domain.model.shared.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestionPacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente registrarPaciente(DatosRegistroPaciente datos) {
        Direccion direccion = new Direccion(datos.direccion());
        Paciente paciente = new Paciente(
            datos.nombre(),
            datos.email(),
            datos.telefono(),
            datos.documento(),
            direccion
        );
        return pacienteRepository.save(paciente);
    }

    @Transactional(readOnly = true)
    public Page<Paciente> listarPacientes(Pageable paginacion) {
        return pacienteRepository.findByActivoTrue(paginacion);
    }

    public Paciente actualizarPaciente(DatosActualizacionPaciente datos) {
        Paciente paciente = pacienteRepository.getReferenceById(datos.id());
        Direccion nuevaDireccion = datos.direccion() != null ? new Direccion(datos.direccion()) : null;
        paciente.actualizarInformacion(datos.nombre(), datos.telefono(), nuevaDireccion);
        return paciente;
    }

    public void eliminarPaciente(Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inactivar();
    }

    @Transactional(readOnly = true)
    public Paciente obtenerPaciente(Long id) {
        return pacienteRepository.getReferenceById(id);
    }
}
