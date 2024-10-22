package lk.ijse.ptobackendv2.dto.impl;

import lk.ijse.ptobackendv2.dto.OrderDetailsStatus;
import lk.ijse.ptobackendv2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailsDto implements SuperDto, OrderDetailsStatus {
    private String detailsID;
    private OrderDto orderID;
    private String itemID;
    private String itemName;
    private double itemPrice;
    private int itemQty;
    private int orderQty;
    private String orderDate;
    private String customerID;
    private double totalPrice;
}
