package com.example.demo.controller;

import com.example.demo.config.Constants;
//import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.JwtTokenUtil;
import com.example.demo.config.UserTokenModel;
import com.example.demo.model.AuthToken;
import com.example.demo.model.LoginUser;
import com.example.demo.service.UserService;
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
@RequestMapping("/api/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService, @Qualifier("userDetailsService") UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    public UserTokenModel register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getLogin());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, loginUser.getPassword(), userDetails.getAuthorities()
        );

        authenticationManager.authenticate(authenticationToken);

        if (authenticationToken.isAuthenticated())
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        final String token = jwtTokenUtil.generateToken(authenticationToken);


        return new UserTokenModel(userService.getUserByUsername(loginUser.getLogin()), new AuthToken(token));
    }

    //дата создания токена
    @GetMapping(value = "/expDate")
    public Date GetExpDate(@PathVariable String token) {
        token = token.replace(Constants.TOKEN_PREFIX, "");
        return jwtTokenUtil.getExpirationDateFromToken(token);
    }

}
