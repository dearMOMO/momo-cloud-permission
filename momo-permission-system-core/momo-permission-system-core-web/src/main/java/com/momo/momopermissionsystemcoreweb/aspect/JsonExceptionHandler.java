//package com.momo.momopermissionsystemcoreweb.aspect;
//
//import com.momo.common.core.error.BizException;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.sql.SQLSyntaxErrorException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * 统一处理非格式化的异常错误
// *
// * @author fuzl
// */
//@ControllerAdvice
//@Slf4j
//public class JsonExceptionHandler {
//
//    private static Pattern pat = Pattern.compile("[\u4e00-\u9fa5]");
//
//
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public Map handler(HttpServletRequest req, Exception e) throws Exception {
//        log.error("JsonExceptionHandler 错误信息：{}  {}", e.getMessage(), e);
//        Map<String, Object> m = new HashMap<>();
//        try {
//            String msg = e.getMessage();
//            if (e instanceof BizException) {
//                m.put("message", msg);
//                m.put("msg", msg);
//                m.put("data", msg);
//                m.put("status", ((BizException) e).getError().getErrorCode());
//            } else if (e instanceof HttpMessageNotReadableException) {
//                String resultMsg = isContainsChinese(msg) ? msg : "请求参数错误";
//                m.put("message", resultMsg);
//                m.put("data", resultMsg);
//                m.put("status", 20000);
//            } else if (e instanceof MethodArgumentNotValidException) {
//                m.put("status", 20000);
//                StringBuffer sb = new StringBuffer();
//                ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors().stream().forEach(x -> sb.append(x.getDefaultMessage() + ";\n"));
//                m.put("data", sb.toString());
//                m.put("message", sb.toString());
//                m.put("msg", sb.toString());
//            } else if (e instanceof HttpRequestMethodNotSupportedException) {
//                log.error("请求类型不被允许:{}" + e.getMessage());
//                m.put("status", 90003);
//                m.put("data", "请求类型不被允许：" + msg);
//                m.put("message", "请求类型不被允许：" + msg);
//                m.put("msg", "请求类型不被允许：" + msg);
//            } else if (e instanceof MalformedJwtException) {
//                log.error("JWT信息错误:{}" + e.getMessage());
//                m.put("status", 90003);
//                m.put("data", "JWT信息错误");
//                m.put("message", "JWT信息错误");
//                m.put("msg", "JWT信息错误");
//            } else if (e instanceof JwtException) {
//                log.error("JWT信息错误:{}" + e.getMessage());
//                m.put("status", 90003);
//                m.put("data", "JWT信息错误");
//                m.put("message", "JWT信息错误");
//                m.put("msg", "JWT信息错误");
//            } else if (e instanceof SQLSyntaxErrorException) {
//                log.error("SQL异常:{}" + e.getMessage());
//                m.put("status", 90003);
//                m.put("data", "SQL异常" + e.getMessage());
//                m.put("message", "SQL异常" + e.getMessage());
//                m.put("msg", "SQL异常" + e.getMessage());
//            } /*else if (null != msg && isContainsChinese(msg)) {
//                m.put("status", 20000);
//                m.put("data", msg);
//            } */ else {
//                m.put("status", 10000);
//                m.put("data", "当前服务器繁忙");
//                m.put("message", "当前服务器繁忙");
//                m.put("msg", "当前服务器繁忙");
//            }
//        } catch (Exception var3) {
//            e.printStackTrace();
//            log.error("统一处理非格式化的异常错误:{}", e.getMessage());
//            m.put("data", var3.getMessage());
//            m.put("status", 9999);
//            m.put("message", "统一处理非格式化的异常错误:{}" + e.getMessage());
//            m.put("msg", "统一处理非格式化的异常错误:{}" + e.getMessage());
//        }
//        return m;
//    }
//
//    private static boolean isContainsChinese(String str) {
//        Matcher matcher = pat.matcher(str);
//        boolean flg = false;
//        if (matcher.find()) {
//            flg = true;
//        }
//        return flg;
//    }
//}
