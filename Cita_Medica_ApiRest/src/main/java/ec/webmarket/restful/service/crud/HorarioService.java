package ec.webmarket.restful.service.crud;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.persistence.HorarioRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

/**
 * Servicio para la gestión de horarios.
 * Proporciona métodos para buscar, convertir y gestionar horarios en la base de datos.
 */
@Service
public class HorarioService extends GenericCrudServiceImpl<Horario, HorarioDTO> {

    @Autowired
    private HorarioRepository repository; // Repositorio para interactuar con la base de datos.

    private ModelMapper modelMapper = new ModelMapper(); // Mapper para convertir entre DTO y entidad.

    /**
     * Busca un horario basado en el DTO proporcionado.
     * @param dto DTO del horario a buscar.
     * @return Un Optional con la entidad Horario si se encuentra.
     */
    @Override
    public Optional<Horario> find(HorarioDTO dto) {
        return repository.findById(dto.getId());
    }

    /**
     * Busca horarios por su disponibilidad.
     * @param disponibilidad Booleano que indica si el horario está disponible.
     * @return Lista de HorarioDTO con los horarios que coinciden.
     */
    public List<HorarioDTO> findByDisponibilidad(Boolean disponibilidad) {
        List<Horario> horarios = repository.findByDisponibilidad(disponibilidad);
        return horarios.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Busca un horario por su ID.
     * @param id Identificador del horario.
     * @return Un Optional con HorarioDTO si se encuentra.
     */
    public Optional<HorarioDTO> findById(Long id) {
        Optional<Horario> horario = repository.findById(id);
        return horario.map(this::mapToDto);
    }

    /**
     * Busca horarios por el ID del odontólogo asociado.
     * @param odontologoId Identificador del odontólogo.
     * @return Lista de HorarioDTO con los horarios asignados al odontólogo.
     */
    public List<HorarioDTO> findByOdontologoId(Long odontologoId) {
        List<Horario> horarios = repository.findByOdontologo_Id(odontologoId);
        return horarios.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Busca horarios por fecha.
     * @param fecha Fecha del horario.
     * @return Lista de HorarioDTO con los horarios encontrados en esa fecha.
     */
    public List<HorarioDTO> findByFecha(LocalDate fecha) {
        List<Horario> horarios = repository.findByFecha(fecha);
        return horarios.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    /**
     * Convierte un DTO de horario en su entidad correspondiente.
     * @param dto DTO de horario.
     * @return Entidad Horario.
     */
    @Override
    public Horario mapToDomain(HorarioDTO dto) {
        return modelMapper.map(dto, Horario.class);
    }

    /**
     * Convierte una entidad de horario en su DTO correspondiente.
     * @param domain Entidad Horario.
     * @return DTO de Horario.
     */
    @Override
    public HorarioDTO mapToDto(Horario domain) {
        return modelMapper.map(domain, HorarioDTO.class);
    }
}
