package com.ordermanagement.OM.dto.response;

import com.ordermanagement.OM.other.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetItemListDTO {
    private String itemName;
    private MeasuringUnitType measuringUnitType;
    private float sellingPrice;
}
