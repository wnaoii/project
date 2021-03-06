package com.icbc.sh.sukura.service.ipml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.icbc.sh.sukura.bo.ApiTransferResponse;
import com.icbc.sh.sukura.bo.Prices;
import com.icbc.sh.sukura.dto.OrderDto;
import com.icbc.sh.sukura.entity.*;
import com.icbc.sh.sukura.exceptions.CommonException;
import com.icbc.sh.sukura.exceptions.SHApiCallException;
import com.icbc.sh.sukura.repository.*;
//import com.icbc.sh.sukura.service.ApiService;
import com.icbc.sh.sukura.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private StockMixRepository stockMixRepository;
    @Autowired
    private StockProductRepository stockProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PieMapper pieMapper;
//    @Autowired
//    private ApiService apiService;
    @Autowired
    private SystemParamsRepository systemParamsRepository;


    @Transactional
    @Override
    public void insertOrder(OrderDto orderDto) {
        String mixList = "";
        String address = "";

        if (orderDto.getAddress().size() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                address = objectMapper.writeValueAsString(orderDto.getAddress());
                if (orderDto.getMixList().size() > 0) {
                    mixList = objectMapper.writeValueAsString(orderDto.getMixList());
                }
            } catch (JsonProcessingException e) {
                logger.error("???????????????????????????{}", e);
                throw new RuntimeException("????????????");
            }
        }
        // ?????? - 1
        for (String mixId : orderDto.getMixList()) {
            StockMixEntity stockMixEntity = stockMixRepository.getOne(mixId);
            if(null == stockMixEntity) {
                throw new CommonException("????????????????????????");
            }
            if(stockMixEntity.getStock() <= 0) {
                throw new CommonException(stockMixEntity.getMixTitle() + "????????????");
            }
            stockMixEntity.setStock(stockMixEntity.getStock() - 1);
            stockMixRepository.save(stockMixEntity);
        }
        StockProductEntity stockProductEntity = stockProductRepository.findOne(orderDto.getProductId());
        if(null == stockProductEntity) {
            throw new CommonException("????????????????????????");
        }
        if(stockProductEntity.getStock() <= 0) {
            throw new CommonException(stockProductEntity.getPTitle() + "????????????????????????????????????");
        }
        stockProductEntity.setStock(stockProductEntity.getStock() - 1);
        stockProductRepository.save(stockProductEntity);

        ProductEntity productEntity = productRepository.getOne(orderDto.getProductId());

        // ????????????
        BigDecimal totalPrice = new BigDecimal(productEntity.getPrice());
        totalPrice = totalPrice.multiply(new BigDecimal(orderDto.getNum()));

        if ("0011".equals(orderDto.getAddress().get(1))) {
            totalPrice = totalPrice.add(new BigDecimal("5"));
        } else {
            totalPrice = totalPrice.add(new BigDecimal("10"));
        }
        BigDecimal mixPrice = new BigDecimal("1.5").multiply(new BigDecimal(orderDto.getMixList().size()));

        totalPrice = totalPrice.add(mixPrice);

        UserEntity userEntity = userRepository.getOne(orderDto.getUserId());
        SystemParamsEntity systemParamsEntity = systemParamsRepository.getOne(1);

        //??????????????????

//        ApiTransferResponse response = apiService.callApiTransfer(systemParamsEntity.getCardNo(), userEntity.getCardNo(), "2020-08-01", totalPrice.toString());
        if("0".equals("0")) {
            OrderEntity orderEntity = OrderEntity.builder()
                    .orderId(orderDto.getOrderId())
                    .mixList(mixList)
                    .price(totalPrice.toString())
                    .productId(orderDto.getProductId())
                    .userId(orderDto.getUserId())
                    .userName(userEntity.getUserName())
                    .phone(userEntity.getPhone())
                    .address(address)
                    .addressLabel(orderDto.getAddressLabel())
                    .mixLabel(orderDto.getMixLabel())
                    .num(String.valueOf(orderDto.getNum()))
                    .createDate(new Date())
                    .ordersStatus("01")
                    .build();
            orderRepository.save(orderEntity);
        } else {
            logger.error("????????????");
            throw new SHApiCallException("??????????????????????????????");
        }

    }

    @Override
    public List<OrderEntity> findAllByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<OrderEntity> findByOrdersStatus(String ordersStatus) {
        return orderRepository.findAllByOrdersStatusOrderByCreateDate(ordersStatus);
    }

    @Override
    public List<OrderEntity> findAll(String ordersStatus, Pageable pageable) {
        Page page =orderRepository.findAll(new Specification<OrderEntity>() {
            List<Predicate> predicates = new ArrayList<>();
            @Override
            public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if (!StringUtils.isEmpty(ordersStatus)) {
                    predicates.add(criteriaBuilder.equal(root.get("ordersStatus"), ordersStatus));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
        return page.getContent();
    }

    @Override
    public List<Prices> getSoldPrice() {
        List<Prices> prices = pieMapper.getSoldPrice();
        prices.forEach(v -> {
            v.setProductName(productRepository.getOne(v.getProductId()).getPTitle());
        });
        return prices;
    }


}
