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
    @FXML private RadioButton rbtnFemenino;

    @FXML private ListView<Patient> lvRegistros;

    private ToggleGroup tgGenero;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lvRegistros.setItems(patients.obtenerRegistros());

        tgGenero = new ToggleGroup();
        rbtnMasculino.setToggleGroup(tgGenero);
        rbtnFemenino.setToggleGroup(tgGenero);
    }

    @FXML
    protected void agregarOnClick() {
        if (validarCampos()) {
            leerDatos();
            cantidadPatient();
            limpiarCampos();
        }
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor ingrese nombres y apellidos.");
            return false;
        }
        if (!rbtnMasculino.isSelected() && !rbtnFemenino.isSelected()) {
            mostrarAlerta("Género no seleccionado", "Debe seleccionar un género.");
            return false;
        }
        if (dtpFechaIngreso.getValue() == null) {
            mostrarAlerta("Fecha vacía", "Debe seleccionar una fecha de ingreso.");
            return false;
        }
        return true;
    }

    private void leerDatos() {
        String nombres = txtNombre.getText().trim();
        String apellidos = txtApellido.getText().trim();
        Boolean genero = rbtnMasculino.isSelected(); // true para Masculino, false para Femenino
        LocalDate fechaIngreso = dtpFechaIngreso.getValue();

        agregarPatient(new Patient(nombres, apellidos, genero, fechaIngreso));
    }

    private void agregarPatient(Patient patient) {
        patients.agregar(patient);
    }

    private void cantidadPatient() {
        lblContador.setText("Registros almacenados: " + patients.obtenerRegistros().size());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        dtpFechaIngreso.setValue(null);
        tgGenero.selectToggle(null);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}