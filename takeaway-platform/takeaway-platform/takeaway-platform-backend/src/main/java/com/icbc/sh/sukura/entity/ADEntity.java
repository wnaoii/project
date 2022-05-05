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
@Table(name = "t_ad")
@Entity
public class ADEntity {
    @Id
    private String adId;
    private String adName;
    private int seq;
    private String userId;
    private String deleted;
    @Column(columnDefinition = "BLOB")
    private byte[] image;
    private Date createDate;
}
