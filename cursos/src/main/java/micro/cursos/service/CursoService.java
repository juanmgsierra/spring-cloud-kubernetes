package micro.cursos.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import micro.cursos.entity.Cursos;

public interface CursoService {
    

    ResponseEntity<List<Cursos>> findCursos();
    ResponseEntity<Cursos> getById(Long id);
    ResponseEntity<Cursos> save(Cursos cursos);   
    ResponseEntity<?> update(Cursos cursos, Long id);
    ResponseEntity<?> delete(Long id); 

}
