//package com.icbc.sh.sukura;
//
//import org.apache.commons.lang3.RandomStringUtils;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sh.api.client.ShapiClientConfig;
//import sh.api.client.log.ShApiClientLoggerUtil;
//import sh.icbc.oss.client.ShOssClient;
//import sh.icbc.oss.client.cfg.ShOssConfigBean;
//import sh.icbc.oss.client.memory.ShOssUploadStream;
//import test.TestRsaKey;
//
//import java.io.FileInputStream;
//
//public class TestUpload {
//    private ShOssConfigBean ossConfig = new ShOssConfigBean();
//    private ShapiClientConfig apiConfig = new ShapiClientConfig();
//    // String localFile = "d:/江苏地图.jpg";
//
//    public static String serverPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArw66fmh4xHMR/us0V+p3J08RCcGlJrNy5E4KxD1sHXa/syJU/5UdK69j+WYdgEIlU8lBNWMfBur607RI62lj0UV3WG7MTEKbnDj8mwPDEWDiXEfjnsWMDQlgKN7FeupowS2jh9zBKPr9PBrfy1rb8f+ANMYsdImn3tDOrwy6X2DuoKFvM6fYpitknjwc1Nje2OK4wKIBhzV6sA6ZYQh+4Iwt9ZAKk4+3WGeYe/w3uiM8Xjk83+ckLWncW+glBznMO76joA6FdLvSafrVsmWju2U+T4GfjcCpI5GgeAhotHCSsrWhrui4sDczkEbuGcsy7A/a5lAC4YqN9Lbm9q4qJwIDAQAB";
//    String localFile = "";
//    //"c:/trace.tar";
//    //"c:/testshftp.gz" ;
//    //"c:/trace.tar";
//    String objectName ="memory.big.test";
//    //"memory.mid.test";
//    //"memory.big.test";
//
//
//    public static int LOOP = 1;
//
//    @Before
//    public void setUp() throws Exception {
//
//        Logger logger = LoggerFactory.getLogger(this.getClass());
//        ShApiClientLoggerUtil.SET_LOGGER(logger);
//        //ShOssThreadPool.setPOOL_SIZE(3);
//        System.out.println("全局初始化完毕@ShOssUploadMemoryStreamTest");
//
//        ossConfig.setTempDir("./tmp");
//        ossConfig.setBucket("shbucket.lich.first");
//        ossConfig.setClientAppId("shapp.shoss.fromlich");
//        String token = "";
//
//        ossConfig.setToken(token);
//        apiConfig.setVersion(ShapiClientConfig.COMMVERSION_AES256_WITH_JAVARSA);
//        apiConfig.setClientPrivatekey(TestRsaKey.privateKey);
//        apiConfig.setServerPublicKey(serverPublicKey);
//        //apiConfig.setServerPublicKey(TestRsaKey.publicKey);
//        apiConfig.setSHAPPID("shapp.shoss.fromlich");
//        //apiConfig.setBASEURL("http://107.6.61.44:9080/shapi");
//        apiConfig.setBASEURL("http://107.6.13.120:8080/shapi");
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void test() throws Exception {
//        for (int i = 0; i < LOOP; i++) {
//            long start=System.currentTimeMillis();
//            String objectNamet=objectName+i;
//            //ossConfig.setObjectName(objectNamet);
//            ShOssClient ossclient = new ShOssClient();
//            try {
//                ossclient.deleteObject(ossConfig, apiConfig, objectNamet);
//                System.out.println("deleteObjectSucc:" + objectNamet);
//
//            } catch (Exception e) {
//                System.err.println("deleteObjectFail:" + e.getMessage());
//            }finally {
//                Thread.sleep(3000);
//            }
//            //这个例子中，上传一个本地文件
//            FileInputStream fis = new FileInputStream(localFile);
//            ShOssUploadStream uploadStream = new ShOssUploadStream(ossConfig, apiConfig, objectNamet, fis);
//            uploadStream.storeObject();
//            uploadStream.join();
//            fis.close();
//            long endTime=System.currentTimeMillis();
//            long cost=(endTime-start)/1000;
//            System.out.println("*****************************LOOP" + i + "*COST="+cost);
//        }
//        Thread.sleep(5 * 1000);
//        // fail("Not yet implemented");
//    }
//
//}
