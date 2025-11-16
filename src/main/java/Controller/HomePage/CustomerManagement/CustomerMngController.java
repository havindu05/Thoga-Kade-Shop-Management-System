package Controller.HomePage.CustomerManagement;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMngController implements CustomerService{

    @Override
    public void btnAdd(String id, String name, String address, double salary) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO Customer VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,id);
            preparedStatement.setObject(2,name);
            preparedStatement.setObject(3,address);
            preparedStatement.setObject(4,salary);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void btnDelete(String id, String name, String address, double salary) {

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE id = ?");
            preparedStatement.setObject(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void btnUpdate(String id, String name, String address, double salary) {

        try {
            Connection connection  =DBConnection.getInstance().getConnection();
            String SQL = "UPDATE Customer SET name = ? , address = ? , salary = ?  WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,name);
            preparedStatement.setObject(2,address);
            preparedStatement.setObject(3,salary);
            preparedStatement.setObject(4,id);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomerDetails() {
        ObservableList<CustomerDTO> customerDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM Customer";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                customerDTOS.add(new CustomerDTO(
                     resultSet.getString("id"),
                     resultSet.getString("name"),
                     resultSet.getString("address"),
                     resultSet.getDouble("salary")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDTOS;
    }
}
