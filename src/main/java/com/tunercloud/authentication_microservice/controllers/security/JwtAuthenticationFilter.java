package com.tunercloud.authentication_microservice.controllers.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tunercloud.authentication_microservice.models.Account;
import com.tunercloud.authentication_microservice.models.JwtAccount;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.tunercloud.authentication_microservice.controllers.security.SecurityConstants.EXPIRATION_TIME;
import static com.tunercloud.authentication_microservice.controllers.security.SecurityConstants.SECRET;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            Account account = new ObjectMapper().readValue(req.getInputStream(), Account.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken( account.getEmail(), account.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create() .withSubject(((JwtAccount) auth.getPrincipal()).getUsername()).withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(HMAC512(SECRET.getBytes()));
        //Convert token to json and return to the user
        JSONObject tokenResponse = new JSONObject();
        try {
            tokenResponse.put("token",  token);
        } catch (JSONException e) {
            logger.error(e.getMessage());
        }

        //Write token response to body
        res.getWriter().print(tokenResponse);
    }
}

