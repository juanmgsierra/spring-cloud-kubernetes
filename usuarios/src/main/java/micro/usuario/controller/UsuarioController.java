package micro.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import micro.usuario.entity.Usuario;
import micro.usuario.service.UsuarioService;

@RestController
@RequestMapping("user")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("users")
    public ResponseEntity<List<Usuario>> list(){
        return usuarioService.findUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        return usuarioService.getById(id);      
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Usuario user){
        return usuarioService.save(user);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario user, @PathVariable Long id){
        return usuarioService.update(id, user);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return usuarioService.delete(id);
    }
}
