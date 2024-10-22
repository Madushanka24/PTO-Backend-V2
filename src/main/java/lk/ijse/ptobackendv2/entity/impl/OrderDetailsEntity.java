package lk.ijse.ptobackendv2.entity.impl;

import jakarta.persistence.*;
import lk.ijse.ptobackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "OrderDetails")
public class OrderDetailsEntity implements SuperEntity {
    @Id
    private int orderDetailsID;
    private String itemName;
    private double itemPrice;
    private int itemQty;
    private int orderQty;
    private String orderDate;
    private String customerID;
    private double totalPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderID",referencedColumnName = "orderID")
    private OrderEntity order;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "itemID",referencedColumnName = "itemID")
    private ItemEntity item;
}
