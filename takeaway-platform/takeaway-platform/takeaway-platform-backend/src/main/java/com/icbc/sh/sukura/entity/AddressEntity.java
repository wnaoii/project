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
@Table(name = "address")
@Entity
public class AddressEntity {
    @Id
    private String addressId;
    private String province;
    private String city;
    private String district;
    private String street;
    private String userId;
    private String label;
}
