package lk.ijse.ptobackendv2.service.impl;

import lk.ijse.ptobackendv2.dao.ItemDao;
import lk.ijse.ptobackendv2.dao.OrderDao;
import lk.ijse.ptobackendv2.dao.OrderDetailsDao;
import lk.ijse.ptobackendv2.dto.impl.CombinedOrderDto;
import lk.ijse.ptobackendv2.dto.impl.ItemDto;
import lk.ijse.ptobackendv2.dto.impl.OrderDetailsDto;
import lk.ijse.ptobackendv2.dto.impl.OrderDto;
import lk.ijse.ptobackendv2.entity.impl.ItemEntity;
import lk.ijse.ptobackendv2.entity.impl.OrderDetailsEntity;
import lk.ijse.ptobackendv2.entity.impl.OrderEntity;
import lk.ijse.ptobackendv2.exception.DataPersistException;
import lk.ijse.ptobackendv2.exception.ItemNotFoundException;
import lk.ijse.ptobackendv2.exception.OrderNotFoundException;
import lk.ijse.ptobackendv2.service.ItemService;
import lk.ijse.ptobackendv2.service.OrderService;
import lk.ijse.ptobackendv2.uill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailsDao orderDetailsDao;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrder(OrderDto orderDto, CombinedOrderDto combinedOrderDto) {
        OrderEntity orderEntity = orderDao.save(mapping.toOrderEntity(orderDto));
        if (orderEntity == null) {
            throw new DataPersistException("Order Not Saved");
        } else {
            OrderDetailsDto dto = getOrderDetailsDto(orderDto, combinedOrderDto);
            boolean isSaved = saveOrderDetails(dto);
            if (isSaved) {
                ItemDto itemDto = itemService.searchItems(combinedOrderDto.getItemID());
                int newQty = itemDto.getItemQty() - combinedOrderDto.getOrderQty();
                if (newQty < 0) {
                    throw new IllegalArgumentException("Insufficient item quantity.");
                }
                itemDto.setItemQty(newQty);
                itemService.updateItems(combinedOrderDto.getItemID(),itemDto);
            }
        }
    }

    @Override
    public List<CombinedOrderDto> loadAllOrders() {
        return mapping.toOrderDetailsDtoLists(orderDetailsDao.findAll());
    }

    @Override
    public void deleteItems(String orderID, String itemID, int orderQty) {
        Optional<OrderDetailsEntity> odIdFound = orderDetailsDao.findById(orderID);
        Optional<OrderEntity> oIdFound = orderDao.findById(orderID);
        if (odIdFound.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        }
        orderDetailsDao.deleteById(orderID);
        if (oIdFound.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        }
        orderDao.deleteById(orderID);
        ItemDto itemDto = itemService.searchItems(itemID);
        int newQty = itemDto.getItemQty() + orderQty;
        if (newQty < 0) {
            throw new IllegalArgumentException("Insufficient item quantity.");
        }
        itemDto.setItemQty(newQty);
        itemService.updateItems(itemID,itemDto);
    }

    @Override
    public CombinedOrderDto searchOrders(String orderID) {
        if (orderDetailsDao.existsById(orderID)) {
            OrderDetailsEntity order = orderDetailsDao.getReferenceById(orderID);
            return mapping.toOrderDetailsDto(order);
        } else {
            throw new OrderNotFoundException("Order Not Found");
        }
    }

    @Override
    public void updateOrder(String orderID, String itemID, int qtyOnHand, CombinedOrderDto combinedOrderDto) {
        Optional<OrderDetailsEntity> orderFound = orderDetailsDao.findById(orderID);
        if (orderFound.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        } else {
            OrderDetailsEntity orderDetails = orderFound.get();
            orderDetails.setCustomerID(combinedOrderDto.getCustomerID());
            orderDetails.setItemName(combinedOrderDto.getItemName());
            orderDetails.setItemPrice(combinedOrderDto.getItemPrice());
            orderDetails.setItemQty(qtyOnHand);
            orderDetails.setOrderDate(combinedOrderDto.getOrderDate());
            orderDetails.setOrderQty(combinedOrderDto.getOrderQty());
            orderDetails.setTotalPrice(combinedOrderDto.getTotalPrice());

            Optional<ItemEntity> existingItem = itemDao.findById(itemID);
            if (existingItem.isPresent()) {
                orderDetails.setItem(existingItem.get());
            } else {
                throw new ItemNotFoundException("Item Not Found");
            }
            ItemDto itemDto = itemService.searchItems(itemID);
            itemDto.setItemQty(qtyOnHand);
            itemService.updateItems(itemID, itemDto);
        }
    }

    private boolean saveOrderDetails(OrderDetailsDto orderDetailsDto) {
        OrderDetailsEntity save = orderDetailsDao.save(mapping.toOrderDetailsEntity(orderDetailsDto));
        if (save == null) {
            return false;
        } else {
            return true;
        }
    }

    private static OrderDetailsDto getOrderDetailsDto(OrderDto orderDto, CombinedOrderDto combinedOrderDto) {
        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setDetailsID(orderDto.getOrderID());
        dto.setOrderID(orderDto);
        dto.setItemID(combinedOrderDto.getItemID());
        dto.setItemName(combinedOrderDto.getItemName());
        dto.setItemPrice(combinedOrderDto.getItemPrice());
        dto.setItemQty(combinedOrderDto.getItemQty());
        dto.setOrderQty(combinedOrderDto.getOrderQty());
        dto.setOrderDate(combinedOrderDto.getOrderDate());
        dto.setCustomerID(combinedOrderDto.getCustomerID());
        dto.setTotalPrice(combinedOrderDto.getTotalPrice());
        return dto;
    }
}
