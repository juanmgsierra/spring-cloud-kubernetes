package micro.cursos.clients;

import micro.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "usuarios-service", url = "host.docker.internal:8001")
public interface UserClientRest {
 
    @GetMapping("user/{id}")
    Usuario detail(@PathVariable Long id);

    @PostMapping("user/create")
    Usuario create(@RequestBody Usuario usuario);

    @GetMapping("user/users-curso")
    List<Usuario> getUsersByCourse(@RequestParam Iterable<Long> ids);
}
