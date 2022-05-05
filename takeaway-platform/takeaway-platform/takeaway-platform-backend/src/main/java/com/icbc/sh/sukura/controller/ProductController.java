package com.icbc.sh.sukura.controller;

import com.icbc.sh.sukura.bo.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {


    @PostMapping("/")
    public Product insertProduct(
            @RequestBody Product product
    ) {
        return product;
    }
}
