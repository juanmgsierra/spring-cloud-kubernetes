package micro.usuario.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import micro.usuario.entity.Usuario;

public interface UsuarioService {
    
    ResponseEntity<List<Usuario>> findUsuarios();
    ResponseEntity<Usuario> getById(Long id);
    ResponseEntity<Usuario> save(Usuario usuario);
    ResponseEntity<?> update(Long id, Usuario usuario);
    ResponseEntity<?> delete(Long id);
    ResponseEntity<List<Usuario>> listIds(Iterable<Long> ids);
}
