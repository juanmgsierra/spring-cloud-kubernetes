package micro.cursos.entity;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import micro.cursos.models.Usuario;

@Data
@Entity
@Table(name ="cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "idCurso")
    private List<CursosUsuario> cursosUsuario;


    @Transient
    private List<Usuario> usuarios;

    public void addCursoUsuario(CursosUsuario cursoUsuario){
        cursosUsuario.add(cursoUsuario);
    }

    public void removeCursoUsuario(CursosUsuario cursoUsuario){
        cursosUsuario.remove(cursoUsuario);
    }

    public Cursos(){
        cursosUsuario = new ArrayList<>();
        usuarios = new ArrayList<>();
    }
}
