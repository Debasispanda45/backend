package com.tastytown.backend.service;

import java.util.List;

import com.tastytown.backend.constants.OrderStatus;
import com.tastytown.backend.dto.BillingInfoDTO;
import com.tastytown.backend.dto.OrderDTO;

public interface IOrderService {
    OrderDTO placeOrder(BillingInfoDTO dto, String userId);

    List<OrderDTO> getOrdersByUser(String userid);

    List<OrderDTO> getAllOrders();

    OrderDTO updateOrderStatus(String orderCode, OrderStatus status);
}
