package com.lt.health.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: MD5加密工具类
 * MD5加密是单向加密 不可逆==>不能解密
 * BCryptPasswordEncoder(强散哈希算法)： 双向加密 可逆的  可解密
 * @author: 狂小腾
 * @date: 2022/3/21 23:44
 */
@Slf4j
public class MD5Util {
    /**
     * md5加密
     *
     * @param password 要加密的内容
     * @return 32位的加密串
     */
    public static String md5(String password) {
        if (StringUtils.isNotBlank(password)) {
            byte[] bytes = null;
            try {
                bytes = MessageDigest.getInstance("md5").digest(password.getBytes());
            } catch (NoSuchAlgorithmException e) {
                log.error("没有MD5这个加密算法！");
            }
            // 由md5加密算法得到的字节数组转换为16进制数字
            StringBuilder code = new StringBuilder(new BigInteger(1, bytes).toString(16));
            //保证md5加密后是32位
            for (int i = 0; i < 32 - code.length(); i++) {
                code.insert(0, "0");
            }
            return code.toString();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}