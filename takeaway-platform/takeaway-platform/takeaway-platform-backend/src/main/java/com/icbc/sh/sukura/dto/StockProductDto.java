package com.icbc.sh.sukura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockProductDto {
    private Date startDate;
    private String productId;
    private String productName;
    private String pType;
    private int stockNum;
}
