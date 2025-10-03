package med.voll.api.api.response;

import med.voll.api.domain.model.gestion_medica.Medico;
import med.voll.api.domain.model.gestion_medica.Especialidad;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        Especialidad especialidad) {

    public DatosListadoMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(),
             medico.getDocumento(), medico.getEspecialidad());
    }
}
