package com.ordermanagement.OM.service.impl;

import com.ordermanagement.OM.dto.request.OrderDTO;
import com.ordermanagement.OM.dto.request.PurchaseItems;
import com.ordermanagement.OM.entity.Customer;
import com.ordermanagement.OM.entity.Items;
import com.ordermanagement.OM.entity.Order;
import com.ordermanagement.OM.entity.OrderDetails;
import com.ordermanagement.OM.repo.CustomerRepo;
import com.ordermanagement.OM.repo.ItemRepo;
import com.ordermanagement.OM.repo.OrderDetailsRepo;
import com.ordermanagement.OM.repo.OrderRepo;
import com.ordermanagement.OM.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    @Transactional
    public String addOrderDetails(OrderDTO orderDTO) {
        if (orderDTO.isARegisteredCustomer()&(orderDTO.getCustomerRegPhoneNo().isEmpty())){
            return "Please Enter the Customer registered Phone no" ;
        }
        else if ((orderDTO.isARegisteredCustomer() && customerRepo.existsByCustomerContactNumberAndCustomerActiveStatus(orderDTO.getCustomerRegPhoneNo(),true)) || !orderDTO.isARegisteredCustomer() ) {
            Customer customer;
            String customerGeneratedId = null;
            if (orderDTO.isARegisteredCustomer()==false){
                customer =null;
            }
            else{
                customer =customerRepo.getByCustomerContactNumberAndCustomerActiveStatus(orderDTO.getCustomerRegPhoneNo(), true);
                customerGeneratedId = customer.getCustomerGeneratedId();
            }

            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year  = localDate.getYear();
            String orderCode = "#ODR"+String.valueOf(year).substring(2)+String.valueOf(orderRepo.count()+1) ;
            LocalDate orderDate = LocalDate.now();
            LocalTime orderTime = LocalTime.now();
            float totalPurchase = 0;
            for (PurchaseItems purchaseItems: orderDTO.getPurchaseItems()){
                Items items =itemRepo.getByItemGeneratedCodeAndItemActiveStatus(purchaseItems.
                        getItemGeneratedCode(),true);
                totalPurchase += (items.getSellingPrice())*(purchaseItems.getQuantity());
                }
            Order order = new Order(
                 orderCode,orderDTO.isARegisteredCustomer(),customerGeneratedId
                    ,totalPurchase,String.valueOf(orderDate),String.valueOf(orderTime).substring(0,8)
            );
            orderRepo.save(order);

            for (PurchaseItems purchaseItems: orderDTO.getPurchaseItems()){
                Items items =itemRepo.getByItemGeneratedCodeAndItemActiveStatus(purchaseItems.getItemGeneratedCode(),true);
                OrderDetails orderDetails = new OrderDetails(
                        1,orderRepo.getByOrderGeneratedCode(orderCode),purchaseItems.getItemGeneratedCode(),
                        items.getSellingPrice(),purchaseItems.getQuantity()
                );
                orderDetailsRepo.save(orderDetails);
            }

           if (customerRepo.existsByCustomerContactNumberAndCustomerActiveStatus(orderDTO.getCustomerRegPhoneNo(),true)){
               ArrayList inputArray =new ArrayList();
               inputArray.add(orderCode); inputArray.add(totalPurchase);

               ArrayList<ArrayList<Object>> pastPurchases = customer.getPastPurchases();
               pastPurchases.add(inputArray);
               customer.setPastPurchases(pastPurchases);
               customerRepo.save(customer);
           }
           return "Order no " + orderCode + " was saved successfully" ;
        }

        else{
            return "Contact no you entered did not Exist";
        }
    }

}
