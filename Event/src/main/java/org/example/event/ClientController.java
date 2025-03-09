package org.example.event;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    MongoDatabase database;
    MongoCollection<Document> clientsCollection;
    MongoCollection<Document> vendorsCollection;
    static String userName;
    static String userID;
    static String clientID;
    @FXML
    Button CreateEvent;
    @FXML
    ScrollPane eventScrollPane;
    @FXML
    VBox eventVBox;
    @FXML
    AnchorPane createEventAnchorPane;
    @FXML
    TextField eventNameField;
    @FXML
    TextField eventLocationField;
    @FXML
    TextArea eventDescriptionField;
    @FXML
    DatePicker eventDatePicker;
    @FXML
    Button createEventButton;
    @FXML
    ScrollPane itemsScrollPane;
    @FXML
    FlowPane itemsFlowPane;
    @FXML
    Line buttonLine;

    int c=0;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        database=MongoDBConnection.getDatabase();
        clientsCollection=database.getCollection("clients");
        vendorsCollection=database.getCollection("vendors");

        eventScrollPane.setVisible(true);
        createEventAnchorPane.setVisible(false);
        itemsScrollPane.setVisible(false);


        try {
            LoadAllItems();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void EventFunc(){
        eventScrollPane.setVisible(true);
        createEventAnchorPane.setVisible(false);
        itemsScrollPane.setVisible(false);
        gotoEvent();
    }
    public void gotoEvent() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(buttonLine);
        translate.setDuration(Duration.millis(300));
        translate.setToX(0);
        translate.play();
    }
    public void gotoVendor() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(buttonLine);
        translate.setDuration(Duration.millis(300));
        translate.setToX(129);
        translate.play();
    }
    public void allVendorFunc() throws IOException {
        eventScrollPane.setVisible(false);
        createEventAnchorPane.setVisible(false);

        itemsScrollPane.setVisible(true);
        itemsFlowPane.setVisible(true);
        gotoVendor();
    }


    public void createEventfunc() {

            eventScrollPane.setVisible(false);
            createEventAnchorPane.setVisible(true);

    }
    public void createEventButton() throws IOException {
        String eventName=eventNameField.getText();
        String eventLocation=eventLocationField.getText();
        String eventDescription=eventDescriptionField.getText();
        String eventDate=eventDatePicker.getValue().toString();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("event.fxml"));
        AnchorPane eventPane = loader.load();
        //Label eventName2 = (Label) eventPane.lookup("#eventName2");
        Label eventNameLabel = (Label) eventPane.lookup("#eventName");
        //Label eventLocation2 = (Label) eventPane.lookup("#eventLocation2");
        Label eventLocationLabel = (Label) eventPane.lookup("#eventLocation");
        //Label eventDateLabel2= (Label) eventPane.lookup("#eventDate2");
        Label eventDateLabel= (Label) eventPane.lookup("#eventDate");
        eventNameLabel.setText(" "+eventName);
        eventLocationLabel.setText(" "+eventLocation);
        eventDateLabel.setText(" "+eventDate);
        eventVBox.getChildren().add(eventPane);
        eventScrollPane.setVisible(true);
        createEventAnchorPane.setVisible(false);

    }
    public void LoadAllItems() throws IOException {



        for (Document vendor : vendorsCollection.find()) {

            if (vendor.containsKey("items")) {
                List<Document> items = (List<Document>) vendor.get("items");

                for (Document item : items) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VendorProfile.fxml"));
                    AnchorPane itemPane = loader.load();
                    Label productNameLabel = (Label) itemPane.lookup("#VendorItemName");
                    Label productPriceLabel = (Label) itemPane.lookup("#VendorItemPrice");
                    Label productCompanyLabel = (Label) itemPane.lookup("#VendorItemCompanyName");
                    Label id = (Label) itemPane.lookup("#userid");
                    ImageView productImageView = (ImageView) itemPane.lookup("#VendorItemImage");
                    Button buyButton = (Button) itemPane.lookup("#VendorItemBuy");

                    productNameLabel.setText(item.getString("Product Name"));
                    productPriceLabel.setText(item.getString("Price"));
                    productCompanyLabel.setText(vendor.getString("username"));
                    id.setText(vendor.getString("userId"));
                    // Load image if available
                    String imageUrl = item.getString("imageURL");
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Image image = new Image(imageUrl);
                        productImageView.setImage(image);
                    }
                    buyButton.setOnAction(event -> {
                        handleBuyNow(itemPane);
                    });
                    itemsFlowPane.getChildren().add(itemPane);
                }
            }
        }

    }
    public void handleBuyNow(AnchorPane itemPane) {
        Label nameLabel = (Label) itemPane.lookup("#VendorItemName");
        Label priceLabel = (Label) itemPane.lookup("#VendorItemPrice");
        Label id = (Label) itemPane.lookup("#userid");
        ImageView productImageView = (ImageView) itemPane.lookup("#VendorItemImage");

        String productName = nameLabel.getText();
        String productPrice = priceLabel.getText();
        String vendor=id.getText();
        Image productImage = productImageView.getImage();
        System.out.println("Buying: " + productName + " for " + productPrice);
    }




}
