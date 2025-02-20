package ec.webmarket.restful.persistence;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ec.webmarket.restful.domain.Odontologo;

/**
 * Repositorio para la entidad Odontologo.
 * Proporciona métodos de consulta para recuperar odontólogos según diferentes criterios.
 */
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {

    /**
     * Busca una lista de odontólogos por su nombre.
     * @param nombre Nombre del odontólogo.
     * @return Lista de odontólogos con el nombre especificado.
     */
    List<Odontologo> findByNombre(String nombre);

    /**
     * Busca una lista de odontólogos por su apellido.
     * @param apellido Apellido del odontólogo.
     * @return Lista de odontólogos con el apellido especificado.
     */
    List<Odontologo> findByApellido(String apellido);

    /**
     * Busca una lista de odontólogos por su número de teléfono.
     * @param telefono Número de teléfono del odontólogo.
     * @return Lista de odontólogos con el teléfono especificado.
     */
    List<Odontologo> findByTelefono(String telefono);

    /**
     * Busca una lista de odontólogos por su dirección de correo electrónico.
     * @param email Correo electrónico del odontólogo.
     * @return Lista de odontólogos con el correo electrónico especificado.
     */
    List<Odontologo> findByEmail(String email);

    /**
     * Busca un odontólogo por el ID de su usuario asociado.
     * @param usuarioId ID del usuario asociado al odontólogo.
     * @return Un Optional con el odontólogo si se encuentra, de lo contrario, vacío.
     */
    Optional<Odontologo> findByUsuario_Id(Long usuarioId);

    /**
     * Busca un odontólogo por el nombre de usuario de su usuario asociado.
     * @param nombreUsuario Nombre de usuario asociado al odontólogo.
     * @return Un Optional con el odontólogo si se encuentra, de lo contrario, vacío.
     */
    Optional<Odontologo> findByUsuario_NombreUsuario(String nombreUsuario);

    /**
     * Busca un odontólogo por su número de cédula.
     * @param cedula Número de cédula del odontólogo.
     * @return Un Optional con el odontólogo si se encuentra, de lo contrario, vacío.
     */
    Optional<Odontologo> findByCedula(String cedula);
}
