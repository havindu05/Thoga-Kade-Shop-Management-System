package Controller.HomePage.CustomerManagement;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustomerService {


    void btnAdd(String id,String name,String address,double salary);

    void btnDelete(String id,String name,String address,double salary);

    void btnUpdate(String id,String name,String address,double salary);

    ObservableList<CustomerDTO> getAllCustomerDetails();
}
