package com.patrickstar.blog.utils;/*
 * @PackageName: com.patrickstar.blog.utils
 * @ClassName: MD5
 * @Description:MD5加密工具类
 * @Author: PatrickStaR
 * @Date: 10/31/2019 10:30 AM
 */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    /**
     * MD5加密
     *
     * @author PatrickStaR
     * @date 10:31 AM 10/31/2019
     * @param str 要加密的字符串
     * @return 加密后的字符串
    **/
    public static String code(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
            byte[] byteDigest = digest.digest();
            int i;
            StringBuffer buffer = new StringBuffer(" ");
            for(int offset = 0; offset < byteDigest.length; offset++){
                i = byteDigest[offset];
                if(i < 0) {
                    i += 256;
                }
                if(i < 16) {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(i));
            }
            //32位加密
            return buffer.toString().trim();
            //16位加密
            //return buffer.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }
}
