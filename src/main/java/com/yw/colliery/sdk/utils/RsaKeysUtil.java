package com.yw.colliery.sdk.utils;

import com.alibaba.druid.filter.config.ConfigTools;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: xuzhou-013
 * @Date: 2019/9/20 16:16
 * @Description:
 */
public class RsaKeysUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "Cqywapis";

    public static void main(String[] args) {
        Map<String, Object> keyMap;
        try {
            keyMap = initKey();
            String publicKey = getPublicKey(keyMap);
            System.out.println(publicKey);
            String privateKey = getPrivateKey(keyMap);
            System.out.println(privateKey);
            String encrypt = ConfigTools.encrypt("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAN0RGn10IBhm+pQV8xKzMn67jlDw\n" +
                    "WnMBJ0m9ShsDSwO3aFY5Y7Aic0ik2pVUEd7j+jAEeA1qMtxuHtIXnmU5ahwMYHxhZ8snx3NMCnYw\n" +
                    "UZO3MOQ8F+b5/4YTF46GePzKJy0YdLgqv+w3lM3X1ndkoZKKYd4DvwhqiyyNyUlScoXfAgMBAAEC\n" +
                    "gYBHjS2hxWBOBaa9H42ZU97YHiSmFeVwq8LnnxjJPXueLFLc9Z5KeBwQOyt0gc128xsbR5cD4Iet\n" +
                    "yQa4wWRHJxosd4vZbkXlNkMDSxWpwyJrNskwDLsz0jExYXjKmSm0g6i7uqTQ3wLUSDn2tOi+5+ul\n" +
                    "+qqlsulna8xCE9Yyqy97AQJBAP6CVqiwbbMY44sNS+yeeYWBDT1CDdBB/xatHt06YcWHQq93qmga\n" +
                    "OvUHIiFDbIjppUN7m6UZAec65bEGeOcZ4GECQQDeXJ1qtnQ9TukuanLPve+NmMdU0kTxfP1GWJ88\n" +
                    "enIc8KNbg2hfsCDVeVwrmTlKVTsRTp/b1dZeYy2EXfA3Lw4/AkEAwyqP6avhc4WSd/8qSbj81zOv\n" +
                    "Q1pz3U++nVUEZgFvGFow+P1633m93EgsE/XwNM12vdj78g7LNtYjbSzZad6eAQJAFSXnztNlgMM1\n" +
                    "iDWVj853rxbdtmJ9Lh5+FCevlVfr7Haygqyfy6H7rXHqYzcibMBoar/4kjbnOXjjRliM1SLDjwJA\n" +
                    "WOpmnSwyDJXsHP7dx2/wcbX+GEDAxci7vMpBB9hrkUpbMqp+cNaz+SVvbuo2I5UD9Y2Wdpf/mrMg\n" +
                    "yn052W9pNg==", "123456");
            System.out.println(encrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        byte[] publicKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        byte[] privateKey = key.getEncoded();
        return encryptBASE64(key.getEncoded());
    }

    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<String, Object>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
}
