package com.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 随机数工具
 *
 * @author CC
 * @date 2024/05/23
 */
public class RandomUtils {
    /**
     * 获取随机数
     *
     * @param numNumber 随机数个数
     * @param minNum    随机数最小值
     * @param maxNum    随机数最大值
     * @throws Exception
     */
    public static String GetRandomNumber(int numNumber, int minNum, int maxNum) throws Exception {

        URL randomOrg = new URL("https://www.random.org/integers/?num=" + numNumber + "&min=" + minNum + "&max=" + maxNum + "&col=1&base=10&format=plain&rnd=new");
        HttpURLConnection con = (HttpURLConnection) randomOrg.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder randomnum = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            randomnum.append(inputLine);
        }
        in.close();
        return randomnum.toString();
    }
}
