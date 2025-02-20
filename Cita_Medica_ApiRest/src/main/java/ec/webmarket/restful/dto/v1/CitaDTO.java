package ec.webmarket.restful.dto.v1;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para la entidad Cita.
 * Se utiliza para transferir datos de una cita entre la capa de servicio y la capa de presentación.
 */
@Data
public class CitaDTO {

    /**
     * Identificador único de la cita.
     */
    private Long id;

    /**
     * Paciente asociado a la cita.
     */
    private PacienteDTO paciente;

    /**
     * Odontólogo que atenderá la cita.
     */
    private OdontologoDTO odontologo;

    /**
     * Horario asignado a la cita.
     */
    private HorarioDTO horario;

    /**
     * Fecha y hora de la cita.
     */
    private LocalDateTime fechayHora;

    /**
     * Estado de la cita (ejemplo: "pendiente", "confirmada", "cancelada").
     */
    private String estado;

    /**
     * Motivo de la cita.
     */
    private String motivo;
}
