package ec.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.PacienteDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.PacienteService;
import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar pacientes en el sistema.
 * Permite registrar y actualizar la información de los pacientes.
 */
@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_PACIENTE })
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;
    
    /**
     * Registra un nuevo paciente en el sistema.
     *
     * @param pacienteDTO Datos del paciente a registrar.
     * @return ResponseEntity con el paciente creado y código de estado HTTP 201.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, pacienteService.create(pacienteDTO)), HttpStatus.CREATED);
    }
    
    /**
     * Modifica la información personal de un paciente existente.
     *
     * @param pacienteDTO Datos del paciente a actualizar.
     * @return ResponseEntity con el paciente actualizado y código de estado HTTP 200.
     */
    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody PacienteDTO pacienteDTO) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, pacienteService.update(pacienteDTO)), HttpStatus.OK);
    }
}
