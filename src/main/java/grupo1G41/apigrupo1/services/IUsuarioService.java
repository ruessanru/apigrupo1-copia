package grupo1G41.apigrupo1.services;



import org.springframework.security.core.userdetails.UserDetailsService;

import grupo1G41.apigrupo1.shared.UsuarioCrearDto;
import grupo1G41.apigrupo1.shared.UsuarioDto;

public interface IUsuarioService extends UserDetailsService{

    public UsuarioDto crearUsuario(UsuarioCrearDto usuarioCrearDto);
    public UsuarioDto obtenerUsuario(String userName);
    
}
