module ni.edu.uam.registropaciente {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.registropaciente to javafx.fxml;
    exports ni.edu.uam.registropaciente;
}