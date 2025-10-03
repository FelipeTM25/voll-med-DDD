package med.voll.api.domain.repository;

import med.voll.api.domain.model.consultas_medicas.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Boolean existsByPacienteIdAndFechaBetween(Long idPaciente, LocalDateTime primerHorario, LocalDateTime ultimoHorario);

    Boolean existsByMedicoIdAndFecha(Long idMedico, LocalDateTime fecha);

    @Query("""
            select case when count(c) > 0 then true else false end from Consulta c 
            where c.paciente.id = :idPaciente 
            and c.fecha = :fecha 
            and c.motivoCancelamiento is null
            """)
    Boolean existsConsultaActivaEnFecha(Long idPaciente, LocalDateTime fecha);

    @Query("""
            select case when count(c) > 0 then true else false end from Consulta c 
            where c.medico.id = :idMedico 
            and c.fecha = :fecha 
            and c.motivoCancelamiento is null
            """)
    Boolean existsConsultaActivaMedicoEnFecha(Long idMedico, LocalDateTime fecha);
}
