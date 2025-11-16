package Controller.HomePage.CustomerManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerMngInfoController implements Initializable {

    @FXML
    private TableColumn<CustomerDTO, String> colAddress;

    @FXML
    private TableColumn<CustomerDTO, String> colId;

    @FXML
    private TableColumn<CustomerDTO, String> colName;

    @FXML
    private TableColumn<CustomerDTO, Double> colSalary;

    @FXML
    private TableView<CustomerDTO> tblCustomerMng;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustId;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtname;

    private final CustomerMngController customerService = new CustomerMngController();
    private final ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();


    @FXML
    void btnAdd(ActionEvent event) {

        if (!isValidInput()) return;

        customerService.btnAdd(
                txtCustId.getText(),
                txtname.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        loadTable();
        clearFields();
        alert("Success", "Customer Added Successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void btnUpdate(ActionEvent event) {

        if (!isValidInput()) return;

        customerService.btnUpdate(
                txtCustId.getText(),
                txtname.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText())
        );

        loadTable();
        alert("Updated", "Customer Updated Successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void btnDelete(ActionEvent event) {

        if (txtCustId.getText().isEmpty()) {
            alert("Error", "Enter Customer ID to delete!", Alert.AlertType.WARNING);
            return;
        }

        customerService.btnDelete(txtCustId.getText(), null, null, 0);
        loadTable();
        clearFields();
        alert("Deleted", "Customer Deleted Successfully!", Alert.AlertType.INFORMATION);
    }

    @FXML
    void btnClear(ActionEvent event) {
        clearFields();
    }


    private void loadTable() {
        ObservableList<CustomerDTO> customers = customerService.getAllCustomerDetails();
        tblCustomerMng.setItems(customers);
    }

    private void setSelectedValue(CustomerDTO dto) {
        txtCustId.setText(dto.getId());
        txtname.setText(dto.getName());
        txtAddress.setText(dto.getAddress());
        txtSalary.setText(String.valueOf(dto.getSalary()));
    }


    private void clearFields() {
        txtCustId.clear();
        txtname.clear();
        txtAddress.clear();
        txtSalary.clear();
    }

    private boolean isValidInput() {

        if (txtCustId.getText().isEmpty() || txtname.getText().isEmpty()
                || txtAddress.getText().isEmpty() || txtSalary.getText().isEmpty()) {

            alert("Warning", "All fields must be filled!", Alert.AlertType.WARNING);
            return false;
        }

        try {
            Double.parseDouble(txtSalary.getText());
        } catch (NumberFormatException e) {
            alert("Error", "Salary must be a valid number!", Alert.AlertType.ERROR);
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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        loadTable();


        tblCustomerMng.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) setSelectedValue(newSel);
        });
    }
}
