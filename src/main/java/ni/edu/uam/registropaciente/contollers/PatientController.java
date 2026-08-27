package ni.edu.uam.registropaciente.contollers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ni.edu.uam.registropaciente.dao.PatientDAO;
import ni.edu.uam.registropaciente.modelos.Patient;

import java.time.LocalDate;

public class PatientController {

    PatientDAO patients = new PatientDAO();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private Label lblContador;

    @FXML
    private DatePicker dtpFechaIngreso;

    @FXML
    private RadioButton rbtnMasculino;

    @FXML
    private ListView lvRegistros;


    @FXML
    protected void agregarOnClick(){
        leerDatos();
        cantidadPatient();

        limpiarCampos();

    }

    private void leerDatos(){
        String nombres = txtNombre.getText();
        String apellidos = txtApellido.getText();
        Boolean genero = rbtnMasculino.isSelected();
        LocalDate fechaIngreso = dtpFechaIngreso.getValue();
        agregarPatient(new Patient(nombres, apellidos, genero, fechaIngreso));

    }

    private void agregarPatient(Patient patient) {
        patients.agregar(patient);
    }

    private void cantidadPatient(){
        lblContador.setText("Registros almacenados: " + patients.obtenerRegistros().size());
    }

    private void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
    }

}
