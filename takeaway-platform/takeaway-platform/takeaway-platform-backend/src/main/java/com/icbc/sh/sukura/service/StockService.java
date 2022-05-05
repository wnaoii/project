package com.icbc.sh.sukura.service;

import com.icbc.sh.sukura.entity.StockMixEntity;
import com.icbc.sh.sukura.entity.StockProductEntity;

import java.util.List;

public interface StockService {

    List<StockProductEntity> getStockProduct();

    void updatePStock(String productId, int stockNum);

    List<StockMixEntity> getStockMix();

    void updateMStock(String mixId, int stockNum);
}
