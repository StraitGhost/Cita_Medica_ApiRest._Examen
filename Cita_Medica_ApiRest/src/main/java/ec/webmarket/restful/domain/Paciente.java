package ec.webmarket.restful.domain;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

/**
 * Representa a un paciente dentro del sistema.
 */
@Getter
@Setter
@Entity
public class Paciente {

    /**
     * Identificador único del paciente.
     * Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    /**
     * Número de cédula del paciente.
     * Debe ser único en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String cedula;

    /**
     * Nombre del paciente.
     */
    @Column(nullable = false)
    private String nombre;

    /**
     * Apellido del paciente.
     */
    @Column(nullable = false)
    private String apellido;

    /**
     * Número de teléfono del paciente.
     */
    @Column(nullable = false)
    private String telefono;

    /**
     * Correo electrónico del paciente.
     * Debe ser único en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Fecha de nacimiento del paciente.
     * No puede ser modificada después de la creación.
     */
    @Column(nullable = false, updatable = false)
    private LocalDate fechaNacimiento;

    /**
     * Dirección de residencia del paciente.
     */
    @Column(nullable = false)
    private String direccion;

    /**
     * Relación uno a uno con la entidad Usuario.
     * Un paciente tiene asociado un usuario en el sistema.
     * Se utiliza `CascadeType.ALL` para que los cambios en `Paciente` afecten a `Usuario`.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;
}
