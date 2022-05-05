package com.icbc.sh.sukura.controller;


import com.icbc.sh.sukura.bo.ImageBo;
import com.icbc.sh.sukura.entity.ImageEntity;
import com.icbc.sh.sukura.repository.ImageRepository;
import com.icbc.sh.sukura.service.ImageService;
import com.sun.prism.Image;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/image")
public class ImageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]>  getImage(
            @PathVariable("id") String imageId
    ) {

        ImageEntity entity = imageRepository.findByImageId(imageId);
        String contentType = entity.getImageType();
        MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
        if(!StringUtils.isEmpty(contentType)){
            if(contentType.equals("image/png")){
                mediaType = MediaType.IMAGE_PNG;
            }else if(contentType.equals("image/jpeg")){
                mediaType = MediaType.IMAGE_JPEG;
            }else if(contentType.equals("image/gif")) {
                mediaType = MediaType.IMAGE_GIF;
            }
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .contentType(mediaType)
                .body(entity.getImageBody());
    }

    @PostMapping(value = "/")
    public Map addProduct(
            @RequestParam(value = "unionId") String unionId,
            @RequestParam(value = "imageId") String imageId,
            @RequestParam(value = "imageClassify", required = false, defaultValue = "") String imageClassify,
            @RequestParam(value = "btnIcon") MultipartFile btnIcon
    ) {
        Map<String, String> map = new HashMap();
        if (btnIcon != null && !btnIcon.isEmpty()) {
            try {
                logger.info("name => {}, size => {}, filenmae => {}, contentType => {}", btnIcon.getName(), btnIcon.getSize(),btnIcon.getOriginalFilename(), btnIcon.getContentType());
                byte [] iconByte = btnIcon.getBytes();
                ImageBo imageBo = ImageBo.builder()
                        .imageId(imageId)
                        .unionId(unionId)
                        .imageClassify(imageClassify)
                        .imageBody(iconByte)
                        .imageName(btnIcon.getName())
                        .imageFileName(btnIcon.getOriginalFilename())
                        .imageSize(btnIcon.getSize())
                        .imageType(btnIcon.getContentType())
                        .build();
                imageService.insertImage(imageBo);
            } catch (IOException e) {
                logger.error("e: {}", e);
            }
        }

        map.put("resCode", "0");
        map.put("resMsg", "");
        return map;
    }
}
