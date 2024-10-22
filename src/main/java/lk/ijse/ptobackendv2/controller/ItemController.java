package lk.ijse.ptobackendv2.controller;

import lk.ijse.ptobackendv2.dto.impl.ItemDto;
import lk.ijse.ptobackendv2.exception.DataPersistException;
import lk.ijse.ptobackendv2.exception.ItemNotFoundException;
import lk.ijse.ptobackendv2.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/v2/itemController")
public class ItemController {
    @Autowired
    private ItemService itemService;
    static Logger logger = LoggerFactory.getLogger(ItemController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto) {
        logger.info("POST Request Received");
        try {
            itemService.saveItem(itemDto);
            logger.info("Item Saved Successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {
            logger.error("Data persistence error: ", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Internal server error: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getAllItems() {
        logger.info("GET Request Received");
        return itemService.loadAllItems();
    }

    @GetMapping(value = "/{itemID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto searchItemByID(@PathVariable("itemID") String itemID) {
        logger.info("GET Request Received For Search Item By ID");
        return itemService.searchItems(itemID);
    }

    @DeleteMapping(value = "/{itemID}")
    public ResponseEntity<Void> deleteItems(@PathVariable("itemID") String itemID) {
        logger.info("DELETE Request Received");
        try {
            itemService.deleteItems(itemID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            logger.error("Item not found: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{itemID}")
    public ResponseEntity<Void> updateItem(@PathVariable("itemID") String itemID, @RequestBody ItemDto itemDto) {
        logger.info("PATCH Request Received");
        try {
            itemService.updateItems(itemID, itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            logger.error("Item not found: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
