package com.icbc.sh.sukura.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
@Entity
public class UserEntity {
    @Column
    @Id
    private String userId;
    @Column
    private String userName;
    @Column
    private String phone;
    @Column
    private String addressId;
    @Column
    private String avarId;
}
