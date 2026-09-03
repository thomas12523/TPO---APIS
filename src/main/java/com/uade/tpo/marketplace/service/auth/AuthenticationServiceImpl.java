package com.uade.tpo.marketplace.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uade.tpo.marketplace.config.JwtService;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationRequest;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationResponse;
import com.uade.tpo.marketplace.entity.Usuario;
import com.uade.tpo.marketplace.entity.dto.request.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.repository.IUsuarioRepository;
import com.uade.tpo.marketplace.service.usuario.IUsuarioService;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UsuarioRequest usuarioRequest) throws UsuarioDuplicateException {
        Usuario usuario = usuarioService.crearUsuario(usuarioRequest);
        String jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getContrasenia()));

        Usuario usuario = usuarioRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow();
        String jwtToken = jwtService.generateToken(usuario);
        return AuthenticationResponse.builder().accessToken(jwtToken).build();
    }
}
