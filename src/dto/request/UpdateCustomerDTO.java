package com.ordermanagement.OM.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCustomerDTO {
    private String customerGeneratedId;
    private String customerName;
    private String customerAddress;
    private String customerContactNumber;
}
