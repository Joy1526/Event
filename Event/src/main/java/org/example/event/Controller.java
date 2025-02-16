package org.example.event;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;



public class Controller implements Initializable{
    //For Login SignUp
    @FXML
    private AnchorPane signinPane;

    @FXML
    private AnchorPane signupPane;

    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private Line line;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //For Login SignUp Page
        //this.signinPane.setVisible(true);
        //this.signupPane.setVisible(false);
        //fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Downloads"));

        //myItemScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    //Login SignUp Page Start
    public void loginFunc(ActionEvent event) {

        //changeFXML.switchScene(event,"VendorsPage.fxml");
        changeFXML.switchScene(event,"VendorsPage.fxml");
    }

    public void SignIn() {

		/*SignInButton.setStyle("-fx-background-color: #d6d6d6;");
		SignUpButton.setStyle("-fx-background-color: #f2f2f2;");
		*/
        signinPane.setVisible(true);
        signupPane.setVisible(false);
        gotoSignIn();
    }

    public void SignUp() {
		/*SignInButton.setStyle("-fx-background-color: #f2f2f2;");
		SignUpButton.setStyle("-fx-background-color: #d6d6d6;");
		*/
        signinPane.setVisible(false);
        signupPane.setVisible(true);
        gotoSignUp();

    }

    void gotoSignUp() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(line);
        translate.setDuration(Duration.millis(300));
        translate.setToX(108);
        translate.play();
    }
    void gotoSignIn() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(line);
        translate.setDuration(Duration.millis(300));
        translate.setToX(0);
        translate.play();
    }
//Login SignUp Page end












}
