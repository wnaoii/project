package com.icbc.sh.sukura.service;


import com.icbc.sh.sukura.bo.ImageBo;
import com.icbc.sh.sukura.entity.ImageEntity;

public interface ImageService {

    ImageEntity getImageByImageId(String imageId);

    ImageEntity insertImage(ImageBo imageBo);
}
