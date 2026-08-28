package com.uade.tpo.marketplace.service.auth;

import com.uade.tpo.marketplace.controllers.auth.AuthenticationRequest;
import com.uade.tpo.marketplace.controllers.auth.AuthenticationResponse;
import com.uade.tpo.marketplace.entity.dto.request.UsuarioRequest;
import com.uade.tpo.marketplace.exceptions.UsuarioDuplicateException;

public interface IAuthenticationService {

    public AuthenticationResponse register(UsuarioRequest usuarioRequest) throws UsuarioDuplicateException;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
