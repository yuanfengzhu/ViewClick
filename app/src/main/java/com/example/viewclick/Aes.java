package com.example.viewclick;

import android.util.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Aes {


//    /**
//     * 说明: AES256加密
//     *
//     * @param stringToEncode 明文
//     * @param keyString      密钥
//     * @return Bses64格式密文
//     */
//    public static String AES256Encode(String stringToEncode, String keyString)
//            throws NullPointerException {
//        if (keyString == null || keyString.length() == 0) {
//            return null;
//        }
//        if (stringToEncode == null || stringToEncode.length() == 0) {
//            return null;
//        }
//        try {
//            SecretKeySpec skeySpec = getKey(keyString);
//            byte[] data = stringToEncode.getBytes(StandardCharsets.UTF_8);
//            final byte[] iv = new byte[16];
//            Arrays.fill(iv, (byte) 0x00);
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
//            String encrypedValue = Base64.encodeToString(cipher.doFinal(data),
//                    Base64.DEFAULT);
//            return encrypedValue;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * 说明 :AES256解密
//     *
//     * @param text      Base64格式密文
//     * @param keyString 密钥
//     * @return String格式明文
//     */
//    public static String AES256Decrypt(String text, String keyString) {
//        // byte[] rawKey = getRawKey(key);
//        if (keyString == null || keyString.length() == 0) {
//            return null;
//        }
//        if (text == null || text.length() == 0) {
//            return null;
//        }
//        try {
//            SecretKey key = getKey(keyString);
//            final byte[] iv = new byte[16];
//            Arrays.fill(iv, (byte) 0x00);
//            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//            byte[] data = Base64.decode(text, Base64.DEFAULT);
//            Cipher cipher = Cipher.getInstance("AES");
//            cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
//            byte[] decrypedValueBytes = (cipher.doFinal(data));
//            return new String(decrypedValueBytes, StandardCharsets.UTF_8);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    /**
//     * 说明 :将密钥转行成SecretKeySpec格式
//     *
//     * @param password 16进制密钥
//     * @return SecretKeySpec格式密钥
//     */
//    private static SecretKeySpec getKey(String password)
//            throws UnsupportedEncodingException {
//        // 如果为128将长度改为128即可
//        int keyLength = 256;
//        byte[] keyBytes = new byte[keyLength / 8];
//        // explicitly fill with zeros
//        Arrays.fill(keyBytes, (byte) 0x0);
//        byte[] passwordBytes = toByte(password);
//        int length = passwordBytes.length < keyBytes.length ? passwordBytes.length
//                : keyBytes.length;
//        System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
//        return new SecretKeySpec(keyBytes, "AES");
//    }
//
//    /**
//     * 说明 :随机生成一组AES密钥
//     *
//     * @return 16进制AES密钥
//     */
//    public static String getRawKey() {
//        KeyGenerator kgen = null;
//        SecureRandom sr = null;
//        try {
//            kgen = KeyGenerator.getInstance("AES");
//            // SHA1PRNG 强随机种子算法, 要区别4.2以上版本的调用方法
//            if (android.os.Build.VERSION.SDK_INT >= 17) {
//                sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
//            } else {
//                sr = SecureRandom.getInstance("SHA1PRNG");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        kgen.init(256, sr); // 256 bits or 128 bits,192bits
//        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        return bytes2Hex(raw);
//    }
//
    /**
     * byte数组转换为16进制字符串
     *
     * @param bts 数据源
     * @return 16进制字符串
     */
    public static String bytes2Hex(byte[] bts) {
        StringBuilder des = new StringBuilder();
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des.append("0");
            }
            des.append(tmp);
        }
        return des.toString();
    }

    /**
     * 将16进制转换为byte数组
     *
     * @param hexString 16进制字符串
     * @return byte数组
     */
    private static byte[] toByte(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2),
                    16).byteValue();
        return result;
    }


    private static String KEY = "gtxyxymsaes2011y";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String KEY_ALGORITHM = "AES";


    public static String encodeAes(String data, String key) {
        try {

            Key keySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);    //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] byteResult = cipher.doFinal(data.getBytes("utf-8"));
            return encode(byteResult).replace("+", "-").replace("/", "_");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String encodeAes(String data) {
        try {

            Key keySpec = new SecretKeySpec(KEY.getBytes(), KEY_ALGORITHM);    //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] byteResult = cipher.doFinal(data.getBytes("utf-8"));
            return encode(byteResult).replace("+", "-").replace("/", "_");
        } catch (Exception e) {
            return "";
        }
    }

    private static String decodeAes(String data, String key) {
        try {

            byte[] raw = key.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = decode(data.replace("-", "+").replace("_", "/"));
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String decodeAes(String data) {
        try {

            byte[] raw = KEY.getBytes("UTF-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = decode(data.replace("-", "+").replace("_", "/"));
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param bytes
     * @return
     */
    private static byte[] decode(final String bytes) {
            return toByte(bytes);
    }

    /**
     * 二进制数据编码为BASE64字符串
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    private static String encode(final byte[] bytes) {
        return bytes2Hex(bytes);
    }

    public static void main(String[] args) {
        String a="18060481388";

        try {
            System.out.println("sss"+decodeAes(""));
        } catch (Exception e) {
            System.out.println("ss");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
