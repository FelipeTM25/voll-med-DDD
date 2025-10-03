package med.voll.api.api.request;

import med.voll.api.application.dto.DatosDireccion;

public record DatosActualizarMedico(
        Long id,
        String nombre,
        String documento,
        DatosDireccion direccion) {
}
