module ni.edu.uam.registropaciente {
    requires javafx.controls;
    requires javafx.fxml;

    requires static lombok;

    opens ni.edu.uam.registropaciente to javafx.fxml;
    opens ni.edu.uam.registropaciente.contollers to javafx.fxml;
    opens ni.edu.uam.registropaciente.modelos to javafx.base, javafx.fxml;

    exports ni.edu.uam.registropaciente;
    exports ni.edu.uam.registropaciente.contollers;
    exports ni.edu.uam.registropaciente.modelos;
}