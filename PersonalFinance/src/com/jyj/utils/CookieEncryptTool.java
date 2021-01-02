package com.jyj.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/*
 * 用Base64加解密保存在Cookie中的信息
 * 按照REC2045的定义*/
public class CookieEncryptTool {
    //加密
    public static String encodeBase64(String cleartext) {
        try {
            cleartext = new String(Base64.encodeBase64(cleartext.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cleartext;
    }

    //解密
    public static String decodeBase64(String ciphertext) {
        try {
            ciphertext = new String(Base64.decodeBase64(ciphertext.getBytes()), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ciphertext;
    }
}
