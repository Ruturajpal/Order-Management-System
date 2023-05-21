package com.ordermanagement.OM.dto.request;

import com.ordermanagement.OM.other.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private String note;
    private float supplierPrice;
    private float sellingPrice;
}
