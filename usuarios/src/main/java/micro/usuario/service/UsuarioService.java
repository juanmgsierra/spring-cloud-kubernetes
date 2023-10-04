package micro.usuario.service;

import java.util.List;
import java.util.Optional;

import micro.usuario.entity.Usuario;

public interface UsuarioService {
    
    List<Usuario> findUsuarios();
    Optional<Usuario> getById(Long id);
    Usuario save(Usuario usuario);
    void eliminar(Long id)

}
