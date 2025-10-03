package med.voll.api.application.service;

import med.voll.api.api.request.DatosActualizarMedico;
import med.voll.api.api.request.DatosRegistroMedico;
import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.model.shared.Direccion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestionMedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico registrarMedico(DatosRegistroMedico datos) {
        Direccion direccion = new Direccion(datos.direccion());
        Medico medico = new Medico(
            datos.nombre(),
            datos.email(),
            datos.telefono(),
            datos.documento(),
            datos.especialidad(),
            direccion
        );
        return medicoRepository.save(medico);
    }

    @Transactional(readOnly = true)
    public Page<Medico> listarMedicos(Pageable paginacion) {
        return medicoRepository.findByActivoTrue(paginacion);
    }

    public Medico actualizarMedico(DatosActualizarMedico datos) {
        Medico medico = medicoRepository.getReferenceById(datos.id());
        Direccion nuevaDireccion = datos.direccion() != null ? new Direccion(datos.direccion()) : null;
        medico.actualizarInformacion(datos.nombre(), datos.documento(), nuevaDireccion);
        return medico;
    }

    public void eliminarMedico(Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivar();
    }

    @Transactional(readOnly = true)
    public Medico obtenerMedico(Long id) {
        return medicoRepository.getReferenceById(id);
    }
}
