package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.bo.Prices;
import com.icbc.sh.sukura.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepository extends JpaRepository<OrderEntity, String>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findByUserId(String userId);

    List<OrderEntity> findAllByOrdersStatusOrderByCreateDate(String ordersStatus);

    @Query(value = "SELECT sum(t.price) as price, t.product_id as productId FROM t_order t where t.orders_status = '01' GROUP BY t.product_id",nativeQuery = true)
    List<Prices> getSoldPrice();


}
