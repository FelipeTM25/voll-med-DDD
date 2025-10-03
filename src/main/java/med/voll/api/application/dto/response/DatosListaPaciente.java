package med.voll.api.application.dto.response;

import med.voll.api.domain.model.gestion_pacientes.Paciente;

public record DatosListaPaciente(
        Long id,
        String nombre,
        String email,
        String documento) {

    public DatosListaPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumento());
    }
}
