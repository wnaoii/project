//package com.icbc.sh.sukura.service.ipml;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//
//import com.google.gson.Gson;
//import com.icbc.sh.sukura.bo.ApiTransferRequest;
//import com.icbc.sh.sukura.bo.ApiTransferResponse;
//import com.icbc.sh.sukura.exceptions.SHApiCallException;
//import com.icbc.sh.sukura.service.ApiService;
//import com.icbc.sh.sukura.utils.ApiComTools;
//import lombok.extern.slf4j.Slf4j;
//
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import sh.api.client.ShApiClient;
//import sh.api.client.ShapiClientConfig;
//import sh.api.client.log.ShApiClientLoggerUtil;
//import sh.icbc.oss.client.cfg.ShOssConfigBean;
//import sh.icbc.oss.client.memory.ShOssDownloadStream;
//import sh.icbc.oss.client.util.gzip.GzipUtil;
//import test.TestRsaKey;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.UUID;
//
//import static test.memory.StreamDownloadTest.serverPublicKey;
//
//
//@Slf4j
//@Service
//public class ApiServiceImpl implements ApiService {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private ShOssConfigBean ossConfig = new ShOssConfigBean();
//    private ShapiClientConfig apiConfig = new ShapiClientConfig();
//    String localFile = "d:/tmp/middle.parallel.zip";
//    String objectName ="parallel.middle.test.zip";
//
//    public ApiTransferResponse callApiTransfer(
//            String drmdCardNo,
//            String crmdCardNo,
//            String reqDate,
//            String amount
//    ) {
//        String urihqapi = "/icbchq/csi/customer/account/V1/transfer";
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
//        String jsonStrTemplate = "";
//        try {
//            jsonStrTemplate = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("request_template/api_req_template.json"), "UTF-8").replace('\n', ' ');
////            jsonStrTemplate = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("request_template/1v1_transfer_v2.json"), "UTF-8").replace('\n', ' ');
//        } catch (IOException e) {
//            logger.error("repurchaseReq.json 读取失败！{}", e);
//        }
//
//        String json = jsonStrTemplate.replace('\n', ' ').replace('\r', ' ');
//        logger.info("json {}", json);
//        Gson gson = new Gson();
//        ApiTransferRequest repurchaseRequest = gson.fromJson(jsonStrTemplate, ApiTransferRequest.class);
//        String serialNo = "9404100101" + RandomStringUtils.random(16, false, true);
//        repurchaseRequest.getChannel().setChan_serialno(serialNo);
//        repurchaseRequest.getCommcitr().setCrmdcardno(crmdCardNo);
//        repurchaseRequest.getCommcitr().setDrmdcardno(drmdCardNo);
//        repurchaseRequest.getCommcitr().setAmount(amount);
//        repurchaseRequest.getCommcitr().setReq_date(reqDate);
//
//        json = gson.toJson(repurchaseRequest);
//        logger.info("1v1转账上送参数: {}", json);
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
//
//        ApiTransferResponse apiTransferResponse = gson.fromJson(msg, ApiTransferResponse.class);
//
//        return  apiTransferResponse;
//    }
//
//    @Override
//    public String ossDownload() {
//
//        ShApiClientLoggerUtil.SET_LOGGER(logger);
//
//        ossConfig.setBucket("shbucket.lich.first");
//        ossConfig.setClientAppId("shapp.shoss.fromlich");
//        String readToken = null;
//        try {
//            readToken = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("request_template/api_req_template.json"), "UTF-8");
//        } catch (IOException e) {
//            logger.error("oss 读取token文件出错: {}", e);
//            throw new RuntimeException("oss 报错");
//        }
//        ossConfig.setToken(readToken);
//        apiConfig.setVersion(ShapiClientConfig.COMMVERSION_AES256);
//        apiConfig.setSHAPPID("shapp.zmexam.backend");
//        apiConfig.setBASEURL("http://107.6.61.44:9080/shapi");
//        apiConfig.setClientPrivatekey("DB01BC234DC35B5DDCBDCCD55316EDE97E9BAADDA64819CDAC90C6BD2DA35E37");
//        apiConfig.setServerPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjQhYX55kFJwrj1ZnRO0LJQJEfSQh9vsfZ+3dq4fERx6udF9Ls7vo0rhIZV1KcGa0tAiyrebLsq9fjc/UY+kXsSQ7JOQp31NjLtGAi25H5YN9xSRUvtbHW8plaedWSgcA9Zw+crMESxuhAt41IVVhZKMm0VZbEOnQglhdj+lWld17k7N1uj3o3JQA4fNAajDICbL6qKV3U2q19hRUzhlAXlYdpeIZhqMc8REkrhv3eqwmzxv2bZ7NSPqG3uvzYn7L5qNkZqTdp37ikUwPhVNXSMQu7m0KUVcO4S5rjhW3r3dQF+r+kpiyjoCVsFTV1NH70ajxEoLFVlH6ksLKZ6xkgQIDAQAB");
//
//        ShOssDownloadStream dstream = null;
//        try {
//            dstream = new ShOssDownloadStream(ossConfig, apiConfig, "tyj_account");
//        } catch (Exception e) {
//            throw new RuntimeException("oss 下载失败");
//        }
//        try (InputStream is = dstream.retriveObject()){
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        return null;
//    }
//
//
//}
