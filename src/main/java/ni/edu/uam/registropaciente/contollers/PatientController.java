package ni.edu.uam.registropaciente.contollers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ni.edu.uam.registropaciente.dao.PatientDAO;
import ni.edu.uam.registropaciente.modelos.Patient;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    PatientDAO patients = new PatientDAO();

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private Label lblContador;
    @FXML private DatePicker dtpFechaIngreso;
    @FXML private RadioButton rbtnMasculino;

    @FXML private ListView<Patient> lvRegistros;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvRegistros.setItems(patients.obtenerRegistros());
    }

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

        dtpFechaIngreso.setValue(null);
        rbtnMasculino.setSelected(false);
    }
}