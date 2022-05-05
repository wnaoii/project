package com.icbc.sh.sukura.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersOADto {
    private String orderId;
    private List<String> mixList = new ArrayList<>();
    private String mixLabel;
    private String productId;
    private String userId;
    private String productName;
    private int num;
    private List<String> address;
    private String addressLabel;
    private Date createDate;
    private String orderStatus;
    private String phone;
    private String price;
    private String userName;
}
