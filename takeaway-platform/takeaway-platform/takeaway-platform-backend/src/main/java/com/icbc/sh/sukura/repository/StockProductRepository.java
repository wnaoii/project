package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.entity.StockMixEntity;
import com.icbc.sh.sukura.entity.StockProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StockProductRepository extends JpaRepository<StockProductEntity, String>, JpaSpecificationExecutor<StockProductEntity> {
}
