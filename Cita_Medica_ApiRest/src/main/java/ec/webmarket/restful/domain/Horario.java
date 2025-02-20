package ec.webmarket.restful.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * Representa el horario disponible de un odontólogo en el sistema.
 */
@Getter
@Setter
@Entity
public class Horario {

    /**
     * Identificador único del horario.
     * Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    /**
     * Fecha en la que está disponible el horario.
     */
    @Column(nullable = false)
    private LocalDate fecha;

    /**
     * Hora de inicio del horario.
     * Formato de 24 horas (HH:mm).
     */
    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horaInicio;

    /**
     * Hora de finalización del horario.
     * Formato de 24 horas (HH:mm).
     */
    @JsonFormat(pattern = "HH:mm")
    @Column(nullable = false)
    private LocalTime horafinal;

    /**
     * Indica si el horario está disponible (true) o no (false).
     */
    @Column(nullable = false)
    private Boolean disponibilidad;

    /**
     * Odontólogo al que pertenece este horario.
     * Relación uno a uno con la entidad Odontólogo.
     */
    @OneToOne
    @JoinColumn(name = "odontologo_id", nullable = false)
    private Odontologo odontologo;  
}
