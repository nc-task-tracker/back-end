package com.example.demo.controller;


import com.example.demo.config.Constants;
//import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.model.AuthToken;
import com.example.demo.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Date;

@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/generate-token")
    public AuthToken register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        UserDetails userDetails = userService.loadUserByUsername(loginUser.getLogin());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, loginUser.getPassword(), userDetails.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        final String token = jwtTokenUtil.generateToken(authenticationToken);
        return new AuthToken(token);
    }

    @GetMapping(value = "/expDate")
    public Date GetExpDate(@PathVariable String token) {
        token = token.replace(Constants.TOKEN_PREFIX, "");
        return jwtTokenUtil.getExpirationDateFromToken(token);
    }

}
