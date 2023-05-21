package com.ordermanagement.OM.service;

import com.ordermanagement.OM.dto.request.OrderDTO;

public interface OrderService {
    String addOrderDetails(OrderDTO orderDTO);
}
