package ec.webmarket.restful.dto.v1;

import lombok.Data;

/**
 * DTO (Data Transfer Object) para la entidad Usuario.
 * Se utiliza para transferir datos de un usuario entre la capa de servicio y la capa de presentación.
 */
@Data
public class UsuarioDTO {

    /**
     * Identificador único del usuario.
     */
    private Long id;

    /**
     * Nombre de usuario utilizado para autenticación.
     */
    private String nombreUsuario;

    /**
     * Clave o contraseña del usuario.
     * ⚠️ Se recomienda cifrarla antes de almacenarla en la base de datos.
     */
    private String clave;

    /**
     * Tipo de usuario: 
     * true = Odontólogo, false = Paciente.
     */
    private Boolean tipoUsuario;
}
