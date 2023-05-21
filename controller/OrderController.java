package com.ordermanagement.OM.controller;

import com.ordermanagement.OM.dto.request.OrderDTO;
import com.ordermanagement.OM.other.responseEntity.StandardResponse;
import com.ordermanagement.OM.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/save-order-details")
    public ResponseEntity<StandardResponse> addOrder(@RequestBody OrderDTO orderDTO) {
        String message = orderService.addOrderDetails(orderDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(204, "Successful", message), HttpStatus.CREATED);
    }

}
