package ec.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Odontologo;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.persistence.OdontologoRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

/**
 * Servicio para la gestión de odontólogos.
 * Extiende de GenericCrudServiceImpl para proporcionar operaciones CRUD genéricas.
 */
@Service
public class OdontologoService extends GenericCrudServiceImpl<Odontologo, OdontologoDTO> {
    
    @Autowired
    private OdontologoRepository repository; // Repositorio para interactuar con la base de datos.
    
    private final ModelMapper modelMapper = new ModelMapper(); // Mapper para convertir entre DTO y entidad.

    /**
     * Busca un odontólogo basado en el DTO proporcionado.
     * @param dto DTO del odontólogo a buscar.
     * @return Un Optional con la entidad Odontologo si se encuentra.
     */
    @Override
    public Optional<Odontologo> find(OdontologoDTO dto) {
        return repository.findById(dto.getId());
    }

    /**
     * Obtiene todos los odontólogos almacenados en la base de datos.
     * @return Lista de OdontologoDTO.
     */
    public List<OdontologoDTO> findAll() {
        List<Odontologo> odontologos = repository.findAll();
        return odontologos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Busca un odontólogo por su ID.
     * @param id Identificador del odontólogo.
     * @return Un Optional con OdontologoDTO si se encuentra.
     */
    public Optional<OdontologoDTO> findById(Long id) {
        Optional<Odontologo> odontologo = repository.findById(id);
        return odontologo.map(this::mapToDto);
    }

    /**
     * Busca un odontólogo por el ID del usuario asociado.
     * @param usuarioId Identificador del usuario.
     * @return Un Optional con OdontologoDTO si se encuentra.
     */
    public Optional<OdontologoDTO> findByUsuarioId(Long usuarioId) {
        Optional<Odontologo> odontologo = repository.findByUsuario_Id(usuarioId);
        return odontologo.map(this::mapToDto);
    }

    /**
     * Busca un odontólogo por el nombre de usuario asociado.
     * @param nombreUsuario Nombre de usuario.
     * @return Un Optional con OdontologoDTO si se encuentra.
     */
    public Optional<OdontologoDTO> findByUsuarioNombreUsuario(String nombreUsuario) {
        Optional<Odontologo> odontologo = repository.findByUsuario_NombreUsuario(nombreUsuario);
        return odontologo.map(this::mapToDto);
    }

    /**
     * Busca un odontólogo por su cédula.
     * @param cedula Número de cédula del odontólogo.
     * @return Un Optional con OdontologoDTO si se encuentra.
     */
    public Optional<OdontologoDTO> findByCedula(String cedula) {
        Optional<Odontologo> odontologo = repository.findByCedula(cedula);
        return odontologo.map(this::mapToDto);
    }

    /**
     * Guarda un odontólogo en la base de datos.
     * @param dto DTO del odontólogo a guardar.
     * @return OdontologoDTO con los datos guardados.
     */
    public OdontologoDTO save(OdontologoDTO dto) {
        Odontologo odontologo = mapToDomain(dto);
        odontologo = repository.save(odontologo);
        return mapToDto(odontologo);
    }

    /**
     * Elimina un odontólogo por su ID.
     * @param id Identificador del odontólogo a eliminar.
     * @return true si la eliminación fue exitosa, false si el odontólogo no existe.
     */
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Convierte un DTO de odontólogo en su entidad correspondiente.
     * @param dto DTO de odontólogo.
     * @return Entidad Odontologo.
     */
    @Override
    public Odontologo mapToDomain(OdontologoDTO dto) {
        return modelMapper.map(dto, Odontologo.class);
    }

    /**
     * Convierte una entidad de odontólogo en su DTO correspondiente.
     * @param domain Entidad Odontologo.
     * @return DTO de Odontologo.
     */
    @Override
    public OdontologoDTO mapToDto(Odontologo domain) {
        return modelMapper.map(domain, OdontologoDTO.class);
    }
}

