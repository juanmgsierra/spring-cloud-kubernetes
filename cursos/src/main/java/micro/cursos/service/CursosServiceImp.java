package micro.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import micro.cursos.entity.Cursos;
import micro.cursos.repository.CursosRepo;

@Service
public class CursosServiceImp implements CursoService {

    @Autowired
    CursosRepo cursosRepo;

    @Override
    public ResponseEntity<List<Cursos>> findCursos() {
        return ResponseEntity.ok((List<Cursos>) cursosRepo.findAll());
    }

    @Override
    public ResponseEntity<Cursos> getById(Long id) {
       Optional<Cursos> curso = cursosRepo.findById(id);
       if(curso.isPresent()){
        return ResponseEntity.ok(curso.get());
       }
       return ResponseEntity.badRequest().body(null);
    }

    @Override
    public ResponseEntity<Cursos> save(Cursos cursos) {
        cursosRepo.save(cursos);
        return ResponseEntity.ok(cursos);
    }

    @Override
    public ResponseEntity<?> update(Cursos cursos, Long id) {
        Optional<Cursos> curso = cursosRepo.findById(id);
        if(curso.isPresent()){
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
    
}
