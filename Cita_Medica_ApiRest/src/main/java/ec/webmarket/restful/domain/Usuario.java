package ec.webmarket.restful.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa un usuario dentro del sistema.
 * Un usuario puede ser un odontólogo o un paciente.
 */
@Getter
@Setter
@Entity
public class Usuario {

    /**
     * Identificador único del usuario.
     * Se genera automáticamente y no es modificable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    /**
     * Nombre de usuario utilizado para autenticación.
     * Debe ser único en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    /**
     * Contraseña del usuario.
     * Se recomienda almacenar de forma cifrada en producción.
     */
    @Column(nullable = false)
    private String clave;

    /**
     * Indica el tipo de usuario en el sistema.
     * `true` representa un odontólogo.
     * `false` representa un paciente.
     */
    @Column(nullable = false)
    private Boolean tipoUsuario;
}
