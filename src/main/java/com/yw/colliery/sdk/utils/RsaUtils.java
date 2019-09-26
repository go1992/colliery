package com.yw.colliery.sdk.utils;

import lombok.Data;
import lombok.SneakyThrows;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Auther: xuzhou-013
 * @Date: 2019/9/23 14:22
 * @Description:
 */
public class RsaUtils {


    /**
     * 公钥加密
     *
     * @param plainText
     * @param publicKey
     * @return
     */
    @SneakyThrows
    public static String encrypt(String plainText, String publicKey) {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "SunRsaSign");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes( StandardCharsets.UTF_8)));
    }

    /**
     * 私钥解密
     *
     * @param cipherText
     * @param privateKey
     * @return
     * @throws BadPaddingException
     */
    @SneakyThrows
    public static String decrypt(String cipherText, String privateKey)  {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "SunRsaSign");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)), StandardCharsets.UTF_8);
    }

    @Data
    public static class StringKeyPair {

        private String publicKey;

        private String privateKey;

    }

    public static void main(String[] args) {
    }

}
