package com.ordermanagement.OM.dto.response;

import com.ordermanagement.OM.other.enums.BranchNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllCustomersDTO {
    private String customerGeneratedId;
    private String customerName;
    private String customerAddress;
    private String customerNic;
    private String customerContactNumber;
    private BranchNames branchName;
}
