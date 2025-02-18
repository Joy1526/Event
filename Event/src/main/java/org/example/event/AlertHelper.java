package org.example.event;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertHelper {
    public static void showStyledAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Remove the default window decorations
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.initStyle(StageStyle.UNDECORATED);

        // Apply custom CSS for styling
        alert.getDialogPane().getStylesheets().add(AlertHelper.class.getResource("application.css").toExternalForm());

        alert.showAndWait();
    }
}
