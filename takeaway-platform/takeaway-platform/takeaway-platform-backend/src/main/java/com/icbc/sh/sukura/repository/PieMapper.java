package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.bo.Prices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PieMapper {

    @Select("SELECT sum(t.price) as price, t.product_id as productId FROM t_order t where t.orders_status = '01' GROUP BY t.product_id")
    List<Prices> getSoldPrice();
}
