package grupo1G41.apigrupo1.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import grupo1G41.apigrupo1.services.IUsuarioService;

@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter{

    private final  IUsuarioService iusuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(IUsuarioService iusuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iusuarioService = iusuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
      .authorizeRequests().antMatchers(HttpMethod.POST, "/usuarios").permitAll()
      .anyRequest().authenticated()
      .and().addFilter(getFiltroAutentication()).addFilter(new FiltroAutorizacion(authenticationManager()))
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

   } 

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
        auth.userDetailsService(iusuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

    public FiltroAutentication getFiltroAutentication() throws Exception {

        final FiltroAutentication filtroAutentication = new FiltroAutentication(authenticationManager());

        filtroAutentication.setFilterProcessesUrl("/usuarios/login");
        
        return filtroAutentication;

    }
}
