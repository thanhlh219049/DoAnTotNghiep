package com.lethanh219049.application.service;

import com.lethanh219049.application.model.dto.OrderDetailDTO;
import com.lethanh219049.application.model.dto.OrderInfoDTO;
import com.lethanh219049.application.entity.Order;
import com.lethanh219049.application.model.request.CreateOrderRequest;
import com.lethanh219049.application.model.request.UpdateDetailOrder;
import com.lethanh219049.application.model.request.UpdateStatusOrderRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Page<Order> adminGetListOrders(String id, String name, String phone, String status, String product, int page);

    Order createOrder(CreateOrderRequest createOrderRequest, long userId);

    void updateDetailOrder(UpdateDetailOrder updateDetailOrder, long id, long userId);

    Order findOrderById(long id);
    List<Order> findOrderByUserId(List<Long> orderIds);

    void updateStatusOrder(UpdateStatusOrderRequest updateStatusOrderRequest, long orderId, long userId);

    List<OrderInfoDTO> getListOrderOfPersonByStatus(int status, long userId);

    OrderDetailDTO userGetDetailById(long id, long userId);

    void userCancelOrder(long id, long userId);

    //Đếm số lượng đơn hàng
    long getCountOrder();

    void saveAll(List<Order> orders);

}
