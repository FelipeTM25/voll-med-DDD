package med.voll.api.application.dto.response;

import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.model.gestion_medica.Especialidad;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad) {

    public DatosRespuestaMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(),
             medico.getDocumento(), medico.getEspecialidad());
    }
}
