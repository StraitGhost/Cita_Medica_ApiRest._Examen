package ec.webmarket.restful.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ec.webmarket.restful.domain.Usuario;
import java.util.Optional;

/**
 * Repositorio para la entidad Usuario.
 * Proporciona métodos de consulta para recuperar información de los usuarios en la base de datos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario.
     * @param nombreUsuario Nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    /**
     * Busca un usuario por su nombre de usuario y clave.
     * @param nombreUsuario Nombre de usuario del usuario.
     * @param clave Clave del usuario.
     * @return Un Optional con el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findByNombreUsuarioAndClave(String nombreUsuario, String clave);

    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario.
     * @return Un Optional con el usuario si se encuentra, de lo contrario, vacío.
     */
    Optional<Usuario> findById(Long id);
}
