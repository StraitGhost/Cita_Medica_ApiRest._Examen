package ec.webmarket.restful.service.crud;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.webmarket.restful.domain.Cita;
import ec.webmarket.restful.domain.Horario;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.persistence.CitaRepository;
import ec.webmarket.restful.service.GenericCrudServiceImpl;

/**
 * Servicio para la gestión de citas odontológicas.
 * Extiende de GenericCrudServiceImpl y proporciona métodos para la manipulación de citas en la base de datos.
 */
@Service
public class CitaService extends GenericCrudServiceImpl<Cita, CitaDTO> {

    @Autowired
    private CitaRepository repository; // Repositorio para interactuar con la base de datos.

    private ModelMapper modelMapper = new ModelMapper(); // Mapper para convertir entre DTO y entidad.

    /**
     * Busca una cita basada en el DTO proporcionado.
     * @param dto DTO de la cita a buscar.
     * @return Un Optional con la entidad Cita si se encuentra.
     */
    @Override
    public Optional<Cita> find(CitaDTO dto) {
        return repository.findById(dto.getId());
    }

    /**
     * Busca todas las citas asociadas a un paciente específico.
     * @param pacienteId Identificador del paciente.
     * @return Lista de citas del paciente.
     */
    public List<Cita> findByPaciente(Long pacienteId) {
        return repository.findByPacienteId(pacienteId);
    }

    /**
     * Busca todas las citas asociadas a un odontólogo específico.
     * @param odontologoId Identificador del odontólogo.
     * @return Lista de citas del odontólogo.
     */
    public List<Cita> findByOdontologo(Long odontologoId) {
        return repository.findByOdontologoId(odontologoId);
    }

    /**
     * Busca citas en un horario específico.
     * @param horario Horario en el que se buscan las citas.
     * @return Lista de CitaDTO con las citas en ese horario.
     */
    public List<CitaDTO> findByHorario(Horario horario) {
        return repository.findByHorario(horario)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    /**
     * Busca citas por fecha y hora específica.
     * @param fechaYHora Fecha y hora en que se programó la cita.
     * @return Lista de CitaDTO con las citas en esa fecha y hora.
     */
    public List<CitaDTO> findByFechaYHora(LocalDateTime fechaYHora) {
        return repository.findByFechayHora(fechaYHora)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    /**
     * Busca citas por estado (Ejemplo: "pendiente", "cancelada", "completada").
     * @param estado Estado de la cita.
     * @return Lista de CitaDTO con las citas que coinciden con el estado.
     */
    public List<CitaDTO> findByEstado(String estado) {
        return repository.findByEstado(estado)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    /**
     * Busca citas por el motivo de consulta.
     * @param motivo Motivo de la cita.
     * @return Lista de CitaDTO con las citas que coinciden con el motivo.
     */
    public List<CitaDTO> findByMotivo(String motivo) {
        return repository.findByMotivo(motivo)
                        .stream()
                        .map(this::mapToDto)
                        .collect(Collectors.toList());
    }

    /**
     * Elimina una cita por su ID.
     * @param id Identificador de la cita a eliminar.
     * @throws NoSuchElementException si la cita no existe.
     */
    public void delete(Long id) {
        Optional<Cita> cita = repository.findById(id);
        if (cita.isPresent()) {
            repository.delete(cita.get());
        } else {
            throw new NoSuchElementException("Cita con ID " + id + " no encontrada");
        }
    }

    /**
     * Convierte un DTO de cita en su entidad correspondiente.
     * @param dto DTO de la cita.
     * @return Entidad Cita.
     */
    @Override
    public Cita mapToDomain(CitaDTO dto) {
        return modelMapper.map(dto, Cita.class);
    }

    /**
     * Convierte una entidad de cita en su DTO correspondiente.
     * @param domain Entidad Cita.
     * @return DTO de Cita.
     */
    @Override
    public CitaDTO mapToDto(Cita domain) {
        return modelMapper.map(domain, CitaDTO.class);
    }
}

