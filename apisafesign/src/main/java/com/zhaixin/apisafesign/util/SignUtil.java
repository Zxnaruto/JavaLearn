package com.zhaixin.apisafesign.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Program: apisafesign
 * @Classname: SignUtil
 * @Author: Zhai
 * @Description:
 * @Date: 2021/06/09 17:40
 */
public class SignUtil {
    public static String getSignature(String method, String url, String body, String timestamp, String nonce, String appSecret){
        //第一层签名
        String requestStr1 = method + url + body + appSecret;
        String signResult1 = DigestUtils.md5Hex(requestStr1);
        //第二层签名
        String requestStr2 = appSecret + timestamp + nonce + signResult1;
        return DigestUtils.md5Hex(requestStr2);
    }
}

