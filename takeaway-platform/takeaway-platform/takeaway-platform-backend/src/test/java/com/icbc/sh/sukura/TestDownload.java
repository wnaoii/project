//package com.icbc.sh.sukura;
//
//import org.apache.commons.io.IOUtils;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sh.api.client.ShapiClientConfig;
//import sh.api.client.log.ShApiClientLoggerUtil;
//import sh.icbc.oss.client.cfg.ShOssConfigBean;
//import sh.icbc.oss.client.memory.ShOssDownloadStream;
//import sh.icbc.oss.client.slidewindow.ShOssDownloadSlideWindow;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class TestDownload {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    private ShOssConfigBean ossConfig = new ShOssConfigBean();
//    private ShapiClientConfig apiConfig = new ShapiClientConfig();
//
//    @Test
//    public void test() {
//
//
//        ossConfig.setBucket("shbucket.dpms.uds.nechdtl");
//        ossConfig.setClientAppId("shapp.zmexam.backend");
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
//    }
//
//    @Test
//    public void tmpTest() {
//        String localFile = "D:/tmp/k-20200614-nechdtl_20200614.bin";
//        String objectName ="k-20200614-nechdtl_20200614.bin";
//
//        ossConfig.setTempDir("D:/tmp");
//        ossConfig.setBucket("shbucket.dpms.uds.nechdtl");
//        ossConfig.setClientAppId("shapp.zmexam.backend");
//        String readToken = null;
//        try {
//            readToken = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("token/read_token.txt"), "UTF-8");
//        } catch (IOException e) {
//            logger.error("oss 读取token文件出错: {}", e);
//            throw new RuntimeException("oss 报错");
//        }
//        ossConfig.setToken(readToken);
//
//        apiConfig.setENCKEY("DB01BC234DC35B5DDCBDCCD55316EDE97E9BAADDA64819CDAC90C6BD2DA35E37");
//        apiConfig.setVersion(ShapiClientConfig.COMMVERSION_AES256);
//        apiConfig.setSHAPPID("shapp.zmexam.backend");
//        apiConfig.setBASEURL("http://107.6.61.44:9080/shapi");
//
//        try {
//            ShOssDownloadSlideWindow downloadWindow = new ShOssDownloadSlideWindow(ossConfig, apiConfig, objectName,
//                    localFile);
//            downloadWindow.downloadObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//}
