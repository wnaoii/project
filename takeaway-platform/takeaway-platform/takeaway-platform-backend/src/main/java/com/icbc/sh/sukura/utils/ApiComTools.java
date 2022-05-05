//package com.icbc.sh.sukura.utils;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import sh.api.client.ShApiClient;
//import sh.api.client.ShapiClientConfig;
//
//import java.util.UUID;
//
//public class ApiComTools {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(ApiComTools.class);
//
//    /**
//     * <pre>
//     *  分行门户通讯函数
//     * </pre>
//     *
//     * @param shGateUrl    http://107.6.61.44:9080/shapi (测试环境)
//     * @param send2HQorSH  请求目标,往哪里发送，只能总 HQ-总行 SH-分行
//     * @param applyUri     请求url ,总行的送api的url,分行的送自己的url
//     * @param applyJsonMsg 请求报文,Json格式字符串
//     * @param shAppid      分行服务门户上的应用名
//     * @param shEncKey     分行门户AES密钥
//     * @param hqAppid_opt  总行APIID,选送,不上总行门户设空串
//     * @return JSONObject 描述如下
//     * SYSTEM_RET  取平台的返回代码 全0才是正常
//     * SYSTEM_MSG 取平台的返回信息
//     * RESPONSE 取业务返回数据
//     * @throws Exception
//     */
//    public static String Exchange2ShGate(String shGateUrl, String send2HQorSH, String applyUri, String applyJsonMsg, String shAppid, String shEncKey, String hqAppid_opt) throws Exception {
//
//
//        String RESPONSE = "";
//
//        ShapiClientConfig scc = new ShapiClientConfig();
//
//        scc.setIcbchqAppid(hqAppid_opt);//总行APIID,不上总行门户设空串
//        scc.setSHAPPID(shAppid);//分行服务门户上的应用名,会和在shapi_app_reg.APPNAME里面的做校验
//        scc.setENCKEY(shEncKey);//分行门户AES密钥,会和在shapi_app_reg 里面的做校验
//
////		String SHGATEURL = ShConfigUtil.DB_QUERY_DIC_ONESTR("SHGATEURL", "SHGATEURL", "0");
//        String SHGATEURL = shGateUrl;
//        //1	SHGATEURL	SHGATEURL	0	http://107.6.61.44:9080/shapi	分行服务门户的主地址  107.6.61.44 107.6.61.90 WUDF	AAAEoQAAGAAAZOtABb
//        if ((SHGATEURL == null) || ("".equals(SHGATEURL))) {
//            logger.info("SHGATEURL参数没有设置,请检查");
//        }
//
//        scc.setBASEURL(SHGATEURL);//分行服务门户的主地址
//        //测试环境 107.6.61.44 107.6.61.90
//        //端口  cat /home/wasup/nginx/conf/nginx.conf |grep listen
//        //联几套   vi /etc/hosts 查看APIP.COSP.SOFT2.ICBCSH说明
//
//        String uuid = UUID.randomUUID().toString();//分行门户的交易流水，跟踪用
//
//        String applyUrl = "";//拼接最终通信url
//        if ("HQ".equals(send2HQorSH)) {
//            logger.info("往总行发送");
//            applyUrl = "/icbchq" + applyUri;//往总行发送 /icbchq
//        } else if ("SH".equals(send2HQorSH)) {
//            logger.info("往分行内部发送");
//            applyUrl = "/shlocal" + applyUri;//往分行发送 /shlocal
//        } else {
//            logger.info("只能送HQ或者SH");
//            return RESPONSE;
//        }
//
//        ShApiClient client = new ShApiClient(scc);
//
//        logger.info("HqAppid_opt=" + hqAppid_opt);
//        logger.info("ShAppid=" + shAppid);
//        logger.info("ShEncKey=" + shEncKey);
//        logger.info("SHGATEURL=" + SHGATEURL);
//        logger.info("applyJsonMsg=" + applyJsonMsg);
//        logger.info("applyUrl=" + applyUrl);
//        logger.info("uuid=" + uuid);
//
//
//        //发起通讯
//        client.doPost(applyUrl, applyJsonMsg, uuid, "");
//        //client.doPost("服务URI", "发送的数据", "业务UUID", "如果该请求是作为其他请求的服务端，则提供前一层请求的UUID，否则送空串");
//
//        //返回信息检查
//        logger.info(
//                "SYSTEM_RET=[" + client.getSysReturnCode() + "]" +     //取平台的返回代码 全0才是正常
//                        "SYSTEM_MSG=[" + client.getSysReturnMsg() + "]" +   //取平台的返回信息
//                        "RESPONSE=[" + client.getResponse() + "]"             //取业务返回数据
//        );
//
//        RESPONSE = client.getResponse();//不用判断SYSTEM_RET,一般不会错,错了直接崩
//        return RESPONSE;
//    }
//
//
//}
