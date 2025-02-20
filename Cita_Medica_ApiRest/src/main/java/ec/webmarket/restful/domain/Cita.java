package ec.webmarket.restful.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa una cita en el sistema, que es programada por un paciente 
 * con un odontólogo en un horario específico.
 */
@Getter
@Setter
@Entity
public class Cita {

    /**
     * Identificador único de la cita.
     * Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    /**
     * Paciente que solicita la cita.
     * Relación muchos a uno con la entidad Paciente.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /**
     * Odontólogo que atenderá la cita.
     * Relación muchos a uno con la entidad Odontólogo.
     */
    @ManyToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;

    /**
     * Horario asignado a la cita.
     * Relación uno a uno con la entidad Horario.
     */
    @OneToOne
    @JoinColumn(name = "horario_id", nullable = false)
    private Horario horario;

    /**
     * Fecha y hora exacta de la cita.
     * Debe ser única para evitar solapamientos.
     */
    @Column(nullable = false, unique = true)
    private LocalDateTime fechayHora;

    /**
     * Estado actual de la cita (Ejemplo: "Pendiente", "Confirmada", "Cancelada").
     * Debe ser único, pero esto podría causar problemas en la base de datos.
     */
    @Column(nullable = false, unique = true)
    private String estado;

    /**
     * Motivo de la cita, indicando la razón de la consulta.
     * Debe ser único, lo cual podría no ser adecuado para esta propiedad.
     */
    @Column(nullable = false, unique = true)
    private String motivo;
}
