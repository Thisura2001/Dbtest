package lk.ijse.Dbtest.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.Dbtest.DB.Dbconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerFormController {

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txttel;


    public void btnSaveonAction(ActionEvent actionEvent) {
        try {

           Connection connection = Dbconnection.getInstance().getConnection();

            String sql = "INSERT INTO customer VALUES (?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            String name = txtname.getText();
            String address = txtaddress.getText();
            String tel = txttel.getText();

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, address);
            preparedStatement.setString(3, tel);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION,"Customer saved successfully").show();
            }

            connection.close();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        txtname.setText("");
        txtaddress.setText("");
        txttel.setText("");
    }

    public void btnDeleteonAction(ActionEvent actionEvent) {
        try {
            Connection connection = Dbconnection.getInstance().getConnection();

            String sql = "DELETE FROM customer WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            String name = txtname.getText();


            preparedStatement.setString(1, name);


            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Deleted successfully").show();
            }
            connection.close();

        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/item_form.fxml"));


        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
        stage.setScene(scene);

        stage.show();
    }
}

