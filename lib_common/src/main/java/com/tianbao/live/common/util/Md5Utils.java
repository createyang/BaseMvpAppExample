package com.tianbao.live.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: Caoy
 * @created on: 2019/1/8 17:17
 * @description:
 */
public class Md5Utils {
    public static  String Md5Encode(String md5)
    {

        String Md5Value = null;
        try
        {
            MessageDigest msg = MessageDigest.getInstance("MD5");
            msg.update(md5.getBytes());
            byte mByte[] = msg.digest();
            int value;
            StringBuffer buffer = new StringBuffer("");
            for (int i = 0; i < mByte.length; i++)
            {
                value = mByte[i];
                if (value < 0)
                {
                    value += 256;
                }
                if (value < 16)
                {
                    buffer.append("0");
                }
                buffer.append(Integer.toHexString(value));
            }
            Md5Value = buffer.toString();
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return Md5Value;
    }
}

