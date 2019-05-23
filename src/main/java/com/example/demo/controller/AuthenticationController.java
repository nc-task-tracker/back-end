package com.example.demo.controller;

import com.example.demo.config.UserTokenModel;
import com.example.demo.dto.UserDto;
import com.example.demo.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(value = "/login")
    public UserTokenModel login(@RequestBody UserDto loginUser) throws AuthenticationException {
        return authenticationService.login(loginUser);
    }

    @PostMapping(value = "/register")
    public UserTokenModel register(@RequestBody UserDto registerUser) throws AuthenticationException {

        return authenticationService.register(registerUser);
    }

/*    @GetMapping(value = "/expDate")
    public Date GetExpDate(@PathVariable String token) {
        token = token.replace(Constants.TOKEN_PREFIX, "");
        return jwtTokenUtil.getExpirationDateFromToken(token);
    }*/

}
