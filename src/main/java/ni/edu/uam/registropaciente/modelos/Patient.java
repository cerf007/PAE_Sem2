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

    public String getGeneroTexto() {
        if (this.genero == null) return "No especificado";
        return this.genero ? "Masculino" : "Femenino";
    }

    @Override
    public String toString() {
        String generoTexto = (genero == null) ? "No especificado" : (genero ? "Masculino" : "Femenino");
        return nombres + " " + apellidos + " " + generoTexto + " " + fechaIngreso;
    }
}