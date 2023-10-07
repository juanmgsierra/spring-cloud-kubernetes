package micro.cursos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import micro.cursos.models.Usuario;
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

import feign.FeignException;
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
        return cursoService.getByIdUsers(id);
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

    @DeleteMapping("delete-course-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
         cursoService.deleteCourseByIdUser(id);
         return ResponseEntity.noContent().build();
    }


    @PutMapping("assign-user/{idCurso}")
    public ResponseEntity<?> assignUser(@RequestBody Usuario usuario, @PathVariable Long idCurso){
        try {            
            return cursoService.assignUser(usuario, idCurso);
        } catch (FeignException e) {
            System.out.println("err" + e.getMessage());
            return ResponseEntity.badRequest().body("communication error with third party service");
        }
    }

    @PostMapping("create-user/{idCurso}")
     public ResponseEntity<?> createUser(@RequestBody Usuario usuario, @PathVariable Long idCurso){
        try {
            return cursoService.createUser(usuario, idCurso);
        } catch (FeignException e) {
            System.out.println("err" + e.getMessage());
            return ResponseEntity.badRequest().body("communication error with third party service");
        }
    }

    @DeleteMapping("delete-user/{idCurso}")
    public ResponseEntity<?> deleteUser(@RequestBody Usuario usuario, @PathVariable Long idCurso){
        try {
            return cursoService.deleteUser(usuario, idCurso);
        } catch (FeignException e) {
            System.out.println("err" + e.getMessage());
            return ResponseEntity.badRequest().body("communication error with third party service");
        }
    }

    private ResponseEntity<?> getErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
