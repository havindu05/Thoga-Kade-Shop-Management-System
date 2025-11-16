package model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrdersDTO {
    private String id;
    private String date;
    private String customerId;
}
