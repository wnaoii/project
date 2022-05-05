package com.icbc.sh.sukura.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.icbc.sh.sukura.dto.OrderDto;
import com.icbc.sh.sukura.dto.OrdersOADto;
import com.icbc.sh.sukura.entity.OrderEntity;
import com.icbc.sh.sukura.entity.ProductEntity;
import com.icbc.sh.sukura.entity.StockMixEntity;
import com.icbc.sh.sukura.repository.ProductRepository;
import com.icbc.sh.sukura.repository.StockMixRepository;
import com.icbc.sh.sukura.repository.UserRepository;
import com.icbc.sh.sukura.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/")
    public OrderDto createOrder(
            @RequestBody OrderDto orderDto,
            HttpSession session
    ) {
        String ordersId = "O-" + session.getId();
        orderDto.setOrderId(ordersId);
        orderService.insertOrder(orderDto);
        return orderDto;
    }

    @GetMapping("/list")
    public List<OrderDto> getListOrders(String userId) {
        List<OrderEntity> orderEntities = orderService.findAllByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (OrderEntity entity: orderEntities) {
            String productName = productRepository.getOne(entity.getProductId()).getPTitle();
            OrderDto dto = OrderDto.builder()
                    .mixLabel(entity.getMixLabel())
                    .addressLabel(entity.getAddressLabel())
                    .num(Integer.parseInt(entity.getNum()))
                    .orderId(entity.getOrderId())
                    .productName(productName)
                    .createDate(entity.getCreateDate())
                    .orderStatus(entity.getOrdersStatus())
                    .build();
            orderDtos.add(dto);
        }
        return orderDtos;
    }

    @GetMapping("/oa/list")
    public List<OrdersOADto> getOAOrdersList(
            @RequestParam(value = "ordersStatus", required = false) String ordersStatus
    ) {
        Integer page = 0;
        Integer size = 20;
        PageRequest pageRequest = new PageRequest(page,size);
        List<OrdersOADto> dtoList = new ArrayList<>();
        orderService.findAll(ordersStatus, pageRequest).forEach(orderEntity -> {
            String productName = productRepository.getOne(orderEntity.getProductId()).getPTitle();
            OrdersOADto orderDto = OrdersOADto.builder()
                    .orderId(orderEntity.getOrderId())
                    .orderStatus(orderEntity.getOrdersStatus())
                    .addressLabel(orderEntity.getAddressLabel())
                    .createDate(orderEntity.getCreateDate())
                    .mixLabel(orderEntity.getMixLabel())
                    .num(Integer.parseInt(orderEntity.getNum()))
                    .phone(orderEntity.getPhone())
                    .productName(productName)
                    .price(orderEntity.getPrice())
                    .userName(orderEntity.getUserName())
                    .userId(orderEntity.getUserId())
                    .build();
            dtoList.add(orderDto);
        });
        return dtoList;
    }
}
