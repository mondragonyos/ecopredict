package com.ecopredict.controller;

import com.ecopredict.dto.UsuarioDTO;
import com.ecopredict.model.Usuario;
import com.ecopredict.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") 
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            if (usuarioDTO.getCorreo() == null || usuarioDTO.getCorreo().trim().isEmpty() ||
                usuarioDTO.getContrasena() == null || usuarioDTO.getContrasena().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Error: El correo y la contraseña son requeridos.");
            }

            if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Error: Este correo electrónico ya se encuentra registrado.");
            }

            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(usuarioDTO.getNombre());
            nuevoUsuario.setCorreo(usuarioDTO.getCorreo().trim());
            nuevoUsuario.setNumHabitantes(usuarioDTO.getNumHabitantes() > 0 ? usuarioDTO.getNumHabitantes() : 1);

            String hashedPassword = "[HASHED_" + usuarioDTO.getContrasena().hashCode() + "]"; 
            nuevoUsuario.setContrasenaHash(hashedPassword);

            usuarioRepository.save(nuevoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado exitosamente en EcoPredict.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado al procesar tu solicitud.");
        }
    }

    // NUEVO MÉTODO AÑADIDO: Manejo de autenticación (Login)
    @PostMapping("/login")
    public ResponseEntity<?> loginUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            if (usuarioDTO.getCorreo() == null || usuarioDTO.getContrasena() == null) {
                return ResponseEntity.badRequest().body("Error: Correo y contraseña requeridos.");
            }

            Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(usuarioDTO.getCorreo().trim());
            
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: El correo electrónico no existe.");
            }

            Usuario usuario = usuarioOpt.get();
            String hashedInput = "[HASHED_" + usuarioDTO.getContrasena().hashCode() + "]";
            
            if (!usuario.getContrasenaHash().equals(hashedInput)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Contraseña incorrecta.");
            }

            // Retorna la entidad para que el HTML guarde el id_usuario en localStorage
            return ResponseEntity.ok(usuario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor al iniciar sesión.");
        }
    }
}