package ec.webmarket.restful.dto.v1;

import java.time.LocalDate;
import lombok.Data;

/**
 * DTO (Data Transfer Object) para la entidad Paciente.
 * Se utiliza para transferir datos de un paciente entre la capa de servicio y la capa de presentación.
 */
@Data
public class PacienteDTO {

    /**
     * Identificador único del paciente.
     */
    private Long id;

    /**
     * Número de cédula del paciente.
     */
    private String cedula;

    /**
     * Nombre del paciente.
     */
    private String nombre;

    /**
     * Apellido del paciente.
     */
    private String apellido;

    /**
     * Número de teléfono del paciente.
     */
    private String telefono;

    /**
     * Correo electrónico del paciente.
     */
    private String email;

    /**
     * Fecha de nacimiento del paciente.
     */
    private LocalDate fechaNacimiento;

    /**
     * Dirección del paciente.
     */
    private String direccion;

    /**
     * Información del usuario asociada al paciente.
     */
    private UsuarioDTO usuario;
}
