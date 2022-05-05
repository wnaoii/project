package com.icbc.sh.sukura.service;

import com.icbc.sh.sukura.entity.ADEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ADService {

    String insertAd(String adName, String userId, MultipartFile image);

    List<ADEntity> getList();
}
