package med.voll.api.application.dto.request;

import med.voll.api.domain.model.gestion_medica.Especialidad;
import med.voll.api.application.dto.DatosDireccion;

public record DatosRegistroMedico(
        String nombre,
        String email,
        String telefono,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion) {
}
