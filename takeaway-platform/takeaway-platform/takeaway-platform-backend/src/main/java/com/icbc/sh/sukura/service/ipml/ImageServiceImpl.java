package com.icbc.sh.sukura.service.ipml;

import com.icbc.sh.sukura.bo.ImageBo;
import com.icbc.sh.sukura.entity.ImageEntity;
import com.icbc.sh.sukura.repository.ImageRepository;
import com.icbc.sh.sukura.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public ImageEntity getImageByImageId(String imageId) {
        ImageEntity entity = imageRepository.findByImageId(imageId);
        return entity;
    }

    @Override
    public ImageEntity insertImage(ImageBo imageBo) {
        return imageRepository.save(this.bo2Entity(imageBo));
    }

    private ImageEntity bo2Entity(ImageBo imageBo) {
        return ImageEntity.builder()
                .imageBody(imageBo.getImageBody())
                .imageFileName(imageBo.getImageFileName())
                .imageId(imageBo.getImageId())
                .imageName(imageBo.getImageName())
                .imageSize(imageBo.getImageSize())
                .imageType(imageBo.getImageType())
                .build();
    }
}
