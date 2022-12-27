package chegamais.com.chagamais.controller;

import chegamais.com.chagamais.controller.DTO.LoginDTO;
import chegamais.com.chagamais.controller.Form.UsuarioForm;
import chegamais.com.chagamais.controller.Response.AuthResponse;
import chegamais.com.chagamais.repository.RoleRepository;
import chegamais.com.chagamais.security.jwt.JWTAuthenticationFilter;
import chegamais.com.chagamais.security.jwt.JWTAuthorizationFilter;
import chegamais.com.chagamais.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UsuarioService usuarioService;
    private JWTAuthorizationFilter jwtAuthorizationFilter;
    private RoleRepository roleRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UsuarioService usuarioService, JWTAuthorizationFilter jwtAuthorizationFilter, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UsuarioForm usuarioForm) {
        // mover para a lógica de serviço
        if (this.usuarioService.checksEmail(usuarioForm.getEmail()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado");
        this.usuarioService.adicionar(usuarioForm.converterParaDTO());
        return new ResponseEntity<>("Usuário cadastrado com sucesso", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
        // mover para a lógica de serviço
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwtAuthorizationFilter.generateJWTToken(auth);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }
}
