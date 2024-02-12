package com.okten.springdemo.controller;

import com.okten.springdemo.dto.JwtRequest;
import com.okten.springdemo.dto.JwtResponse;
import com.okten.springdemo.service.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtService jwtService;

    @PostMapping("/api/auth/signin")
    public ResponseEntity<JwtResponse> signIn(@RequestBody @Valid JwtRequest jwtRequest) {
        Authentication authentication = UsernamePasswordAuthenticationToken
                .unauthenticated(jwtRequest.getUsername(), jwtRequest.getPassword());
        authenticationManager.authenticate(authentication);

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtService.generateToken(userDetails);

        JwtResponse response = new JwtResponse();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }
}
