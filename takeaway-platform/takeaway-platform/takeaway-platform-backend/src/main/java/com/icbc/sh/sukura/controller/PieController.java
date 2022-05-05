package com.icbc.sh.sukura.controller;


import com.icbc.sh.sukura.bo.Prices;
import com.icbc.sh.sukura.dto.SoldPieDto;
import com.icbc.sh.sukura.repository.OrderRepository;
import com.icbc.sh.sukura.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/charts")
public class PieController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/pie/sold")
    public List<SoldPieDto> getSoldPieData() {
        List<Prices> prices = orderService.getSoldPrice();
        List<SoldPieDto> soldPieDtos = new ArrayList<>();
        prices.forEach(v -> {
            SoldPieDto soldPieDto = SoldPieDto.builder()
                    .name(v.getProductName())
                    .value(String.valueOf(v.getPrice()))
                    .build();
            soldPieDtos.add(soldPieDto);
        });
        return soldPieDtos;
    }

    @GetMapping("/test")
    public Object ff () {
        return orderRepository.getSoldPrice();
    }
}
