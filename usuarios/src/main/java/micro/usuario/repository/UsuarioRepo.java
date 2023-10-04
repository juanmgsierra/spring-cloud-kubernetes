package micro.usuario.repository;

import org.springframework.data.repository.CrudRepository;

import micro.usuario.entity.Usuario;

public interface UsuarioRepo extends CrudRepository<Usuario,Long>{
    
}
