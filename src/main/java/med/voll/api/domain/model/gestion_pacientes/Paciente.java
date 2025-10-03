package med.voll.api.domain.model.gestion_pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.model.shared.AggregateRoot;
import med.voll.api.domain.model.shared.Direccion;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente extends AggregateRoot {
    private String nombre;
    private String email;
    private String documento;
    private String telefono;

    @Embedded
    private Direccion direccion;

    private Boolean activo;

    public Paciente(String nombre, String email, String telefono, String documento, Direccion direccion) {
        this.activo = true;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.documento = documento;
        this.direccion = direccion;
    }

    public void actualizarInformacion(String nombre, String telefono, Direccion direccion) {
        if (nombre != null) {
            this.nombre = nombre;
        }
        if (telefono != null) {
            this.telefono = telefono;
        }
        if (direccion != null) {
            this.direccion = direccion;
        }
    }

    public void inactivar() {
        this.activo = false;
    }

    public boolean estaActivo() {
        return this.activo;
    }
}
