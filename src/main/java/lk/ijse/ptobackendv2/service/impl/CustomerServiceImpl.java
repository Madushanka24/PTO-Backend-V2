package lk.ijse.ptobackendv2.service.impl;

import lk.ijse.ptobackendv2.dao.CustomerDao;
import lk.ijse.ptobackendv2.dto.impl.CustomerDto;
import lk.ijse.ptobackendv2.entity.impl.CustomerEntity;
import lk.ijse.ptobackendv2.exception.CustomerNotFoundException;
import lk.ijse.ptobackendv2.service.CustomerService;
import lk.ijse.ptobackendv2.utill.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private Mapping mapping;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        CustomerEntity save = customerDao.save(mapping.toCustomerEntity(customerDto));
        if (save == null) {
            throw new DataAccessException("Customer not saved") {
                @Override
                public Throwable getRootCause() {
                    return super.getRootCause();
                }
            };
        }
    }

    @Override
    public List<CustomerDto> loadAllCustomers() {
        return mapping.toCustomerDtoList(customerDao.findAll());
    }

    @Override
    public void deleteCustomers(String customerID) {
        Optional<CustomerEntity> customerFound = customerDao.findById(customerID);
        if (customerFound.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        } else {
            customerDao.deleteById(customerID);
        }
    }

    @Override
    public void updateCustomers(String customerID, CustomerDto customerDto) {
        Optional<CustomerEntity> customerFound = customerDao.findById(customerID);
        if (customerFound.isEmpty()) {
            throw new CustomerNotFoundException("Customer not found");
        } else {
            customerFound.get().setCustomerName(customerDto.getCustomerName());
            customerFound.get().setCustomerAddress(customerDto.getCustomerAddress());
            customerFound.get().setCustomerPhoneNumber(customerDto.getCustomerPhoneNumber());
        }
    }

    @Override
    public CustomerDto getCustomersByID(String customerId) {
        if (customerDao.existsById(customerId)) {
            CustomerEntity customer = customerDao.getReferenceById(customerId);
            return mapping.toCustomerDto(customer);
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }

    @Override
    public CustomerDto getCustomersByPhoneNumber(String customerPhoneNumber) {
        if (customerDao.existsById(customerPhoneNumber)) {
            CustomerEntity customer = customerDao.getReferenceById(customerPhoneNumber);
            return mapping.toCustomerDto(customer);
        } else {
            throw new CustomerNotFoundException("Customer not found");
        }
    }
}