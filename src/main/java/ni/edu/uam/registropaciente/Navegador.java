package ni.edu.uam.registropaciente;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navegador {
    public static void cambiarVentana(ActionEvent event, String fxmlFile, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navegador.class.getResource(fxmlFile));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(titulo);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}
