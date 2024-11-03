package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configrations.jwt.JWTUtilites;
import com.example.demo.configrations.user.UserDeatailsApp;
import com.example.demo.model.User;
import com.example.demo.request.AddUserRequest;
import com.example.demo.request.ChangePassword;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.JwtResponse;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController


@RequestMapping("/users")

public class UserController {
    @Autowired
    private JWTUtilites jwtUtils;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserService userService;

    
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateTokenForUser(authentication);
            UserDeatailsApp userDetails = (UserDeatailsApp) authentication.getPrincipal(); 
             JwtResponse jwtResponse = new JwtResponse(userDetails.getId(), jwt);
            return ResponseEntity.ok(jwtResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.ok("");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> createUser(@RequestBody AddUserRequest request) {
        try {
            User user = userService.createUser(request);
            return ResponseEntity.ok( user);
        } catch (Exception e) {
            return ResponseEntity.status(CONFLICT).body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody ChangePassword request, @PathVariable Long id) {
        try {
            User user = userService.updateUser(request, id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
           return ResponseEntity.status(NOT_FOUND).body("not found");
        }
    }

    @DeleteMapping("/de/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted");
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body("not found");
        }
    }
}
