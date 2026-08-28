package com.uade.tpo.marketplace.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.marketplace.entity.dto.request.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;
import com.uade.tpo.marketplace.service.auth.IAuthenticationService;

@RestController
@RequestMapping("Auth")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UsuarioRequest usuarioRequest)
            throws UsuarioDuplicateException {
        return ResponseEntity.ok(authenticationService.register(usuarioRequest));
    }

    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
