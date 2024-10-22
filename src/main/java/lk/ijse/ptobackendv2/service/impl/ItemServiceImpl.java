package lk.ijse.ptobackendv2.service.impl;

import lk.ijse.ptobackendv2.dao.ItemDao;
import lk.ijse.ptobackendv2.dto.impl.ItemDto;
import lk.ijse.ptobackendv2.entity.impl.ItemEntity;
import lk.ijse.ptobackendv2.exception.DataPersistException;
import lk.ijse.ptobackendv2.exception.ItemNotFoundException;
import lk.ijse.ptobackendv2.service.ItemService;
import lk.ijse.ptobackendv2.uill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;
    @Override
    public void saveItem(ItemDto itemDto) {
        ItemEntity save = itemDao.save(mapping.toItemEntity(itemDto));
        if (save == null) {
            throw new DataPersistException("Item not found");
        }
    }

    @Override
    public List<ItemDto> loadAllItems() {
        return mapping.toItemDtoList(itemDao.findAll());
    }

    @Override
    public ItemDto searchItems(String itemID) {
        if (itemDao.existsById(itemID)) {
            ItemEntity item = itemDao.getReferenceById(itemID);
            return mapping.toItemDto(item);
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    @Override
    public void deleteItems(String itemID) {
        Optional<ItemEntity> itemFound = itemDao.findById(itemID);
        if (itemFound.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            itemDao.deleteById(itemID);
        }
    }

    @Override
    public void updateItems(String itemID, ItemDto itemDto) {
        Optional<ItemEntity> itemFound = itemDao.findById(itemID);
        if (itemFound.isEmpty()) {
            throw new ItemNotFoundException("Item not found");
        } else {
            itemFound.get().setItemName(itemDto.getItemName());
            itemFound.get().setItemPrice(itemDto.getItemPrice());
            itemFound.get().setItemQty(itemDto.getItemQty());
        }
    }
}
