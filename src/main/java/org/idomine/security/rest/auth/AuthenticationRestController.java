package org.idomine.security.rest.auth;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.idomine.security.exceptions.AuthenticationException;
import org.idomine.security.jwt.JwtAuthenticationRequest;
import org.idomine.security.jwt.JwtTokenUtil;
import org.idomine.security.jwt.JwtUser;
import org.idomine.security.model.User;
import org.idomine.security.repository.UserRepository;
import org.idomine.security.service.JwtAuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class AuthenticationRestController
{
    @Value("${jwt.header}")
    private String tokenHeader;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping({ "/auth" })
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException
    {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Reload password post-security so we can generate the token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @GetMapping({ "/auth/refresh" })
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request)
    {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
        {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping({ "/auth/whoami" })
    public ResponseEntity<?> whoami(HttpServletRequest request)
    {
        // Authorization = 
        // token: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUzMDEyNjM5OSwiaWF0IjoxNTI5NTIxNTk5fQ.1bSdXzCAm5mY6Vj7w2K_WWM2xxK5HDc7rG6WVzQOC1bGekErFm6uozEjQGgYZNZxFVb1IKc9ZtEoJNAyCkcGNg
        
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
        {
            return ResponseEntity.ok( user );
        }
        return ResponseEntity.badRequest().body(null);
    }    

    @GetMapping({ "/auth/authorities" })
    public ResponseEntity<?> authorities(HttpServletRequest request)
    {
        String authToken = request.getHeader(tokenHeader);
        final String token = authToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate()))
        {
            return ResponseEntity.ok( user.getAuthorities() );
        }
        return ResponseEntity.badRequest().body(null);
    }    
    
    @PostMapping({ "/auth/register","/auth/signup" })
    @Transactional
    public ResponseEntity<?> register(@RequestBody User user )
    {
        user.setEnabled(true);
        user.setLastPasswordResetDate(new Date());
        userRepository.save(user);
        return ResponseEntity.ok( user );
        //return ResponseEntity.badRequest().body(null);
    }

    @GetMapping({ "/auth/password/tip" })
    public ResponseEntity<?> passwordTip(HttpServletRequest request)
    {
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping({ "/auth/password/change" })
    public ResponseEntity<?> passordChange(HttpServletRequest request)
    {
        return ResponseEntity.badRequest().body(null);
    }
    
    @GetMapping({ "/auth/password/reset" })
    public ResponseEntity<?> passordReset(HttpServletRequest request)
    {
        return ResponseEntity.badRequest().body(null);
    }
    
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    /**
     * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
     */
    private void authenticate(String username, String password)
    {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (DisabledException e)
        {
            throw new AuthenticationException("User is disabled!", e);
        }
        catch (BadCredentialsException e)
        {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }
}
