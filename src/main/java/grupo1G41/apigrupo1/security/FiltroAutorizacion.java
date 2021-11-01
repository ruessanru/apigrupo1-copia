package grupo1G41.apigrupo1.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class FiltroAutorizacion  extends BasicAuthenticationFilter{

    public FiltroAutorizacion(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
       
       String header = request.getHeader(ConstantesSecurity.HEADER_STRING);

       if(header==null || !header.startsWith(ConstantesSecurity.TOKEN_PREFIX)){
           
           chain.doFilter(request, response);

           return;
       }


       UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request, header);

       SecurityContextHolder.getContext().setAuthentication(authenticationToken);

       chain.doFilter(request, response);
    }
    
    
    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request, String header) {

                if(header!=null){
                    
                    String token = header.replace(ConstantesSecurity.TOKEN_PREFIX,"");

                    String user= Jwts.parser()
                                    .setSigningKey(ConstantesSecurity.TOKEN_SECRET)
                                    .parseClaimsJws(token)
                                    .getBody().getSubject();
                    
                    if(user!=null){
                        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    }
                }


        return null;
    }
    
}
