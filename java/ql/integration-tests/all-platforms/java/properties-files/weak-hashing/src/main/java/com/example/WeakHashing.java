package com.example;

import java.utils.Properties;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class WeakHashing {
    void hashing() {
        java.util.Properties props = new java.util.Properties();
        props.load(new FileInputStream("example.properties"));

        MessageDigest bad = MessageDigest.getInstance(props.getProperty("hashAlg1"));

        MessageDigest ok = MessageDigest.getInstance(props.getProperty("hashAlg2"));
    }
}
