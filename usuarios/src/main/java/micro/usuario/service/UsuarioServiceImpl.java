package micro.usuario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import micro.usuario.entity.Usuario;
import micro.usuario.repository.UsuarioRepo;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Override
    public List<Usuario> findUsuarios() {
        return (List<Usuario>) usuarioRepo.findAll();
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        
        return usuarioRepo.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        
        return usuarioRepo.save(usuario);
    }

    @Override
    public void eliminar(Long id) {      
        usuarioRepo.deleteById(id);
    }
    
}
