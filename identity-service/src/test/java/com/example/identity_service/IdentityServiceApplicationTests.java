package com.example.identity_service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdentityServiceApplicationTests {

    @Test
    void hash() throws NoSuchAlgorithmException {
        String password = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte[] digest = md.digest();
        String md5Hash = DatatypeConverter.printHexBinary(digest);
        System.out.println(md5Hash);
    }
}
