package com.icbc.sh.sukura.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    @JsonProperty("pTitle")
    private String pTitle;
    @JsonProperty("pDesc")
    private String pDesc;
    private String price;
    @JsonProperty("pType")
    private String pType;
    private Date createTime;
    private int hot;
    private int addable;
    private String addableList;
    private String method;
    private Date startDate;
    @JsonProperty("imageId")
    private String imageId;
    @JsonProperty("contacts")
    private String contacts;
    @JsonProperty("contactsNo")
    private String contactsNo;
    private String soldStatus;
    private int stockNum;
}
