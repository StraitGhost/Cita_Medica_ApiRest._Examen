package ec.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.CitaDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.CitaService;
import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar las citas dentro del sistema.
 * Permite la creación, modificación, eliminación y consulta de citas
 * tanto para pacientes como para odontólogos.
 */
@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_CITA })
public class CitaController {

    @Autowired
    private CitaService citaService;

    /**
     * Obtiene todas las citas asociadas a un paciente específico.
     *
     * @param pacienteId ID del paciente cuyas citas se desean consultar.
     * @return ResponseEntity con la lista de citas del paciente.
     */
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<?> getCitasByPaciente(@PathVariable Long pacienteId) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.findByPaciente(pacienteId)), HttpStatus.OK);
    }

    /**
     * Obtiene todas las citas asociadas a un odontólogo específico.
     *
     * @param odontologoId ID del odontólogo cuyas citas se desean consultar.
     * @return ResponseEntity con la lista de citas del odontólogo.
     */
    @GetMapping("/odontologo/{odontologoId}")
    public ResponseEntity<?> getCitasByOdontologo(@PathVariable Long odontologoId) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.findByOdontologo(odontologoId)), HttpStatus.OK);
    }

    /**
     * Registra una nueva cita en el sistema.
     *
     * @param citaDTO Datos de la cita a registrar, validados previamente.
     * @return ResponseEntity con la cita creada y el código de estado HTTP 201.
     */
    @PostMapping
    public ResponseEntity<?> crearCita(@RequestBody @Valid CitaDTO citaDTO) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.create(citaDTO)), HttpStatus.CREATED);
    }

    /**
     * Modifica una cita existente, permitiendo su actualización o reprogramación.
     *
     * @param id      ID de la cita a modificar.
     * @param citaDTO Datos actualizados de la cita.
     * @return ResponseEntity con la cita actualizada y código de estado HTTP 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCita(@PathVariable Long id, @RequestBody @Valid CitaDTO citaDTO) {
        citaDTO.setId(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citaService.update(citaDTO)), HttpStatus.OK);
    }

    /**
     * Elimina una cita del sistema.
     *
     * @param id ID de la cita a eliminar.
     * @return ResponseEntity con un mensaje de confirmación y código de estado HTTP 200.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelarCita(@PathVariable Long id) {
        citaService.delete(id);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, "Cita eliminada correctamente."), HttpStatus.OK);
    }
}
