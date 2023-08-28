package com.task.task.controller;


import com.task.task.auth.JwtRequest;
import com.task.task.auth.JwtResponse;
import com.task.task.services.JwtUserDetailsService;
import com.task.task.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @Autowired
        private JwtUserDetailsService jwtUserDetailsService;

        @PostMapping("/authenticate")
        public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }

        private void authenticate(String username, String password) throws Exception {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            } catch (DisabledException e) {
                throw new Exception("User disabled", e);
            } catch (BadCredentialsException e) {
                throw new Exception("Invalid credentials", e);
            }
        }
}
