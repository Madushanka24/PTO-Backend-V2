package lk.ijse.ptobackendv2.controller;

import lk.ijse.ptobackendv2.dto.impl.CustomerDto;
import lk.ijse.ptobackendv2.exception.CustomerNotFoundException;
import lk.ijse.ptobackendv2.exception.DataPersistException;
import lk.ijse.ptobackendv2.service.CustomerService;
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
@RequestMapping("/api/v2/customerController")

public class CustomerController {
    @Autowired
    private CustomerService customerService;
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerDto customerDto) {
        logger.info("POST Request Received");
        try {
            customerService.saveCustomer(customerDto);
            logger.info("Customer Saved Successfully");
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
    public List<CustomerDto> getAllCustomers() {
        logger.info("GET Request Received");
        return customerService.loadAllCustomers();
    }

    @GetMapping(value = "/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto searchCustomersByID(@PathVariable("customerID") String customerId) {
        logger.info("GET Request Received For Search Customer By ID");
        return customerService.getCustomersByID(customerId);
    }

    @GetMapping(value = "/{customerPhoneNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto searchCustomersByPhoneNumber(@PathVariable("customerPhoneNumber") String customerPhoneNumber) {
        System.out.println(customerPhoneNumber);
        logger.info("GET Request Received For Search Customer By PhoneNumber");
        return customerService.getCustomersByPhoneNumber(customerPhoneNumber);
    }

    @DeleteMapping(value = "/{customerID}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerID") String customerID) {
        logger.info("DELETE Request Received");
        try {
            customerService.deleteCustomers(customerID);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{customerID}")
    public ResponseEntity<Void> updateCustomer(@PathVariable("customerID") String customerID, @RequestBody CustomerDto customerDto) {
        logger.info("PATCH Request Received");
        try {
            customerService.updateCustomers(customerID, customerDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: ", e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}