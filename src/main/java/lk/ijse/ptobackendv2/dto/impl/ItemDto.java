package lk.ijse.ptobackendv2.dto.impl;

import lk.ijse.ptobackendv2.dto.ItemStatus;
import lk.ijse.ptobackendv2.dto.SuperDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto implements SuperDto, ItemStatus {
    private String itemID;
    private String itemName;
    private double itemPrice;
    private int itemQty;
}
