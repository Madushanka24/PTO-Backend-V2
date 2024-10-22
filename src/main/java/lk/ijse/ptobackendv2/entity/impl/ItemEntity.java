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
@Table(name = "Items")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemID;
    private String itemName;
    private double itemPrice;
    private int itemQty;
    @OneToMany(mappedBy = "item")
    private List<OrderDetailsEntity> orderDetailsEntities;
}
