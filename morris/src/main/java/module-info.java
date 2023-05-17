module com.nineman.morris {
    requires javafx.controls;
    requires javafx.fxml;

    requires annotations;

    opens com.nineman.morris to javafx.fxml;
    exports com.nineman.morris;
    exports com.nineman.morris.actions;
    opens com.nineman.morris.actions to javafx.fxml;
}