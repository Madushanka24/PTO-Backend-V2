package lk.ijse.ptobackendv2.dto.impl;

import lk.ijse.ptobackendv2.dto.CustomerStatus;
import lk.ijse.ptobackendv2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto implements SuperDto, CustomerStatus {
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
}
