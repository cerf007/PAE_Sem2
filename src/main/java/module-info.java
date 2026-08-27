module ni.edu.uam.registropaciente {
    requires javafx.controls;
    requires javafx.fxml;

    // AGREGA ESTA LÍNEA PARA LOMBOK:
    requires static lombok;

    // Apertura de paquetes para JavaFX y Reflexión
    opens ni.edu.uam.registropaciente to javafx.fxml;
    opens ni.edu.uam.registropaciente.contollers to javafx.fxml;
    opens ni.edu.uam.registropaciente.modelos to javafx.base, javafx.fxml;

    // Exportación de paquetes
    exports ni.edu.uam.registropaciente;
    exports ni.edu.uam.registropaciente.contollers;
    exports ni.edu.uam.registropaciente.modelos;
}