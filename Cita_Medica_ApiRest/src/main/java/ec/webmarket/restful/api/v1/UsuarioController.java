package ec.webmarket.restful.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ec.webmarket.restful.common.ApiConstants;
import ec.webmarket.restful.dto.v1.UsuarioDTO;
import ec.webmarket.restful.service.crud.UsuarioService;
import java.util.Optional;

/**
 * Controlador REST para la gestión de usuarios en el sistema.
 * Permite registrar, autenticar y gestionar roles y credenciales de los usuarios.
 */
@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_USUARIO })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param dto Datos del usuario a registrar.
     * @return ResponseEntity con el usuario registrado y código de estado HTTP 200.
     */
    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody UsuarioDTO dto) {
        UsuarioDTO savedUser = usuarioService.register(dto);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Autentica un usuario con sus credenciales.
     *
     * @param nombreUsuario Nombre de usuario.
     * @param clave Contraseña del usuario.
     * @return ResponseEntity con los datos del usuario autenticado o un código de estado HTTP 401 si la autenticación falla.
     */
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestParam String nombreUsuario, @RequestParam String clave) {
        Optional<UsuarioDTO> user = usuarioService.authenticate(nombreUsuario, clave);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

    /**
     * Asigna un rol específico a un usuario (paciente u odontólogo).
     *
     * @param id ID del usuario.
     * @param tipoUsuario Tipo de usuario (true = odontólogo, false = paciente).
     * @return ResponseEntity con mensaje de éxito o código de estado HTTP 404 si el usuario no es encontrado.
     */
    @PutMapping("/assignRole/{id}")
    public ResponseEntity<String> assignRole(@PathVariable Long id, @RequestParam Boolean tipoUsuario) {
        boolean success = usuarioService.assignRole(id, tipoUsuario);
        return success ? ResponseEntity.ok("Rol asignado correctamente") : ResponseEntity.notFound().build();
    }

    /**
     * Cambia la contraseña de un usuario registrado.
     *
     * @param id ID del usuario.
     * @param newPassword Nueva contraseña del usuario.
     * @return ResponseEntity con mensaje de éxito o código de estado HTTP 404 si el usuario no es encontrado.
     */
    @PutMapping("/updatePassword/{id}")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestParam String newPassword) {
        boolean success = usuarioService.updatePassword(id, newPassword);
        return success ? ResponseEntity.ok("Contraseña actualizada correctamente") : ResponseEntity.notFound().build();
    }
}
