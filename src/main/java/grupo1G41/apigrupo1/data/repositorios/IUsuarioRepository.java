package grupo1G41.apigrupo1.data.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import grupo1G41.apigrupo1.data.entidades.UsuarioEntity;


@Repository
public interface IUsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    public UsuarioEntity findByUserEmail (String userEmail);
    public UsuarioEntity findByUserName (String useName);
    
}
