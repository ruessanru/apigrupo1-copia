package grupo1G41.apigrupo1.controllers;

import grupo1G41.apigrupo1.models.peticiones.UsuarioRegistrarRequestModel;
import grupo1G41.apigrupo1.models.respuestas.UsuarioResModel;
import grupo1G41.apigrupo1.services.IUsuarioService;
import grupo1G41.apigrupo1.shared.UsuarioCrearDto;
import grupo1G41.apigrupo1.shared.UsuarioDto;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControllers {

     

      @Autowired
      ModelMapper modelmapper;

      @Autowired
      IUsuarioService iUsuarioService;

      @GetMapping
      public UsuarioResModel obtenerUsuario(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getPrincipal().toString();

        UsuarioDto usuarioDto =iUsuarioService.obtenerUsuario(userName);

        UsuarioResModel usuarioResModel= modelmapper.map(usuarioDto, UsuarioResModel.class);

        return usuarioResModel;


      }

      
      @PostMapping
      
      public UsuarioResModel crearUsuario(@RequestBody UsuarioRegistrarRequestModel usuarioRegistrarRequestModel){

           UsuarioCrearDto usuarioCrearDto = modelmapper.map(usuarioRegistrarRequestModel, UsuarioCrearDto.class);

           UsuarioDto usuarioDto= iUsuarioService.crearUsuario(usuarioCrearDto);

           UsuarioResModel usuarioResModel = modelmapper.map(usuarioDto, UsuarioResModel.class);

          return usuarioResModel;
      }

    
}
