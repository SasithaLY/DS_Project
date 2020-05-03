package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.forms.JwtResponse;
import com.example.demo.forms.Login;
import com.example.demo.forms.Register;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
    AuthenticationManager authManager;
 
    @Autowired
    UserRepository userRepo;
 
    @Autowired
    PasswordEncoder encoder;
 
    @Autowired
    JwtProvider jwt;
 
    @PostMapping("/signin")
    public ResponseEntity<?> userAuthenticate(@Valid @RequestBody Login login) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		login.getUsername(),
                		login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = jwt.generateJwtToken(auth);
        return ResponseEntity.ok(new JwtResponse(token));
    }
 
    @PostMapping("/signup")
    public ResponseEntity<String> userSignup(@Valid @RequestBody Register reg) {
        if(userRepo.existsByUsername(reg.getUsername())) {
            return new ResponseEntity<String>("Failed -> Username Exist!",
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user
        User u = new User(reg.getUsername(),
               encoder.encode(reg.getPassword()), reg.getRole(), reg.getPhone(), reg.getEmail(), reg.isActive());
        userRepo.save(u);
        return ResponseEntity.ok().body("Successfully Registered The User!");
    }
}
