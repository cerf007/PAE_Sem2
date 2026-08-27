package ni.edu.uam.registropaciente.contollers;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

    // Componentes del TableView
    @FXML private TableView<Patient> tvRegistros;
    @FXML private TableColumn<Patient, String> colNombres;
    @FXML private TableColumn<Patient, String> colApellidos;
    @FXML private TableColumn<Patient, String> colGenero;
    @FXML private TableColumn<Patient, LocalDate> colFechaIngreso;

    private ToggleGroup tgGenero;
    private final LocalDate FECHA_MINIMA = LocalDate.of(2026, 1, 1);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar columnas del TableView
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colGenero.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGeneroTexto()));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<>("fechaIngreso"));

        // Enlazar lista
        tvRegistros.setItems(patients.obtenerRegistros());

        // Agrupar RadioButtons
        tgGenero = new ToggleGroup();
        rbtnMasculino.setToggleGroup(tgGenero);
        rbtnFemenino.setToggleGroup(tgGenero);

        // Bloquear escritura e inhabilitar fechas anteriores a 2026
        dtpFechaIngreso.setEditable(false);
        dtpFechaIngreso.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date != null && date.isBefore(FECHA_MINIMA)) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffe6e6;");
                }
            }
        });
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
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor ingrese nombres y apellidos.");
            return false;
        }

        String regexSoloLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        if (!nombre.matches(regexSoloLetras) || !apellido.matches(regexSoloLetras)) {
            mostrarAlerta("Texto inválido", "Los nombres y apellidos solo deben contener letras.");
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

        if (dtpFechaIngreso.getValue().isBefore(FECHA_MINIMA)) {
            mostrarAlerta("Fecha inválida", "La fecha de ingreso no puede ser anterior al 01/01/2026.");
            return false;
        }

        return true;
    }

    private void leerDatos() {
        String nombres = txtNombre.getText().trim();
        String apellidos = txtApellido.getText().trim();
        Boolean genero = rbtnMasculino.isSelected();
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