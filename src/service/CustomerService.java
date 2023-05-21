package com.ordermanagement.OM.service;

import com.ordermanagement.OM.dto.request.CustomerDTO;
import com.ordermanagement.OM.dto.request.UpdateCustomerDTO;
import com.ordermanagement.OM.dto.response.GetAllCustomersDTO;

import java.util.List;

public interface CustomerService {
    String saveCustomerDetails(CustomerDTO customerDTO);
    List<GetAllCustomersDTO> getAllActiveCustomers();
    String unregisterCustomer(String generatedCustomerId);
    String updateCustomer(UpdateCustomerDTO updateCustomerDTO);
}
