package org.idomine.security.rest.user;

import javax.servlet.http.HttpServletRequest;

import org.idomine.security.jwt.JwtTokenUtil;
import org.idomine.security.jwt.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;

   // Exemplo: @PreAuthorize  
   // @PreAuthorize("hasRole('ROLE_USER')")
   // public interface ParkrunCourseRepository extends CrudRepository<ParkrunCourse, Long> {
   //     @Override
   //     @PreAuthorize("hasRole('ROLE_ADMIN')")
   //     ParkrunCourse save(ParkrunCourse parkrunCourse);
   // }    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

}
