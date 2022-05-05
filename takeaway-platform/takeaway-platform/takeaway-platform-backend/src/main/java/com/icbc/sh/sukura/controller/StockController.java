package com.icbc.sh.sukura.controller;

import com.icbc.sh.sukura.bo.Product;
import com.icbc.sh.sukura.dto.OrderDto;
import com.icbc.sh.sukura.dto.StockMixDto;
import com.icbc.sh.sukura.dto.StockProductDto;
import com.icbc.sh.sukura.entity.OrderEntity;
import com.icbc.sh.sukura.entity.ProductEntity;
import com.icbc.sh.sukura.entity.StockMixEntity;
import com.icbc.sh.sukura.entity.StockProductEntity;
import com.icbc.sh.sukura.service.DrinkService;
import com.icbc.sh.sukura.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/stock")
public class StockController {
    @Autowired
    private StockService stockService;
    @Autowired
    private DrinkService drinkService;

    @GetMapping("/product")
    public List<StockProductDto> getProductStockList() {
        List<StockProductEntity> productEntities = stockService.getStockProduct();
        List<StockProductDto> dtos = new ArrayList<>();
        productEntities.forEach( v -> {
            Product product = drinkService.getProduct(v.getProductId());
            StockProductDto dto = StockProductDto.builder()
                    .productId(product.getProductId())
                    .productName(product.getPTitle())
                    .pType(product.getPType())
                    .startDate(product.getStartDate())
                    .stockNum(v.getStock())
                    .build();
            dtos.add(dto);
        });
        return dtos;
    }

    @GetMapping("/mix")
    public List<StockMixEntity> getMixStockList() {
        return stockService.getStockMix();
    }

    @PutMapping("/product")
    public void updateProductStock(
            @RequestParam("productId") String productId,
            @RequestParam("stockNum") int stockNum
    ) {
        stockService.updatePStock(productId, stockNum);
    }

    @PutMapping("/mix")
    public void updateMixStock(
            @RequestParam("mixId") String mixId,
            @RequestParam("stockNum") int stockNum
    ) {
        stockService.updateMStock(mixId, stockNum);
    }
}
