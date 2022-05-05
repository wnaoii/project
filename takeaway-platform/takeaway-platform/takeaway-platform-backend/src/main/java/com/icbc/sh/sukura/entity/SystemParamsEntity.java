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
@Table(name = "t_sys_params")
@Entity
public class SystemParamsEntity {
    private String systemTitle;
    private String cardNo;
    @Id
    private int sid;
}
