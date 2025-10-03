package med.voll.api.api.controller;

import jakarta.validation.Valid;
import med.voll.api.application.dto.request.DatosReservaConsulta;
import med.voll.api.application.dto.request.DatosCancelamientoConsulta;
import med.voll.api.application.dto.response.DatosDetalleConsulta;
import med.voll.api.application.service.GestionConsultaService;
import med.voll.api.domain.model.consultas_medicas.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private GestionConsultaService gestionConsultaService;

    @PostMapping
    public ResponseEntity<DatosDetalleConsulta> reservar(@RequestBody @Valid DatosReservaConsulta datos, UriComponentsBuilder builder) {
        Consulta consulta = gestionConsultaService.reservarConsulta(datos);
        DatosDetalleConsulta datosDetalle = new DatosDetalleConsulta(consulta);
        URI url = builder.path("/consultas/{id}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(url).body(datosDetalle);
    }

    @DeleteMapping
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        gestionConsultaService.cancelarConsulta(datos);
        return ResponseEntity.noContent().build();
    }
}
