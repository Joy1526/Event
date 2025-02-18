package org.example.event;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.bson.Document;


public class Controller implements Initializable{
    //DataBase
    MongoDatabase database;
    MongoCollection<Document> usersCollection;

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


    //SignUp
    @FXML
    private TextField signUpUserName;
    @FXML
    private TextField signUpEmail;
    @FXML
    private PasswordField signUpPassword;
    @FXML
    private RadioButton signUpVendorButton;
    @FXML
    private RadioButton signUpClientButton;

    //Login
    @FXML
    private TextField loginUserName;
    @FXML
    private PasswordField loginPassword;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //For Login SignUp Page
        //this.signinPane.setVisible(true);
        //this.signupPane.setVisible(false);
        //fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Downloads"));

        //myItemScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        database=MongoDBConnection.getDatabase();
        usersCollection=database.getCollection("users");
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    //Login SignUp Page Start

    @FXML
    public void SignIn() {

		/*SignInButton.setStyle("-fx-background-color: #d6d6d6;");
		SignUpButton.setStyle("-fx-background-color: #f2f2f2;");
		*/
        signinPane.setVisible(true);
        signupPane.setVisible(false);
        gotoSignIn();
    }

    @FXML
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

    String selectedRole() {
        if (signUpVendorButton.isSelected()) return "vendor";
        if (signUpClientButton.isSelected()) return "client";
        return null;
    }
    void emptysignupFields(){
        signUpUserName.setText("");
        signUpPassword.setText("");
        signUpEmail.setText("");
    }
    @FXML
    void SignUpButton(ActionEvent event) {
        String username=signUpUserName.getText();
        String password=signUpPassword.getText();
        String email=signUpEmail.getText();

        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || selectedRole()==null){
            //showAlert("Error","Please fill all fields!");
            AlertHelper.showStyledAlert("Error","Please fill all fields!", Alert.AlertType.ERROR);
            return;
        }
        Document existingUser=usersCollection.find(Filters.eq("username",username)).first();
        if(existingUser != null) {
            //showAlert("Error", "Username already taken.");
            AlertHelper.showStyledAlert("Error","Username already taken.", Alert.AlertType.ERROR);
            return;
        }
        Document newUser=new Document("username",username);
        newUser.append("password",password);
        newUser.append("email",email);
        newUser.append("role",selectedRole());
        usersCollection.insertOne(newUser);
        showAlert("Success","User successfully registered!");
        AlertHelper.showStyledAlert("Success","User successfully registered!", Alert.AlertType.INFORMATION);
        signinPane.setVisible(false);
        signupPane.setVisible(true);
        emptysignupFields();
        gotoSignUp();

    }
    @FXML
    void LoginButton(ActionEvent event) {
        String username=loginUserName.getText();
        String password=loginPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            //showAlert("Error", "All fields are required.");
            AlertHelper.showStyledAlert("Error", "All fields are required.", Alert.AlertType.ERROR);

            return;
        }
        //check
        Document user=usersCollection.find(Filters.eq("username",username)).first();
        if(user!=null && Objects.equals(user.getString("password"),password)){
            //showAlert("success","Login Successful!");
            AlertHelper.showStyledAlert("success","Login Successful!", Alert.AlertType.INFORMATION);


        }
        else{
            showAlert("Error","Login Failed!");
            AlertHelper.showStyledAlert("Error","Login Failed!", Alert.AlertType.ERROR);

            return;
        }
        changeFXML.switchScene(event,"VendorsPage.fxml");



        //changeFXML.switchScene(event,"VendorsPage.fxml");


    }

//Login SignUp Page end












}
