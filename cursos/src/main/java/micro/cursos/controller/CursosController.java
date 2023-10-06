package micro.cursos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import micro.cursos.entity.Cursos;
import micro.cursos.service.CursoService;

@RestController
@RequestMapping("curso")
public class CursosController {

    @Autowired
    private CursoService cursoService;

    @GetMapping("cursos")
    public ResponseEntity<List<Cursos>> list() {
        return cursoService.findCursos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        return cursoService.getById(id);
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@Valid @RequestBody Cursos curso, BindingResult result) {
        if (result.hasErrors()) {
            return getErrores(result);
        }
        return cursoService.save(curso);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Cursos cursos, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return getErrores(result);
        }
        return cursoService.update(cursos, id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return cursoService.delete(id);
    }

    private ResponseEntity<?> getErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
