package micro.cursos.models;

import lombok.Data;

@Data
public class Usuario {

    private Long id;

    private String nombre;

    private String correo;

    private Integer telefono;

    private String password;
}