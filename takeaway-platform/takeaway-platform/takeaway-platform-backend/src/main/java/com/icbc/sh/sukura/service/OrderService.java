package com.icbc.sh.sukura.service;

import com.icbc.sh.sukura.bo.Prices;
import com.icbc.sh.sukura.dto.OrderDto;
import com.icbc.sh.sukura.entity.OrderEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface OrderService {

    void insertOrder(OrderDto orderDto);

    List<OrderEntity> findAllByUserId(String userId);

    List<OrderEntity> findByOrdersStatus(String ordersStatus);

    List<OrderEntity> findAll(String ordersStatus, Pageable pageable);

    List<Prices> getSoldPrice();


}
