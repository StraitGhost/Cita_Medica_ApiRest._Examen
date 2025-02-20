package ec.webmarket.restful.dto.v1;

import lombok.Data;

/**
 * DTO (Data Transfer Object) para la entidad Odontólogo.
 * Se utiliza para transferir datos de un odontólogo entre la capa de servicio y la capa de presentación.
 */
@Data
public class OdontologoDTO {

    /**
     * Identificador único del odontólogo.
     */
    private Long id;

    /**
     * Número de cédula del odontólogo.
     */
    private String cedula;

    /**
     * Nombre del odontólogo.
     */
    private String nombre;

    /**
     * Apellido del odontólogo.
     */
    private String apellido;

    /**
     * Número de teléfono del odontólogo.
     */
    private String telefono;

    /**
     * Correo electrónico del odontólogo.
     */
    private String email;

    /**
     * Información del usuario asociada al odontólogo.
     */
    private UsuarioDTO usuario;
}
