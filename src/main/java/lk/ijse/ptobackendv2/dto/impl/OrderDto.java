package lk.ijse.ptobackendv2.dto.impl;

import lk.ijse.ptobackendv2.dto.OrderStatus;
import lk.ijse.ptobackendv2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto implements SuperDto, OrderStatus {
    private String orderID;
    private String orderDate;
    private String customerID;
}
