package com.ordermanagement.OM.service.impl;

import com.ordermanagement.OM.dto.request.CustomerDTO;
import com.ordermanagement.OM.dto.request.UpdateCustomerDTO;
import com.ordermanagement.OM.dto.response.GetAllCustomersDTO;
import com.ordermanagement.OM.entity.Customer;
import com.ordermanagement.OM.other.mappers.MapStructMapper;
import com.ordermanagement.OM.repo.CustomerRepo;
import com.ordermanagement.OM.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public String saveCustomerDetails(CustomerDTO customerDTO) {

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        int i = Math.abs(random.nextInt()%25);
        String customerGenID = String.valueOf(year).substring(2)+String.valueOf(customerRepo.count()+1) + alphabet.charAt(i);

        ArrayList<ArrayList<Object>> pastPurchases = new ArrayList<>();

        Customer customer = mapStructMapper.customerDetailsDtoToEntity(customerDTO);
        customer.setCustomerActiveStatus(true);
        customer.setCustomerGeneratedId(customerGenID);
        customer.setPastPurchases(pastPurchases);
        customerRepo.save(customer);
        return "Customer with id " + customerGenID + " saved Successfully";
    }

    @Override
    public List<GetAllCustomersDTO> getAllActiveCustomers() {
        List<Customer> customerActiveList = customerRepo.getAllByCustomerActiveStatus(true);
        List<GetAllCustomersDTO> getAllCustomersDTOList = new ArrayList<>();

        if (customerRepo.count()>0){
            for (Customer customer:customerActiveList){
                GetAllCustomersDTO getAllCustomersDTO=mapStructMapper.allCustomersEntityToDTO(customer);
                getAllCustomersDTOList.add(getAllCustomersDTO);
            }
            return getAllCustomersDTOList;
        }
        else{
            throw new RuntimeException("No Customers found");
        }
    }

    @Override
    public String unregisterCustomer(String customerContactNo) {
       if (customerRepo.existsByCustomerContactNumberAndCustomerActiveStatus(customerContactNo, true )){
           Customer customer = customerRepo.getByCustomerContactNumberAndCustomerActiveStatus(customerContactNo,true);
           customer.setCustomerActiveStatus(false);
           customerRepo.save(customer);
           return "Customer with "+customerContactNo + " contact no is unregistered Successfully";
       }
       else {
           return "Contact no does not exist";
       }
    }

    @Override
    public String updateCustomer(UpdateCustomerDTO updateCustomerDTO) {
        if (customerRepo.existsByCustomerGeneratedIdAndCustomerActiveStatus(updateCustomerDTO.getCustomerGeneratedId(), true )){
            Customer customer = customerRepo.getByCustomerGeneratedIdAndCustomerActiveStatus(updateCustomerDTO.getCustomerGeneratedId(),true);
            customer.setCustomerName(updateCustomerDTO.getCustomerName());
            customer.setCustomerAddress(updateCustomerDTO.getCustomerAddress());
            customer.setCustomerContactNumber(updateCustomerDTO.getCustomerContactNumber());
            customerRepo.save(customer);
            return updateCustomerDTO.getCustomerGeneratedId()+" is successfully updated";
        }
        else {
            return "ID does not exist";
        }
    }

}
