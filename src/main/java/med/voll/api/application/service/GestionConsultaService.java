package med.voll.api.application.service;

import med.voll.api.application.dto.request.DatosCancelamientoConsulta;
import med.voll.api.application.dto.request.DatosReservaConsulta;
import med.voll.api.domain.model.consultas_medicas.Consulta;
import med.voll.api.domain.service.CancelacionConsultaService;
import med.voll.api.domain.service.ReservaConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestionConsultaService {

    @Autowired
    private ReservaConsultaService reservaService;

    @Autowired
    private CancelacionConsultaService cancelacionService;

    public Consulta reservarConsulta(DatosReservaConsulta datos) {
        return reservaService.reservar(
            datos.idPaciente(),
            datos.idMedico(),
            datos.especialidad(),
            datos.fecha()
        );
    }

    public void cancelarConsulta(DatosCancelamientoConsulta datos) {
        cancelacionService.cancelar(datos.idConsulta(), datos.motivo());
    }
}
