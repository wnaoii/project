package com.icbc.sh.sukura.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_mix")
@Entity
public class StockMixEntity {
    @Id
    private String mixId;
    private String mixTitle;
    private String createTime;
    private int stock;
}
