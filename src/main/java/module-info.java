module co.edu.uniquindio.poo.sistemahospitaluq {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;



    opens co.edu.uniquindio.poo.sistemahospitaluq.app to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemahospitaluq.app;

    opens co.edu.uniquindio.poo.sistemahospitaluq.controller to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemahospitaluq.controller;

    opens co.edu.uniquindio.poo.sistemahospitaluq.model to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemahospitaluq.model;

    opens co.edu.uniquindio.poo.sistemahospitaluq.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.sistemahospitaluq.viewController;



}