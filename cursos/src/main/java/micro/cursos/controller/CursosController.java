package micro.cursos.controller;

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

import micro.cursos.entity.Cursos;
import micro.cursos.service.CursoService;

@RestController
@RequestMapping("curso")
public class CursosController {
    
    @Autowired
    private CursoService cursoService;

    @GetMapping("cursos")
    public ResponseEntity<List<Cursos>> list(){
        return cursoService.findCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){
        return cursoService.getById(id);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Cursos curso){
        return cursoService.save(curso);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@RequestBody Cursos cursos, @PathVariable Long id){
        return cursoService.update(cursos, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return cursoService.delete(id);
    }

}
