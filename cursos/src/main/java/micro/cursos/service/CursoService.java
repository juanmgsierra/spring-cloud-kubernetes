package micro.cursos.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import micro.cursos.entity.Cursos;
import micro.cursos.models.Usuario;

public interface CursoService {
    

    ResponseEntity<List<Cursos>> findCursos();
    ResponseEntity<Cursos> getById(Long id);
    ResponseEntity<Cursos> getByIdUsers(Long id);
    ResponseEntity<Cursos> save(Cursos cursos);   
    ResponseEntity<?> update(Cursos cursos, Long id);
    ResponseEntity<?> delete(Long id); 
    void deleteCourseByIdUser(Long id);
    ResponseEntity<?> assignUser(Usuario usuario, Long idCurso);
    ResponseEntity<?> createUser(Usuario usuario, Long idCurso);
    ResponseEntity<?> deleteUser(Usuario usuario, Long idCurso);
}
