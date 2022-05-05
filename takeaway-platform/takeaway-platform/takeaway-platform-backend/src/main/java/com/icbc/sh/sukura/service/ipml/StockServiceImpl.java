package com.icbc.sh.sukura.service.ipml;

import com.icbc.sh.sukura.entity.StockMixEntity;
import com.icbc.sh.sukura.entity.StockProductEntity;
import com.icbc.sh.sukura.repository.StockMixRepository;
import com.icbc.sh.sukura.repository.StockProductRepository;
import com.icbc.sh.sukura.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockProductRepository stockProductRepository;
    @Autowired
    private StockMixRepository stockMixRepository;

    @Override
    public List<StockProductEntity> getStockProduct() {
        return stockProductRepository.findAll();
    }

    @Override
    public void updatePStock(String productId, int stockNum) {
        StockProductEntity stockProductEntity = stockProductRepository.getOne(productId);
        stockProductEntity.setStock(stockNum);
        stockProductRepository.save(stockProductEntity);
    }

    @Override
    public List<StockMixEntity> getStockMix() {
        return stockMixRepository.findAll();
    }

    @Override
    public void updateMStock(String mixId, int stockNum) {
        StockMixEntity stockMixEntity = stockMixRepository.findOne(mixId);
        stockMixEntity.setStock(stockNum);
        stockMixRepository.save(stockMixEntity);
    }
}
