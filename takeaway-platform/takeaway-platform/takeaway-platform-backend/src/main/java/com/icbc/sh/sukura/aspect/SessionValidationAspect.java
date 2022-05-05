//package com.icbc.sh.sukura.aspect;
//
//import com.alibaba.dubbo.config.annotation.Reference;
//import com.icbc.sh.ecommunications.service.AccountService;
//import com.icbc.sh.server.RedisClient;
//import org.apache.commons.lang3.StringUtils;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Component
//public class SessionValidationAspect {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Reference
//    private RedisClient redisService;
//    @Autowired
//    AccountService accountService;
//
//    @Pointcut("(!@within(com.icbc.sh.ecommunications.annotation.SessionValidationIgnore) || " +
//            "!@annotation(com.icbc.sh.ecommunications.annotation.SessionValidationIgnore)) && " +
//            "(@within(com.icbc.sh.ecommunications.annotation.SessionValidation) || " +
//            "@annotation(com.icbc.sh.ecommunications.annotation.SessionValidation))"
//    )
//    public void invokeController() {
//    }
//
//    @Before("invokeController()")
//    public void doBefore(JoinPoint JoinPoint) throws Exception {
//        ServletRequestAttributes attributes = (ServletRequestAttributes)
//                RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        String url = request.getRequestURI();
//
//        String token = request.getHeader("IBANK_TOKEN");
//        if (StringUtils.isEmpty(token)) {
//            logger.error("非法调用: {}", url);
//            throw new RuntimeException("非法调用! " + url);
//        }
//        if (null == redisService || !redisService.exists(token)) {
//            logger.error("登陆失效: {}", url);
//            throw new RuntimeException("登陆失效! " + url);
//        }
//        String jsonSession = redisService.get(token);
//        if (StringUtils.isEmpty(jsonSession)) {
//            logger.error("登陆失效: {}", url);
//            throw new RuntimeException("登陆失效! " + url);
//        }
//    }
//
//
//}
