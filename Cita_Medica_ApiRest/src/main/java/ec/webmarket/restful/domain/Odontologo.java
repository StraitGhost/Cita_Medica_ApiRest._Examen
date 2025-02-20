package ec.webmarket.restful.domain;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa a un odontólogo dentro del sistema.
 */
@Getter
@Setter
@Entity
public class Odontologo {

    /**
     * Identificador único del odontólogo.
     * Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    /**
     * Número de cédula del odontólogo.
     * Debe ser único en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String cedula;

    /**
     * Nombre del odontólogo.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Apellido del odontólogo.
     */
    @Column(nullable = false)
    private String apellido;

    /**
     * Número de teléfono del odontólogo.
     * Debe ser único en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String telefono;

    /**
     * Correo electrónico del odontólogo.
     * Debe ser único en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Dirección del odontólogo.
     */
    @Column(nullable = false)
    private String direccion;

    /**
     * Relación uno a uno con la entidad Usuario.
     * Un odontólogo tiene asociado un usuario en el sistema.
     * Se utiliza `CascadeType.ALL` para que los cambios en `Odontologo` afecten a `Usuario`.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;
}
