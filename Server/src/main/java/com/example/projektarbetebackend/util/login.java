package com.example.projektarbetebackend.util;

import com.example.projektarbetebackend.Controllers.UserController;
import com.example.projektarbetebackend.Models.AuthenticationResponse;
import com.example.projektarbetebackend.Models.User;
import com.example.projektarbetebackend.Repositories.UserRepository;
import com.example.projektarbetebackend.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@RestController
public class login {
    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService userDetailsService;

    private final JwtUtil jwtTokenUtil;

    @Autowired
    UserRepository userRepository;

    public login(AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {

        if(BCrypt.checkpw(user.getPassword(), userRepository.findByUsername(user.getUsername()).getPassword())){
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(user.getUsername());
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }else{
            throw new Exception("Incorrect username or password");
        }

    }

}
