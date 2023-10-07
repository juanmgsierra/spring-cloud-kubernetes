package micro.cursos.repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import micro.cursos.entity.Cursos;

public interface CursosRepo extends CrudRepository<Cursos, Long>  {

    @Modifying
    @Query("delete from CursosUsuario cu where cu.idUsuario =?1")
    void deleteUserById(Long id);
}
