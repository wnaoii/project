package com.icbc.sh.sukura.service.ipml;

import com.icbc.sh.sukura.bo.ADBo;
import com.icbc.sh.sukura.entity.ADEntity;
import com.icbc.sh.sukura.entity.AddressEntity;
import com.icbc.sh.sukura.repository.ADRepository;
import com.icbc.sh.sukura.service.ADService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ADServiceImpl implements ADService {
    @Autowired
    private ADRepository adRepository;

    @Override
    public String insertAd(String adName, String userId, MultipartFile image) {
        String adId = "A-"+ UUID.randomUUID().toString();
        byte[] imageBody = null;
        try {
            imageBody = image.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("广告图片存储时发生错误");
        }
        ADEntity adEntity = ADEntity.builder()
                .adId(adId)
                .adName(adName)
                .createDate(new Date())
                .deleted("1")
                .userId(userId)
                .image(imageBody)
                .build();
        adRepository.save(adEntity);
        return adId;
    }

    @Override
    public List<ADEntity> getList() {
        List<ADEntity> adEntities = adRepository.findAll(new Sort(Sort.Direction.DESC, "seq"));
        List<ADBo> adBos = new ArrayList<>();
     /*   adEntities.forEach(v -> {

        });*/
        return adEntities;
    }
}
