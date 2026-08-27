package ni.edu.uam.registropaciente.modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String nombres;
    private String apellidos;
    private Boolean genero;
    private LocalDate fechaIngreso;

    @Override
    public String toString() {
        return nombres + " " + apellidos + " " + genero + " " + fechaIngreso;
    }
}