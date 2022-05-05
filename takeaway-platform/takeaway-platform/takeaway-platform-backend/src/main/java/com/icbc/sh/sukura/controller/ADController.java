package com.icbc.sh.sukura.controller;


import com.icbc.sh.sukura.bo.ADBo;
import com.icbc.sh.sukura.entity.ADEntity;
import com.icbc.sh.sukura.service.ADService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ad")
public class ADController {
    @Autowired
    private ADService adService;

    @PostMapping("/")
    public String insertAD(
            @RequestParam(value = "adName") String adName,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "adIcon") MultipartFile adIcon
    ) {
        return adService.insertAd(adName, userId, adIcon);
    }

    @GetMapping("/list")
    public List<ADEntity> getList() {
        return adService.getList();
    }
}
