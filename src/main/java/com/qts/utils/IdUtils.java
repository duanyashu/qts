package com.qts.utils;

import java.util.UUID;

/**
 * @description: id工具类
 * @author: duanyashu
 * @time: 2020-07-07 08:53
 */
public class IdUtils {

    public static String getId(){
        String uuidStr = UUID.randomUUID().toString();
        return uuidStr.substring(0,8)+uuidStr.substring(9,13)+uuidStr.substring(14,18)+uuidStr.substring(19,23)+uuidStr.substring(24);
    }

    public static void main(String[] args) {
        System.out.println(getId());
    }
}
