package Controller.HomePage.ItemManagement;

import javafx.collections.ObservableList;
import model.dto.ItemDTO;

public interface ItemService {

    void btnAdd(String code,String description,double unitPrice,int qtyOnHand);

    void btnDelete(String code);

    void btnUpdate(String code,String description,double unitPrice,int qtyOnHand);

    ObservableList<ItemDTO> getAllItemDetails();
}
