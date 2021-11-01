package grupo1G41.apigrupo1.services;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import grupo1G41.apigrupo1.data.entidades.UsuarioEntity;
import grupo1G41.apigrupo1.data.repositorios.IUsuarioRepository;
import grupo1G41.apigrupo1.shared.UsuarioCrearDto;
import grupo1G41.apigrupo1.shared.UsuarioDto;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    ModelMapper modelmapper;

    @Autowired
    IUsuarioRepository iusuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UsuarioDto crearUsuario(UsuarioCrearDto usuarioCrearDto){

         if(iusuarioRepository.findByUserEmail(usuarioCrearDto.getUserEmail())!= null){
             throw new RuntimeException("Este correo ya se encuentra registrado");
         }
         if(iusuarioRepository.findByUserName(usuarioCrearDto.getUserName())!= null){
            throw new RuntimeException("Este nombre de usuario ya se encuentra registrado");
         }

        UsuarioEntity usuarioEntityDto= modelmapper.map(usuarioCrearDto, UsuarioEntity.class);
        usuarioEntityDto.setUserId(UUID.randomUUID().toString());
        usuarioEntityDto.setEncryptedPassword (bCryptPasswordEncoder.encode(usuarioCrearDto.getPassword()));

        UsuarioEntity usuarioEntitySave= iusuarioRepository.save(usuarioEntityDto);

        UsuarioDto usuarioDto= modelmapper.map(usuarioEntitySave, UsuarioDto.class);

        return usuarioDto;

    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      
         UsuarioEntity usuarioEntity=iusuarioRepository.findByUserName(userName);

         if(usuarioEntity == null){
             throw new UsernameNotFoundException(userName);
         }
          return new User(usuarioEntity.getUserName(), usuarioEntity.getEncryptedPassword(), new ArrayList<>());
        
    }

    @Override
    public UsuarioDto obtenerUsuario(String userName) {
       
        UsuarioEntity usuarioEntity=iusuarioRepository.findByUserName(userName);

        if(usuarioEntity == null){
            throw new UsernameNotFoundException(userName);
        }
        UsuarioDto usuarioDto= modelmapper.map(usuarioEntity, UsuarioDto.class);


        return usuarioDto;
    }
    
}
