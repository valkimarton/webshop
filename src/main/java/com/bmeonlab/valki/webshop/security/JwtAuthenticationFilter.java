package com.bmeonlab.valki.webshop.security;

import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.service.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;

    // TODO: doesnt work, should be fixed
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int tokenExpirationTime;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;

        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {

        System.out.println(request.getParameterMap());

        var username = request.getParameter("username");
        var password = request.getParameter("password");

        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        System.out.println("########## attempt Authentication: username: " + username + ", pw: " + password + "  ###########");

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication authentication) {
        var user = ((User) authentication.getPrincipal());
        System.out.println("########" + user.getUsername());
        Long id = customerService.getCustomerByUsername(user.getUsername()).getId();
        var roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, SecurityConstants.TOKEN_EXPIRATION);
        System.out.println("CALENDAR TIME: " + calendar.getTime() + ", Stamp: " + calendar.getTime());

        var signingKey = SecurityConstants.JWT_SECRET.getBytes();

        var token = Jwts.builder()
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(user.getUsername())
                .setExpiration(calendar.getTime())
                .claim("rol", roles)
                .claim("id", id)
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();

        response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX + token);
    }
}