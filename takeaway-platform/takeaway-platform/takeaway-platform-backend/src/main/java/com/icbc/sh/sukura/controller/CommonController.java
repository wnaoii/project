//package com.icbc.sh.sukura.controller;
//
//import com.alibaba.fastjson.JSONObject;
////import com.icbc.sh.sukura.bo.ApiTransferResponse;
//import com.icbc.sh.sukura.exceptions.SHApiCallException;
////import com.icbc.sh.sukura.service.ipml.ApiServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
////import sh.api.client.ShApiClient;
////import sh.api.client.ShapiClientConfig;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@RestController
//@RequestMapping("/common")
//public class CommonController {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
////    @Autowired
////    private ApiServiceImpl apiService;
//
//    @GetMapping("/product/list/{imageId}")
//    public Object getImage(
//            @PathVariable("imageId") String imageId
//    ) {
//        return null;
//    }
//
//    @PostMapping(value = "/")
//    public String  testApi(
//            @RequestParam("api") String api,
//            @RequestBody String reqJson
//    ) {
//
//        String urihqapi = api;
//        ShapiClientConfig scc = new ShapiClientConfig();
//        String enckey = "DB01BC234DC35B5DDCBDCCD55316EDE97E9BAADDA64819CDAC90C6BD2DA35E37";
//        scc.setENCKEY(enckey);
//        scc.setSHAPPID("shapp.zmexam.backend");
//        scc.setIcbchqAppid("00000000000000000002");
//        String apiAddress = "http://107.6.61.44:9080/shapi";
//        scc.setBASEURL(apiAddress);
//        String uuid = UUID.randomUUID().toString();
//
//        String msg = "";
//
//        String json = reqJson.replace('\n', ' ').replace('\r', ' ');
//
//        logger.info("json {}", json);
//
//        ShApiClient client = new ShApiClient(scc);
//        try {
//            client.doPost(urihqapi, json, uuid, "");
//        } catch (Exception e) {
//            logger.error("1v1转账门户调用失败");
//            throw new SHApiCallException("1v1转账门户调用失败");
//        }
//        logger.info("返回：SYSTEM_RET = [{}], SYSTEM_MSG = [{}], RESPONSE = [{}]", client.getSysReturnCode(), client.getSysRetMString(), client.getResponse());
//
//        msg = client.getResponse();
//        JSONObject jsonObject = JSONObject.parseObject(client.getResponse());
//
//        return  msg;
//    }
//
////    @GetMapping("/api")
////    public ApiTransferResponse callApi(
////            @RequestParam("drmdCardNo") String drmdCardNo,
////            @RequestParam("crmdCardNo") String crmdCardNo,
////            @RequestParam("reqDate") String reqDate,
////            @RequestParam("amount") String amount
////    ) {
////        return apiService.callApiTransfer(drmdCardNo, crmdCardNo, reqDate, amount);
////    }
//
//}
