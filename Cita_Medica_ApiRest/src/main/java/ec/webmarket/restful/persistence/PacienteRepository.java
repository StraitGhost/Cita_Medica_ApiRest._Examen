package ec.webmarket.restful.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.Paciente;

/**
 * Repositorio para la entidad Paciente.
 * Proporciona métodos de consulta para recuperar pacientes según diferentes criterios.
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    /**
     * Busca una lista de pacientes por su nombre.
     * @param nombre Nombre del paciente.
     * @return Lista de pacientes con el nombre especificado.
     */
    List<Paciente> findByNombre(String nombre);

    /**
     * Busca una lista de pacientes por su apellido.
     * @param apellido Apellido del paciente.
     * @return Lista de pacientes con el apellido especificado.
     */
    List<Paciente> findByApellido(String apellido);

    /**
     * Busca una lista de pacientes por su número de teléfono.
     * @param telefono Número de teléfono del paciente.
     * @return Lista de pacientes con el teléfono especificado.
     */
    List<Paciente> findByTelefono(String telefono);

    /**
     * Busca una lista de pacientes por su dirección de correo electrónico.
     * @param email Correo electrónico del paciente.
     * @return Lista de pacientes con el correo electrónico especificado.
     */
    List<Paciente> findByEmail(String email);

    /**
     * Busca una lista de pacientes por su fecha de nacimiento.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @return Lista de pacientes con la fecha de nacimiento especificada.
     */
    List<Paciente> findByFechaNacimiento(LocalDate fechaNacimiento);

    /**
     * Busca una lista de pacientes por su dirección de residencia.
     * @param direccion Dirección del paciente.
     * @return Lista de pacientes con la dirección especificada.
     */
    List<Paciente> findByDireccion(String direccion);

    /**
     * Busca un paciente por el ID de su usuario asociado.
     * @param usuarioId ID del usuario asociado al paciente.
     * @return Un Optional con el paciente si se encuentra, de lo contrario, vacío.
     */
    Optional<Paciente> findByUsuario_Id(Long usuarioId);

    /**
     * Busca un paciente por el nombre de usuario de su usuario asociado.
     * @param nombreUsuario Nombre de usuario asociado al paciente.
     * @return Un Optional con el paciente si se encuentra, de lo contrario, vacío.
     */
    Optional<Paciente> findByUsuario_NombreUsuario(String nombreUsuario);

    /**
     * Busca un paciente por su número de cédula.
     * @param cedula Número de cédula del paciente.
     * @return Un Optional con el paciente si se encuentra, de lo contrario, vacío.
     */
    Optional<Paciente> findByCedula(String cedula);
}
