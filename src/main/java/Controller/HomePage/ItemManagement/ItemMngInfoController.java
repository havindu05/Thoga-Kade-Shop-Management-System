package Controller.HomePage.ItemManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.ItemDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemMngInfoController implements Initializable {

    @FXML
    private TableColumn<ItemDTO, String> colCode;

    @FXML
    private TableColumn<ItemDTO, String> colDescription;

    @FXML
    private TableColumn<ItemDTO, Integer> colQuantity;

    @FXML
    private TableColumn<ItemDTO, Double> colUnitPrice;

    @FXML
    private TableView<ItemDTO> tblItemManagement;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    private final ItemMngController itemService = new ItemMngController();
    private final ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();

    @FXML
    void btnAdd(ActionEvent event) {
        if (!isValidInput()) return;

        itemService.btnAdd(
                txtCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQuantity.getText())
        );

        loadTable();
        clearFields();
        alert("Success", "Item Added Successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void btnUpdate(ActionEvent event) {
        if (!isValidInput()) return;

        itemService.btnUpdate(
                txtCode.getText(),
                txtDescription.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQuantity.getText())
        );

        loadTable();
        alert("Updated", "Item Updated Successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void btnDelete(ActionEvent event) {
        if (txtCode.getText().isEmpty()) {
            alert("Error", "Enter Item Code to delete!", Alert.AlertType.WARNING);
            return;
        }

        itemService.btnDelete(txtCode.getText());
        loadTable();
        clearFields();
        alert("Deleted", "Item Deleted Successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void btnClear(ActionEvent event) {
        clearFields();
    }



    public void btnMainMenue(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void btnorderMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/OrderMngInfo.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void btnitemMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ItemMngInfo.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void btnOrderDetMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/OrderDetMngInfo.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void btnCustomerMng(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/CustomerMngInfo.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }



    private void loadTable() {
        ObservableList<ItemDTO> items = itemService.getAllItemDetails();
        tblItemManagement.setItems(items);
    }

    private void setSelectedValue(ItemDTO dto) {
        txtCode.setText(dto.getCode());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
        txtQuantity.setText(String.valueOf(dto.getQtyOnHand()));
    }

    private void clearFields() {
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQuantity.clear();
    }

    private boolean isValidInput() {
        if (txtCode.getText().isEmpty() || txtDescription.getText().isEmpty()
                || txtUnitPrice.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
            alert("Warning", "All fields must be filled!", Alert.AlertType.WARNING);
            return false;
        }

        try {
            Double.parseDouble(txtUnitPrice.getText());
            Integer.parseInt(txtQuantity.getText());
        } catch (NumberFormatException e) {
            alert("Error", "Unit Price must be a number and Quantity must be an integer!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }

    private void alert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.show();
    }

    private void loadPage(String fxmlPath, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        loadTable();


        tblItemManagement.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) setSelectedValue(newSel);
        });
    }
}
