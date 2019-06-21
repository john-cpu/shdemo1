package com.sh.shdemo.entity;

import java.util.UUID;

public class test {
    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().substring(1,8);
        System.out.println(uuid);
    }
}
