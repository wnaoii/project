package com.icbc.sh.sukura.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_order")
@Entity
public class OrderEntity {
    @Id
    private String orderId;
    private String productId;
    private String userName;
    private String userId;
    private String mixList;
    private String price;
    private String address;
    private String addressLabel;
    private String phone;
    private Date createDate;
    private String ordersStatus;
    private String num;
    private String mixLabel;
}
