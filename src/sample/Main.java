package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.text.TabExpander;
import javax.swing.text.TabableView;

public class Main extends Application {
    TableView<products> table;
    TextField inputname,inputprice;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("tableview.fxml"));
        primaryStage.setTitle("Hello World");

        HBox hbox=new HBox();
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.setSpacing(10);

        table=new TableView<>();
        table.getItems().addAll(getproducts());
        TableColumn<products,String> product_name=new TableColumn<>("name");
        TableColumn<products,Double> product_price=new TableColumn<>("price");
        product_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        product_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        product_name.setMinWidth(10);
        product_price.setMinWidth(10);
        table.getColumns().addAll(product_name,product_price);
        table.setEditable(true);

        inputname=new TextField();
        inputname.setPromptText("input a name of the product");
        inputprice=new TextField();
        inputprice.setPromptText("input the price");

        Button abutton=new Button("input");
        abutton.setOnAction(e->addintolist(inputname,inputprice));
        Button delete=new Button("delete");
        delete.setOnAction(e->delete());

        hbox.getChildren().addAll(table,inputname,inputprice,abutton,delete);
        primaryStage.setScene(new Scene(hbox, 600, 275));
        primaryStage.show();
    }

    private void addintolist(TextField a,TextField b) {
        products addproduct=new products(a.getText(),Double.parseDouble(b.getText()));
        table.getItems().addAll(addproduct);
        inputname.clear();
        inputprice.clear();
    }

    private void delete()
    {
        ObservableList<products> list=table.getItems();
        ObservableList<products> deleteone=table.getSelectionModel().getSelectedItems();
        //list.remove(deleteone);
        deleteone.forEach(row -> table.getItems().remove(row));
    }

    private ObservableList<products> getproducts() {
        ObservableList<products> list=FXCollections.observableArrayList();
        list.addAll(new products("cream",100));
        list.addAll(new products("eye cream",200));
        list.addAll(new products("lotion",100));
        list.addAll(new products("face washer",100));

        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
