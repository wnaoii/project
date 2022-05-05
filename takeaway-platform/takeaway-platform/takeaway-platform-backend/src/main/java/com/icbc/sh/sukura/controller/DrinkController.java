package com.icbc.sh.sukura.controller;


import com.icbc.sh.sukura.bo.Product;
import com.icbc.sh.sukura.service.DrinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static java.lang.Thread.sleep;

@Slf4j
@RestController
@RequestMapping("/drink")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;

    @GetMapping("/product/list")
    public Object getAllProduct(
            @RequestParam(value = "pType", required = false) String pType
    ) {
        Integer page = 0;
        Integer size = 20;
        PageRequest pageRequest = new PageRequest(page,size);
        return drinkService.getProductList(pType, pageRequest);
    }

    @PostMapping("/")
    public Product insertProduct(
            @RequestBody Product product
    ) {
        drinkService.insertProduct(product);
        return product;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(
            @PathVariable("id") String productId
    ) {
        return drinkService.getProduct(productId);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(
            @PathVariable("id")  String productId
    ) {
        drinkService.deleteProduct(productId);
    }
}
