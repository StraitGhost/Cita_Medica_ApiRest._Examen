package ec.webmarket.restful.api.v1;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.HorarioService;

/**
 * Controlador REST para gestionar los horarios de los odontólogos en el sistema.
 * Permite la creación, modificación y consulta de horarios disponibles.
 */
@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_HORARIO })
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    /**
     * Registra un nuevo horario en el sistema.
     *
     * @param horarioDTO Datos del horario a registrar.
     * @return ResponseEntity con el horario creado y código de estado HTTP 201.
     */
    @PostMapping
    public ResponseEntity<?> crearHorario(@RequestBody HorarioDTO horarioDTO) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarioService.create(horarioDTO)), HttpStatus.CREATED);
    }

    /**
     * Bloquea un horario específico, cambiando su disponibilidad a "false".
     *
     * @param id ID del horario a bloquear.
     * @return ResponseEntity con el horario actualizado o un mensaje de error si no se encuentra.
     */
    @PutMapping("/{id}/bloquear")
    public ResponseEntity<?> bloquearHorario(@PathVariable Long id) {
        HorarioDTO horario = horarioService.findById(id).orElse(null);
        if (horario == null) {
            return new ResponseEntity<>(new ApiResponseDTO<>(false, "Horario no encontrado"), HttpStatus.NOT_FOUND);
        }
        horario.setDisponibilidad(false); // Bloquear el horario
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarioService.update(horario)), HttpStatus.OK);
    }

    /**
     * Obtiene una lista de horarios filtrados por disponibilidad.
     *
     * @param estado Estado de disponibilidad (true: disponible, false: no disponible).
     * @return ResponseEntity con la lista de horarios filtrados.
     */
    @GetMapping("/disponibilidad/{estado}")
    public ResponseEntity<?> obtenerHorariosPorDisponibilidad(@PathVariable Boolean estado) {
        List<HorarioDTO> horarios = horarioService.findByDisponibilidad(estado);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }

    /**
     * Obtiene los horarios asignados a un odontólogo específico.
     *
     * @param odontologoId ID del odontólogo cuyos horarios se desean consultar.
     * @return ResponseEntity con la lista de horarios del odontólogo.
     */
    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<?> obtenerHorariosPorOdontologo(@PathVariable Long odontologoId) {
        List<HorarioDTO> horarios = horarioService.findByOdontologoId(odontologoId);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }

    /**
     * Obtiene los horarios registrados para una fecha específica.
     *
     * @param fecha Fecha en formato "yyyy-MM-dd" a consultar.
     * @return ResponseEntity con la lista de horarios en esa fecha.
     */
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> obtenerHorariosPorFecha(@PathVariable String fecha) {
        LocalDate localDate = LocalDate.parse(fecha);
        List<HorarioDTO> horarios = horarioService.findByFecha(localDate);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, horarios), HttpStatus.OK);
    }
}
