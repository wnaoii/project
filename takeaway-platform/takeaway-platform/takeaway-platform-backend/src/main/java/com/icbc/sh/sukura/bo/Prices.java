package com.icbc.sh.sukura.bo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prices {
    private String productId;
    private String productName;
    private String price;
}
