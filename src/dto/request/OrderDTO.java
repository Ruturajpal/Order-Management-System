package com.ordermanagement.OM.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private boolean isARegisteredCustomer;
    private String customerRegPhoneNo;
    private List<PurchaseItems> purchaseItems;
}


