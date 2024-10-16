package lk.ijse.ptobackendv2.service;

import lk.ijse.ptobackendv2.dto.impl.CustomerDto;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);
    List<CustomerDto> loadAllCustomers();
    void deleteCustomers(String customerID);
    void updateCustomers(String customerID, CustomerDto customerDto);
    CustomerDto getCustomersByID(String customerId);
    CustomerDto getCustomersByPhoneNumber(String customerPhoneNumber);
}
