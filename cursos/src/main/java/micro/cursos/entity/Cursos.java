package micro.cursos.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="cursos")
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
}
