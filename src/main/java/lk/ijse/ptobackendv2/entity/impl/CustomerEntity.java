package lk.ijse.ptobackendv2.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lk.ijse.ptobackendv2.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Customer")
public class CustomerEntity implements SuperEntity {
    @Id
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;
}
