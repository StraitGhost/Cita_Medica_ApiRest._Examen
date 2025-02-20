package ec.webmarket.restful.dto.v1;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para la entidad Horario.
 * Se utiliza para transferir datos de un horario entre la capa de servicio y la capa de presentación.
 */
@Data
public class HorarioDTO {

    /**
     * Identificador único del horario.
     */
    private Long id;

    /**
     * Fecha en la que está programado el horario.
     */
    private LocalDate fecha;

    /**
     * Hora de inicio del horario.
     */
    private LocalTime horaInicio;

    /**
     * Hora de finalización del horario.
     */
    private LocalTime horafinal;

    /**
     * Indica si el horario está disponible para agendar citas.
     */
    private Boolean disponibilidad;

    /**
     * Odontólogo asignado a este horario.
     */
    private OdontologoDTO odontologo;
}
