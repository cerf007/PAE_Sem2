package ni.edu.uam.registropaciente;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ni.edu.uam.registropaciente.dao.PatientDAO;
import ni.edu.uam.registropaciente.modelos.Patient;

public class PatientController {

    PatientDAO patients = new PatientDAO();

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private Label lblContador;

    @FXML
    protected void agregarOnClick(){
        leerDatos();
        cantidadPatient();
        limpiarCampos();

    }

    private void leerDatos(){
        String nombres = txtNombre.getText();
        String apellidos = txtApellido.getText();
        agregarPatient(new Patient(nombres, apellidos));

    }

    private void agregarPatient(Patient patient) {
        patients.agregarPatiente(patient);
    }

    private void cantidadPatient(){
        lblContador.setText("Registros almacenados: " + patients.listarPatient().size());
    }

    private void limpiarCampos(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtNombre.setText("");
    }

}
