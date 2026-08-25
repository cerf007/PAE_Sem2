package ni.edu.uam.registropaciente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientApplication  extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PatientApplication.class.getResource("patient-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Patient");
        stage.setScene(scene);
        stage.show();
    }
}
