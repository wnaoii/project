package com.icbc.sh.sukura.service;

import com.icbc.sh.sukura.bo.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DrinkService {

    List<Product> getProductList(String pType, Pageable pageable);

    Product insertProduct(Product product);

    Product getProduct(String productId);

    void deleteProduct(String productId);
}
