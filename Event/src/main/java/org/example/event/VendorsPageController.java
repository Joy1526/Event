package org.example.event;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VendorsPageController implements Initializable {
    FileChooser fileChooser=new FileChooser();

    //for Vendors Items
    @FXML
    AnchorPane vendorItem;



    //for Vendors Page
    private int a1=0,a2=1,a3=2;//rows
    private int grid=0;
    private int c=0;
    @FXML
    Line vendorLine;
    @FXML
    ScrollPane myItemScrollPane;
    @FXML
    AnchorPane addItemAnchorPane;
    @FXML
    FlowPane myItemsflowPane=new FlowPane();
    @FXML
    TextField productName;
    @FXML
    TextField productId;
    @FXML
    TextField productPrice;
    @FXML
    ImageView sampleProductImage;

    @FXML
    ImageView VendorItemImage;
    @FXML
    Label VendorItemName;
    @FXML
    Label VendorItemPrice;
    @FXML
    Button VendorItemBuy;

    /*public void addColumn() {
        RowConstraints row1 = new RowConstraints();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setMinWidth(310);
        col1.setPrefWidth(310);
        col1.setMaxWidth(310);
        myItemsGridPane.getColumnConstraints().add(col1);
    }
    public void addRow() {
        RowConstraints row1 = new RowConstraints();
        row1.setMinHeight(272);
        row1.setPrefHeight(272);
        row1.setMaxHeight(272);
        myItemsGridPane.getRowConstraints().add(row1);
    }
    */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        fileChooser.setInitialDirectory(new File("C:\\Users\\User\\Downloads"));
        myItemsflowPane.setHgap(5);  // 10-pixel horizontal gap between items
        myItemsflowPane.setVgap(15);  // 10-pixel vertical gap between items
			/*ColumnConstraints col1 = new ColumnConstraints();
			col1.setMinWidth(310);
			col1.setPrefWidth(310);
			col1.setMaxWidth(310);
			ColumnConstraints col2 = new ColumnConstraints();
			col1.setMinWidth(310);
			col1.setPrefWidth(310);
			col1.setMaxWidth(310);
			ColumnConstraints col3 = new ColumnConstraints();
			col1.setMinWidth(310);
			col1.setPrefWidth(310);
			col1.setMaxWidth(310);
			RowConstraints row1 = new RowConstraints();
			row1.setMinHeight(180);
			row1.setPrefHeight(180);
			row1.setMaxHeight(180);
			myItemsGridPane.getColumnConstraints().addAll(col1,col2,col3);
			myItemsGridPane.getRowConstraints().add(row1);
			*/
			/*addRow();
			addColumn();
			addColumn();
			addColumn();
``			*/
			/*myItemsGridPane.setHgap(10);
			myItemsGridPane.setVgap(10);
			*/

    }






    //Vendors Page start
    public void myItems() {
        gotoItems();
    }
    public void orders() {
        gotoorders();
    }
    public void profits() {
        gotoprofits();
    }

    public void gotoItems() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(vendorLine);
        translate.setDuration(Duration.millis(300));
        translate.setToX(0);
        translate.play();
    }
    public void gotoorders() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(vendorLine);
        translate.setDuration(Duration.millis(300));
        translate.setToX(120);
        translate.play();
    }
    public void gotoprofits() {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(vendorLine);
        translate.setDuration(Duration.millis(300));
        translate.setToX(240);
        translate.play();
    }
    public void addItemFunc() throws IOException {
        if(c%2==0) {
            //myItemScrollPane.setVisible(false);
            myItemsflowPane.setVisible(false);
            addItemAnchorPane.setVisible(true);
        }
        else {
            System.out.println(grid+" "+a1);
            if(grid==3) {
                grid=0;
                //addRow();
                a1+=1;
                //a2+=3;
                //a3+=3;
            }
            ImageView temp=new ImageView(sampleProductImage.getImage());//temp chilo
            temp.setFitWidth(307);
            temp.setFitHeight(180);
            Label pName=new Label(productName.getText());
            Label pPrice=new Label(productPrice.getText()+" Tk");

            //myItemsGridPane.add(vendorItem,0,0);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VendorItems.fxml"));
            AnchorPane itemPane = loader.load();

            Label productNameLabel = (Label) itemPane.lookup("#VendorItemName");
            Label productPriceLabel = (Label) itemPane.lookup("#VendorItemPrice");
            ImageView productImageView = (ImageView) itemPane.lookup("#VendorItemImage");
            Button productButton = (Button) itemPane.lookup("#VendorItemButton");

            productImageView.setImage(sampleProductImage.getImage());
            productNameLabel.setText(pName.getText());
            productPriceLabel.setText(pPrice.getText());

            myItemsflowPane.getChildren().add(itemPane);

            //myItemsGridPane.add(pName,grid,a2);
            //myItemsGridPane.add(pPrice,grid,a3);
            sampleProductImage.setImage(new Image(getClass().getResourceAsStream("example.png")));

            grid++;

            addItemAnchorPane.setVisible(false);
            myItemsflowPane.setVisible(true);
            //myItemScrollPane.setVisible(true);

        }
        c++;
    }

    public void importImage() {
        fileChooser.setTitle("Select Product Image");
        File file=fileChooser.showOpenDialog(new Stage());
        sampleProductImage.setImage(new Image(file.toURI().toString()));
			/*Image image = new Image(file.toURI().toString());
			System.out.println(file.toURI().toString());
			sampleProductImage.setImage(image);
			 */
    }

}
