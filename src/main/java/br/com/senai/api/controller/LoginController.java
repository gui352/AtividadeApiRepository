package br.com.senai.api.controller;

import br.com.senai.api.assembler.UsuarioAssembler;
import br.com.senai.api.model.input.RoleUsuarioInputDTO;
import br.com.senai.api.model.input.UsuarioInputDOT;
import br.com.senai.domain.model.AuthenticationResponse;
import br.com.senai.domain.model.Usuario;
import br.com.senai.security.ImplementsUserDetailsService;
import br.com.senai.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    private AuthenticationManager authenticationManager;
    private ImplementsUserDetailsService implementsUserDetailsService;
    private JWTUtil jwtUtil;
    private UsuarioAssembler usuarioAssembler;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UsuarioInputDOT usuarioInputDOT) throws  Exception{
        Usuario usuario = usuarioAssembler.toEntity(usuarioInputDOT);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
            );
        }catch (BadCredentialsException ex){
            throw new Exception("Usuario ou senha invalido",ex);
        }

        final UserDetails userDetails = implementsUserDetailsService.loadUserByUsername(
                usuario.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, usuarioAssembler.toModel(usuario)));

    }

}
