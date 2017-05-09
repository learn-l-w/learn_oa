package com.learn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: liudi
 * Date: 17/5/8
 * Time: 下午23:09
 */
public class MD5Utils {

    private static final Logger logger = LoggerFactory
            .getLogger(MD5Utils.class);

    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("MD5Utils messagedigest初始化失败", e);
        }
    }

    /**
     * 对文件进行md5加密
     *
     * @param file
     * @return
     */
    public static String getMD5File(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5InputStream(InputStream in) throws IOException {
        StringBuffer md5 = new StringBuffer();
        byte[] dataBytes = new byte[4096];
        int nread = 0;
        while ((nread = in.read(dataBytes)) != -1) {
            messagedigest.update(dataBytes, 0, nread);
        }
        byte[] mdbytes = messagedigest.digest();
        // convert the byte to hex format
//        for (int i = 0; i < mdbytes.length; i++) {
//            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
//        }
        return bufferToHex(mdbytes);
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    /**
     * 检查md5的密码
     *
     * @param password
     * @param md5PwdStr
     * @return
     */
    public static boolean checkPassword(String password, String md5PwdStr) {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }


}
