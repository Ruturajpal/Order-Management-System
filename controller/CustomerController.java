package com.ordermanagement.OM.controller;

import com.ordermanagement.OM.dto.request.CustomerDTO;
import com.ordermanagement.OM.dto.request.UpdateCustomerDTO;
import com.ordermanagement.OM.dto.response.GetAllCustomersDTO;
import com.ordermanagement.OM.other.responseEntity.StandardResponse;
import com.ordermanagement.OM.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/save-customer-details")
    public ResponseEntity<StandardResponse> saveCustomerDetails(@RequestBody CustomerDTO customerDTO){
        String message = customerService.saveCustomerDetails(customerDTO);
        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.CREATED);
        return response ;
    }

    @GetMapping(path = "/get-all-customers")
    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<GetAllCustomersDTO> getAllCustomersDTOS = customerService.getAllActiveCustomers();
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successful", getAllCustomersDTOS), HttpStatus.OK);
    }

    @PutMapping(path = "/unregister-customer/", params = "registeredContactNo")
    public ResponseEntity<StandardResponse> unregisterCustomer(@RequestParam(value = "registeredContactNo") String customerContactNo){
        String message = customerService.unregisterCustomer(customerContactNo);
        return  new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.OK);
    }

    @PutMapping(path = "/update-customer/")
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO) {
        String message = customerService.updateCustomer(updateCustomerDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.OK);
    }

}
