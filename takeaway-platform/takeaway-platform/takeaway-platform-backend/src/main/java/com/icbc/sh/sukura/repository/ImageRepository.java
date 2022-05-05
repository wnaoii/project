package com.icbc.sh.sukura.repository;

import com.icbc.sh.sukura.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<ImageEntity, String>, JpaSpecificationExecutor<ImageEntity> {

    ImageEntity findByImageId(String imageId);
}
