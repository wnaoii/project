package com.icbc.sh.sukura.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ADBo {
    private String adName;
    private String adId;
    private String createDate;
    private String userId;
    private String userName;
    private String deleted;
}
