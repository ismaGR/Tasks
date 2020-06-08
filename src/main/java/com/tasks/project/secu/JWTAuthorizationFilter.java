package com.tasks.project.secu;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
	

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwtToken = request.getHeader(JwtProperties.HEADER_STRING);

        if(jwtToken==null || !jwtToken.startsWith(JwtProperties.TOKEN_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }
        Claims claims= Jwts.parser()
                .setSigningKey(JwtProperties.SECRET)
                .parseClaimsJws(jwtToken.replace(JwtProperties.TOKEN_PREFIX,""))
                .getBody();
        String username = claims.getSubject();

        ArrayList<Map<String, String>> roles =  (ArrayList<Map<String, String>>) claims.get("roles");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.get("authority")));
        });
        UsernamePasswordAuthenticationToken authenticatedToken=
            new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
            filterChain.doFilter(request,response);

    }
    
    private Claims validateToken(HttpServletRequest request) {
		String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");
		return Jwts.parser().setSigningKey(JwtProperties.SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
	}
}
