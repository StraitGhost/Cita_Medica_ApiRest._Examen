package ec.webmarket.restful.persistence;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Horario;

/**
 * Repositorio para la entidad Cita.
 * Proporciona métodos de consulta para recuperar citas según diferentes criterios.
 */
public interface CitaRepository extends JpaRepository<Cita, Long> {

    /**
     * Busca todas las citas asociadas a un paciente por su ID.
     * @param pacienteId ID del paciente.
     * @return Lista de citas del paciente.
     */
    List<Cita> findByPacienteId(Long pacienteId);

    /**
     * Busca todas las citas asociadas a un odontólogo por su ID.
     * @param odontologoId ID del odontólogo.
     * @return Lista de citas del odontólogo.
     */
    List<Cita> findByOdontologoId(Long odontologoId);

    /**
     * Busca todas las citas asignadas a un horario específico.
     * @param horario Horario en el que se agendaron las citas.
     * @return Lista de citas en ese horario.
     */
    List<Cita> findByHorario(Horario horario);

    /**
     * Busca todas las citas programadas en una fecha y hora específicas.
     * @param fechayHora Fecha y hora de la cita.
     * @return Lista de citas en ese horario exacto.
     */
    List<Cita> findByFechayHora(LocalDateTime fechayHora);

    /**
     * Busca todas las citas que tengan un estado específico.
     * @param estado Estado de la cita (Ejemplo: "Confirmada", "Cancelada", "Pendiente").
     * @return Lista de citas con el estado indicado.
     */
    List<Cita> findByEstado(String estado);

    /**
     * Busca todas las citas por motivo de consulta.
     * @param motivo Motivo de la cita (Ejemplo: "Limpieza dental", "Extracción", etc.).
     * @return Lista de citas con el motivo especificado.
     */
    List<Cita> findByMotivo(String motivo);
}
