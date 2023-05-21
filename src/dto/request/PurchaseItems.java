package com.ordermanagement.OM.dto.request;

import com.ordermanagement.OM.other.enums.MeasuringUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseItems {
    private String itemGeneratedCode;
    private float quantity;
    private MeasuringUnitType measuringUnitType;
}
