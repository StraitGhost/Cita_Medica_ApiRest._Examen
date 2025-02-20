package ec.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Paciente;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.persistence.PacienteRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

/**
 * Servicio para la gestión de pacientes.
 * Proporciona métodos para la manipulación de pacientes en la base de datos.
 */
@Service
public class PacienteService extends GenericCrudServiceImpl<Paciente, PacienteDTO> {

    @Autowired
    private PacienteRepository repository; // Repositorio para interactuar con la base de datos.

    private final ModelMapper modelMapper = new ModelMapper(); // Mapper para convertir entre DTO y entidad.

    /**
     * Busca un paciente basado en el DTO proporcionado.
     * @param dto DTO del paciente a buscar.
     * @return Un Optional con la entidad Paciente si se encuentra.
     */
    @Override
    public Optional<Paciente> find(PacienteDTO dto) {
        return repository.findById(dto.getId());
    }

    /**
     * Obtiene la lista de todos los pacientes registrados.
     * @return Lista de PacienteDTO con la información de los pacientes.
     */
    public List<PacienteDTO> findAll() {
        List<Paciente> pacientes = repository.findAll();
        return pacientes.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Busca un paciente por su ID.
     * @param id Identificador del paciente.
     * @return Un Optional con el PacienteDTO si se encuentra.
     */
    public Optional<PacienteDTO> findById(Long id) {
        Optional<Paciente> paciente = repository.findById(id);
        return paciente.map(this::mapToDto);
    }

    /**
     * Busca un paciente por el ID de su usuario.
     * @param usuarioId Identificador del usuario asociado al paciente.
     * @return Un Optional con el PacienteDTO si se encuentra.
     */
    public Optional<PacienteDTO> findByUsuarioId(Long usuarioId) {
        Optional<Paciente> paciente = repository.findByUsuario_Id(usuarioId);
        return paciente.map(this::mapToDto);
    }

    /**
     * Busca un paciente por el nombre de usuario de su cuenta.
     * @param nombreUsuario Nombre de usuario del paciente.
     * @return Un Optional con el PacienteDTO si se encuentra.
     */
    public Optional<PacienteDTO> findByUsuarioNombreUsuario(String nombreUsuario) {
        Optional<Paciente> paciente = repository.findByUsuario_NombreUsuario(nombreUsuario);
        return paciente.map(this::mapToDto);
    }

    /**
     * Busca un paciente por su número de cédula.
     * @param cedula Número de cédula del paciente.
     * @return Un Optional con el PacienteDTO si se encuentra.
     */
    public Optional<PacienteDTO> findByCedula(String cedula) {
        Optional<Paciente> paciente = repository.findByCedula(cedula);
        return paciente.map(this::mapToDto);
    }

    /**
     * Guarda o actualiza la información de un paciente.
     * @param dto DTO con la información del paciente.
     * @return PacienteDTO con los datos guardados.
     */
    public PacienteDTO save(PacienteDTO dto) {
        Paciente paciente = mapToDomain(dto);
        paciente = repository.save(paciente);
        return mapToDto(paciente);
    }

    /**
     * Elimina un paciente de la base de datos por su ID.
     * @param id Identificador del paciente.
     * @return true si el paciente fue eliminado, false si no se encontró.
     */
    public boolean deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Convierte un DTO de paciente en su entidad correspondiente.
     * @param dto DTO del paciente.
     * @return Entidad Paciente.
     */
    @Override
    public Paciente mapToDomain(PacienteDTO dto) {
        return modelMapper.map(dto, Paciente.class);
    }

    /**
     * Convierte una entidad de paciente en su DTO correspondiente.
     * @param domain Entidad Paciente.
     * @return DTO de Paciente.
     */
    @Override
    public PacienteDTO mapToDto(Paciente domain) {
        return modelMapper.map(domain, PacienteDTO.class);
    }
}
