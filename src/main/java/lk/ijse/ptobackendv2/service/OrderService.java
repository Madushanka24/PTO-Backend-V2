package lk.ijse.ptobackendv2.service;

import lk.ijse.ptobackendv2.dto.impl.CombinedOrderDto;
import lk.ijse.ptobackendv2.dto.impl.OrderDto;

import java.util.List;

public interface OrderService {
    void saveOrder(OrderDto orderDto, CombinedOrderDto combinedOrderDto);
    List<CombinedOrderDto> loadAllOrders();
    void deleteItems(String orderID, String itemID, int orderQty);
    CombinedOrderDto searchOrders(String orderID);
    void updateOrder(String orderID, String itemID, int qtyOnHand, CombinedOrderDto combinedOrderDto);
}
