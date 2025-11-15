package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailDTO {
    private int orderId;
    private String itemCode;
    private int qty;
    private double unitPrice;
}
