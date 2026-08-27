package ni.edu.uam.registropaciente.contollers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ni.edu.uam.registropaciente.Navegador;
import ni.edu.uam.registropaciente.modelos.User;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private ImageView imgLogo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Carga de logo.jpg
        Image logo = cargarImagen("/ni/edu/uam/registropaciente/images/logo.jpg");
        if (imgLogo != null && logo != null) {
            imgLogo.setImage(logo);
        }
    }

    private Image cargarImagen(String ruta) {
        try {
            InputStream is = getClass().getResourceAsStream(ruta);
            if (is != null) {
                return new Image(is);
            } else {
                System.err.println("No se encontró la imagen en: " + ruta);
            }
        } catch (Exception e) {
            System.err.println("Error al cargar imagen " + ruta + ": " + e.getMessage());
        }
        return null;
    }

    @FXML
    protected void loginOnClick(ActionEvent event) {
        if (txtUsuario == null || txtContrasena == null) return;

        User loginUser = new User(txtUsuario.getText().trim(), txtContrasena.getText().trim());

        if ("admin".equalsIgnoreCase(loginUser.getUsuario()) && "admin".equals(loginUser.getContrasena())) {
            mostrarAlerta("Acceso Concedido", "¡Bienvenido al sistema!", Alert.AlertType.INFORMATION);

            try {
                // Uso de la clase Navegador
                Navegador.cambiarVentana(event, "/ni/edu/uam/registropaciente/patient-view.fxml", "Gestión de Pacientes");
            } catch (Exception e) {
                System.err.println("Error al navegar a la pantalla de pacientes: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            mostrarAlerta("Error de Autenticación", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}