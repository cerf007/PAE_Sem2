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
        String user = txtUser.getText();
        String pass = txtPassword.getText();

        if (user.isBlank() || pass.isBlank()) {
            lblError.setText("Por favor ingrese usuario y contraseña.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("patient-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Gestión de Pacientes");
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblError.setText("Error al cargar la vista de pacientes.");
        }
    }

}
