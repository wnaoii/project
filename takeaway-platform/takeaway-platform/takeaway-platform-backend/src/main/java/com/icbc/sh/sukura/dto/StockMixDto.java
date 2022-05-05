package com.icbc.sh.sukura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockMixDto {
    private String mixId;
    private String mixName;
    private String stockNum;
}
