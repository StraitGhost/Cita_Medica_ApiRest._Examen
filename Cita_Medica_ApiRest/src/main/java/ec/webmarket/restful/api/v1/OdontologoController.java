package ec.webmarket.restful.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.OdontologoDTO;
import ec.webmarket.restful.dto.v1.HorarioDTO;
import ec.webmarket.restful.security.ApiResponseDTO;
import ec.webmarket.restful.service.crud.OdontologoService;
import ec.webmarket.restful.service.crud.HorarioService;
import jakarta.validation.Valid;

/**
 * Controlador REST para gestionar los odontólogos en el sistema.
 * Permite registrar odontólogos y consultar sus citas programadas.
 */
@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_ODONTOLOGO })
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private HorarioService horarioService;  // Servicio para gestionar los horarios de los odontólogos.

    /**
     * Registra un nuevo odontólogo en el sistema.
     *
     * @param dto Datos del odontólogo a registrar.
     * @return ResponseEntity con el odontólogo creado y código de estado HTTP 201.
     */
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody OdontologoDTO dto) {
        return new ResponseEntity<>(new ApiResponseDTO<>(true, odontologoService.create(dto)), HttpStatus.CREATED);
    }

    /**
     * Obtiene los horarios de citas asignados a un odontólogo específico.
     *
     * @param odontologoId ID del odontólogo cuyos horarios se desean consultar.
     * @return ResponseEntity con la lista de horarios del odontólogo.
     */
    @GetMapping("/{odontologoId}/citas")
    public ResponseEntity<?> getCitasByOdontologo(@PathVariable Long odontologoId) {
        List<HorarioDTO> citas = horarioService.findByOdontologoId(odontologoId);
        return new ResponseEntity<>(new ApiResponseDTO<>(true, citas), HttpStatus.OK);
    }
}
