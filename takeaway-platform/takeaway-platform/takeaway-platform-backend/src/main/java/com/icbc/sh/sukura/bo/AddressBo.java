package com.icbc.sh.sukura.bo;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressBo {
    private String addressId;
    private String province;
    private String city;
    private String district;
    private String street;
    private String userId;
    private String label;
}
