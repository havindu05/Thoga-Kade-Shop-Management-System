package Controller.HomePage.ItemManagement;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.dto.CustomerDTO;
import model.dto.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMngController implements ItemService{

    @Override
    public void btnAdd(String code, String description, double unitPrice, int qtyOnHand) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "INSERT INTO Item VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,code);
            preparedStatement.setObject(2,description);
            preparedStatement.setObject(3,unitPrice);
            preparedStatement.setObject(4,qtyOnHand);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void btnDelete(String code) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Item WHERE id = ?");
            preparedStatement.setObject(1,code);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void btnUpdate(String code, String description, double unitPrice, int qtyOnHand) {
        try {
            Connection connection  =DBConnection.getInstance().getConnection();
            String SQL = "UPDATE Item SET description = ? , unitprice = ? , qtyOnHand = ?  WHERE code = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setObject(1,description);
            preparedStatement.setObject(2,unitPrice);
            preparedStatement.setObject(3,qtyOnHand);
            preparedStatement.setObject(4,code);

            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<ItemDTO> getAllItemDetails() {
        ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DBConnection.getInstance().getConnection();
            String SQL = "SELECT * FROM Item";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                itemDTOS.add(new ItemDTO(
                        resultSet.getString("code"),
                        resultSet.getString("description"),
                        resultSet.getDouble("unitPrice"),
                        resultSet.getInt("qtyOnHand")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemDTOS;
    }
}
