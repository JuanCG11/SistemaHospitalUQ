module co.edu.uniquindio.poo.sistemahospitaluq {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.sistemahospitaluq to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemahospitaluq;
}