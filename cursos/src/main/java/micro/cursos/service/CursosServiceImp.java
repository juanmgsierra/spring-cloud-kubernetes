package micro.cursos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import micro.cursos.clients.UserClientRest;
import micro.cursos.entity.CursosUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import micro.cursos.entity.Cursos;
import micro.cursos.models.Usuario;
import micro.cursos.repository.CursosRepo;

@Service
public class CursosServiceImp implements CursoService {

    @Autowired
    CursosRepo cursosRepo;

    @Autowired
    UserClientRest UserClientRest;

    @Override
    public ResponseEntity<List<Cursos>> findCursos() {
        return ResponseEntity.ok((List<Cursos>) cursosRepo.findAll());
    }

    @Override
    public ResponseEntity<Cursos> getById(Long id) {
        Optional<Cursos> curso = cursosRepo.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(curso.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<Cursos> getByIdUsers(Long id) {
        Optional<Cursos> curso = cursosRepo.findById(id);
        if (!curso.isPresent()) {
            return ResponseEntity.badRequest().body(null);
        }
        Cursos getCurso = curso.get();
        if(!getCurso.getCursosUsuario().isEmpty()){
            List<Long> ids = getCurso.getCursosUsuario().stream().map(item -> item.getIdUsuario()).collect(Collectors.toList());
            List<Usuario> usuarios = UserClientRest.getUsersByCourse(ids);
            getCurso.setUsuarios(usuarios);
        }
        return ResponseEntity.ok(getCurso);
    }

    @Override
    public ResponseEntity<Cursos> save(Cursos cursos) {
        cursosRepo.save(cursos);
        return ResponseEntity.ok(cursos);
    }

    @Override
    public ResponseEntity<?> update(Cursos cursos, Long id) {
        Optional<Cursos> curso = cursosRepo.findById(id);
        if (curso.isPresent()) {
            Cursos cursoUpdate = curso.get();
            cursoUpdate.setId(id);
            cursoUpdate.setNombre(cursos.getNombre());
            cursoUpdate.setDescripcion(cursos.getDescripcion());
            cursosRepo.save(cursoUpdate);
            return ResponseEntity.ok(cursoUpdate);
        }
        return ResponseEntity.badRequest().body("Curso doesn't exist");
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        try {
            cursosRepo.deleteById(id);
            return ResponseEntity.ok("Curso deleted succesfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("error in service");

        }
    }

    @Override
    @Transactional
    public void deleteCourseByIdUser(Long id) {
       cursosRepo.deleteUserById(id);
    }

    @Override
    public ResponseEntity<?> assignUser(Usuario usuario, Long id) {
        Optional<Cursos> o = cursosRepo.findById(id);
        if (o.isPresent()) {
            Usuario getUser = UserClientRest.detail(usuario.getId());
            Cursos curso = o.get();
            CursosUsuario cursoUsuario = new CursosUsuario();
            cursoUsuario.setIdUsuario(getUser.getId());
            curso.addCursoUsuario(cursoUsuario);
            cursosRepo.save(curso);
            return ResponseEntity.ok(getUser);
        }
        return ResponseEntity.badRequest().body("El curso enviado no existe");

    }

    @Override
    public ResponseEntity<?> createUser(Usuario usuario, Long id) {
        Optional<Cursos> o = cursosRepo.findById(id);
        if (o.isPresent()) {
            Usuario createdUser = UserClientRest.create(usuario);
            Cursos curso = o.get();
            CursosUsuario cursoUsuario = new CursosUsuario();
            cursoUsuario.setIdUsuario(createdUser.getId());
            curso.addCursoUsuario(cursoUsuario);
            cursosRepo.save(curso);
            return ResponseEntity.ok(createdUser);
        }
        return ResponseEntity.badRequest().body("El curso enviado no existe");
    }

    @Override
    public ResponseEntity<?> deleteUser(Usuario usuario, Long id) {
        Optional<Cursos> o = cursosRepo.findById(id);
        if (o.isPresent()) {
            Usuario getUser = UserClientRest.detail(usuario.getId());
            Cursos curso = o.get();
            CursosUsuario cursoUsuario = new CursosUsuario();
            cursoUsuario.setIdUsuario(getUser.getId());
            curso.removeCursoUsuario(cursoUsuario);
            cursosRepo.save(curso);
            return ResponseEntity.ok(getUser);
        }
        return ResponseEntity.badRequest().body("El curso enviado no existe");

    }

}
