package github.maqu.aes256;

import android.util.Base64;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Project :  AES256.
 * Package name: github.maqu.aes256
 * Created by :  Benjamin.
 * Created time: 2017/4/23 17:38
 * Changed by :  Benjamin.
 * Changed time: 2017/4/23 17:38
 * Class description: AES encrypt and Decrypt
 */

public class AES256Cipher {

    /**
     * 1、如果报错：Java.security.InvalidKeyException: Illegal key size
     * 请更换   JDK路径\jre\lib\security 文件底下的local_policy.jar和US_export_policy.jar
     * 详情请参考：http://bbs.csdn.NET/topics/340263760
     * <p>
     * 2、如果报错：java.lang.SecurityException: Cannot set up certs for trusted CAs
     * 请更换  jce.jar 包 或者使用更高版本的JDK
     * 详情请参考：http://blog.csdn.Net/tomy_xu_1981/article/details/539323
     */

    public static byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};


    public static String AES_Encode(String str, String key) throws Exception {

        byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec);

        return Base64.encodeToString(cipher.doFinal(textBytes), 0);
    }

    /**
     * @param str 需要加密加密的字符
     * @param key 加密用的字符串key
     * @return
     * @throws Exception
     */
    public static String AES_Decode(String str, String key) throws Exception {

        byte[] textBytes = Base64.decode(str, 0);
        //byte[] textBytes = str.getBytes("UTF-8");
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        SecretKeySpec newKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec);
        return new String(cipher.doFinal(textBytes), "UTF-8");
    }
}
