package com.icbc.sh.sukura.bo;

import lombok.*;

import java.io.Serializable;

@Data
@ToString(exclude = {"ImageBo"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageBo implements Serializable {
    private String imageId;
    private String imageName;
    private String imageFileName;
    private long imageSize;
    private byte[] imageBody;
    private String unionId;
    private String imageType;
    private String imageClassify;
}
