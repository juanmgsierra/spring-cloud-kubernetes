package micro.cursos.repository;
import org.springframework.data.repository.CrudRepository;

import micro.cursos.entity.Cursos;

public interface CursosRepo extends CrudRepository<Cursos, Long>  {
    
}
