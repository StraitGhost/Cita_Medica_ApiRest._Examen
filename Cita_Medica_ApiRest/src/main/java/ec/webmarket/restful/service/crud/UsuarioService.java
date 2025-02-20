package ec.webmarket.restful.service.crud;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.webmarket.restful.domain.Usuario;
import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.persistence.UsuarioRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

import java.util.Optional;

/**
 * Servicio para la gestión de usuarios.
 * Proporciona operaciones CRUD y métodos específicos para autenticación y asignación de roles.
 */
@Service
public class UsuarioService extends GenericCrudServiceImpl<Usuario, UsuarioDTO> {

    @Autowired
    private UsuarioRepository repository; // Repositorio para la interacción con la base de datos.

    private final ModelMapper modelMapper = new ModelMapper(); // Mapper para convertir entre DTO y entidad.

    /**
     * Busca un usuario basado en el DTO proporcionado.
     * @param dto DTO del usuario a buscar.
     * @return Un Optional con la entidad Usuario si se encuentra.
     */
    @Override
    public Optional<Usuario> find(UsuarioDTO dto) {
        return repository.findById(dto.getId());
    }

    /**
     * Registra un nuevo usuario sin cifrado de contraseña.
     * @param dto DTO del usuario a registrar.
     * @return UsuarioDTO con los datos guardados.
     */
    public UsuarioDTO register(UsuarioDTO dto) {
        Usuario usuario = mapToDomain(dto);
        return mapToDto(repository.save(usuario));
    }

    /**
     * Autentica un usuario verificando su nombre de usuario y contraseña.
     * (Nota: Actualmente no implementa cifrado, lo cual puede representar un riesgo de seguridad).
     * @param nombreUsuario Nombre de usuario del usuario.
     * @param clave Contraseña del usuario.
     * @return Un Optional con UsuarioDTO si las credenciales son correctas.
     */
    public Optional<UsuarioDTO> authenticate(String nombreUsuario, String clave) {
        Optional<Usuario> usuario = repository.findByNombreUsuarioAndClave(nombreUsuario, clave);
        return usuario.map(this::mapToDto);
    }

    /**
     * Actualiza la contraseña de un usuario.
     * (Nota: Se recomienda cifrar la contraseña antes de almacenarla en la base de datos).
     * @param id Identificador del usuario.
     * @param newPassword Nueva contraseña a establecer.
     * @return true si la contraseña fue actualizada, false si el usuario no fue encontrado.
     */
    public boolean updatePassword(Long id, String newPassword) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setClave(newPassword);
            repository.save(usuario.get());
            return true;
        }
        return false;
    }

    /**
     * Asigna un rol al usuario (paciente u odontólogo).
     * @param id Identificador del usuario.
     * @param tipoUsuario Booleano que indica el tipo de usuario (true para odontólogo, false para paciente).
     * @return true si el rol fue asignado correctamente, false si el usuario no fue encontrado.
     */
    public boolean assignRole(Long id, Boolean tipoUsuario) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setTipoUsuario(tipoUsuario);
            repository.save(usuario.get());
            return true;
        }
        return false;
    }

    /**
     * Convierte un DTO de usuario en su entidad correspondiente.
     * @param dto DTO del usuario.
     * @return Entidad Usuario.
     */
    @Override
    public Usuario mapToDomain(UsuarioDTO dto) {
        return modelMapper.map(dto, Usuario.class);
    }

    /**
     * Convierte una entidad de usuario en su DTO correspondiente.
     * @param domain Entidad Usuario.
     * @return DTO de Usuario.
     */
    @Override
    public UsuarioDTO mapToDto(Usuario domain) {
        return modelMapper.map(domain, UsuarioDTO.class);
    }
}

