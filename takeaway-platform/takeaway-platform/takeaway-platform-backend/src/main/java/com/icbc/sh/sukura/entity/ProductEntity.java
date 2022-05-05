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
@Table(name = "product")
@Entity
public class ProductEntity {
    @Id
    @Column
    private String productId;
    @Column
    private String pTitle;
    @Column
    private String pDesc;
    @Column
    private String price;
    @Column
    private String pType;
    @Column
    private Date createTime;
    private int hot;
    private int addable;
    private String addableList;
    private String method;
    private Date startDate;
    private String imageId;
    private String contacts;
    private String contactsNo;
    private String soldStatus;
    private String deleted;
}
