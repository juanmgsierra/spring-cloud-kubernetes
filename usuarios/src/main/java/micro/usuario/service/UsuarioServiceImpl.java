package micro.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import micro.usuario.entity.Usuario;
import micro.usuario.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public ResponseEntity<List<Usuario>> findUsuarios() {
        return ResponseEntity.ok((List<Usuario>) usuarioRepo.findAll());
    }

    @Override
    public ResponseEntity<Usuario> getById(Long id) {
        Optional<Usuario> user = usuarioRepo.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<Usuario> save(Usuario usuario) {
        Usuario userCreated = usuarioRepo.save(usuario);
        return ResponseEntity.ok(userCreated);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            usuarioRepo.deleteById(id);
            return ResponseEntity.ok("User deleted succesfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error in service");
        }

    }

    @Override
    public ResponseEntity<?> update(Long id, Usuario usuario) {
       Optional<Usuario> user = usuarioRepo.findById(id);
       if(user.isPresent()){          
            Usuario updateUser = user.get();
            updateUser.setId(id);
            updateUser.setCorreo(usuario.getCorreo());
            updateUser.setNombre(usuario.getNombre());
            updateUser.setPassword(usuario.getPassword());
            updateUser.setTelefono(usuario.getTelefono());
            usuarioRepo.save(updateUser);
            return ResponseEntity.ok().body(updateUser);
       }
       return ResponseEntity.badRequest().body("User don't exist");
    }

}
