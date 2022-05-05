package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.entity.OrderEntity;
import com.icbc.sh.sukura.entity.StockMixEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StockMixRepository extends JpaRepository<StockMixEntity, String>, JpaSpecificationExecutor<StockMixEntity> {
}
