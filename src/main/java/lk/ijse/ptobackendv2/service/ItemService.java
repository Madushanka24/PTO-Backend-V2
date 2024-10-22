package lk.ijse.ptobackendv2.service;

import lk.ijse.ptobackendv2.dto.impl.ItemDto;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDto itemDto);
    List<ItemDto> loadAllItems();
    void deleteItems(String itemID);
    void updateItems(String itemID, ItemDto itemDto);
    ItemDto searchItems(String itemID);
}
