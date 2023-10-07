package micro.cursos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cursosusuario")
public class CursosUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idUsuario", unique = true)
    private Long idUsuario;

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(!(obj instanceof CursosUsuario)){
            return false;
        }

        CursosUsuario o = (CursosUsuario) obj;
        return this.idUsuario != null && this.idUsuario.equals(o.idUsuario);
    }

}
