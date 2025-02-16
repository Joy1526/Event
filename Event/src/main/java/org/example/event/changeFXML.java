package org.example.event;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class changeFXML {

    public static void switchScene(javafx.event.ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(changeFXML.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene=new Scene(root);
            scene.getStylesheets().add(changeFXML.class.getResource("application.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
