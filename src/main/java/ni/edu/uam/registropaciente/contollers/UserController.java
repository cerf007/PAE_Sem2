package ni.edu.uam.registropaciente.contollers;

import javafx.application.Platform;
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

    private int intentos = 0;
    private final int INTENTOS_MAXIMOS = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
                Navegador.cambiarVentana(event, "/ni/edu/uam/registropaciente/patient-view.fxml", "Gestión de Pacientes");
            } catch (Exception e) {
                System.err.println("Error al navegar a la pantalla de pacientes: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            // Incrementar contador de errores
            intentos++;
            int restantes = INTENTOS_MAXIMOS - intentos;

            if (intentos >= INTENTOS_MAXIMOS) {
                mostrarAlerta("Acceso Bloqueado", "Has superado el límite de 3 intentos fallidos. El programa se cerrará.", Alert.AlertType.ERROR);
                Platform.exit();
            } else {
                mostrarAlerta("Error de Autenticación",
                        "Usuario o contraseña incorrectos. Te quedan " + restantes + " intento(s).",
                        Alert.AlertType.ERROR);

                txtContrasena.clear();
                txtContrasena.requestFocus();
            }
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