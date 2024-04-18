package com.example.demo.security.controller;

import com.example.demo.security.dto.AuthReqDTO;
import com.example.demo.security.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityContoller {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public String authenticate(@RequestBody AuthReqDTO authReqDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authReqDTO.getUserName(), authReqDTO.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authReqDTO.getUserName());
        } else {
            throw new UsernameNotFoundException("Invalid user");
        }
    }
}
