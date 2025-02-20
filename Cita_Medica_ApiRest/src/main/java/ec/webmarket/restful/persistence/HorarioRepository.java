package ec.webmarket.restful.persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.domain.Odontologo;

/**
 * Repositorio para la entidad Horario.
 * Proporciona métodos de consulta para recuperar horarios según diferentes criterios.
 */
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    /**
     * Busca todos los horarios asociados a un odontólogo por su ID.
     * @param odontologoId ID del odontólogo.
     * @return Lista de horarios del odontólogo.
     */
    List<Horario> findByOdontologo_Id(Long odontologoId);

    /**
     * Busca todos los horarios disponibles en una fecha específica.
     * @param fecha Fecha de los horarios.
     * @return Lista de horarios en esa fecha.
     */
    List<Horario> findByFecha(LocalDate fecha);

    /**
     * Busca todos los horarios que comienzan a una hora específica.
     * @param horaInicio Hora de inicio del horario.
     * @return Lista de horarios que inician a la hora indicada.
     */
    List<Horario> findByHoraInicio(LocalTime horaInicio);

    /**
     * Busca todos los horarios que terminan a una hora específica.
     * @param horafinal Hora de finalización del horario.
     * @return Lista de horarios que finalizan a la hora indicada.
     */
    List<Horario> findByHorafinal(LocalTime horafinal);

    /**
     * Busca todos los horarios según su disponibilidad.
     * @param disponibilidad `true` si el horario está disponible, `false` si no lo está.
     * @return Lista de horarios según disponibilidad.
     */
    List<Horario> findByDisponibilidad(Boolean disponibilidad);

    /**
     * Busca todos los horarios de un odontólogo específico.
     * @param odontologo Objeto odontólogo.
     * @return Lista de horarios del odontólogo dado.
     */
    List<Horario> findByOdontologo(Odontologo odontologo);
}
