package ni.edu.uam.registropaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    @FXML
    private void handleLogin(ActionEvent event) {
        String user = leerUsuario();
        String pass = leerPassword();

        if (!comprobarCredenciales(user, pass)) {
            lblError.setText("Por favor ingrese usuario y contraseña.");
            return;
        }

        try {
            abrirVentanaPacientes(event);
        } catch (IOException e) {
            e.printStackTrace();
            lblError.setText("Error al cargar la vista de pacientes.");
        }
    }

    private String leerUsuario() {
        return txtUser.getText().trim();
    }

    private String leerPassword() {
        return txtPassword.getText();
    }

    private boolean comprobarCredenciales(String user, String pass) {
        return !user.isBlank() && !pass.isBlank();
    }

    private void abrirVentanaPacientes(ActionEvent event) throws IOException {
        Navegador.cambiarVentana(event, "patient-view.fxml", "Gestión de Pacientes");
    }
}