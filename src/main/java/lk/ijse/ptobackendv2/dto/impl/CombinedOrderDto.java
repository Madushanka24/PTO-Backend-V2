package lk.ijse.ptobackendv2.dto.impl;

import lk.ijse.ptobackendv2.dto.CombinedOrderStatus;
import lk.ijse.ptobackendv2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CombinedOrderDto implements SuperDto, CombinedOrderStatus {
    private String orderID;
    private String orderDate;
    private String customerID;
    private String itemID;
    private String itemName;
    private double itemPrice;
    private int itemQty;
    private int orderQty;
    private double totalPrice;
}
