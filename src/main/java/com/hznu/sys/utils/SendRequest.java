package com.hznu.sys.utils;

/**
 * @author LIN
 * @date 2020/7/14 13:59
 */


import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendRequest {

    public static JSONObject sendJudgePost(JSONObject jsonParam) throws Exception {
        JSONObject result = null;
        BufferedReader in = null;
        try {
            // 创建url资源
            String urls = "http://linming.top:8880/judge";
            URL url = new URL(urls);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置允许输出
            conn.setDoOutput(true);
            // 设置允许输入
            conn.setDoInput(true);
            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置token
            conn.setRequestProperty("X-Judge-Server-Token", "b824cecedb22b06c3883b1f1dd9dd3150608fc24f8d0c16b0f85af8c8c761667");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            // 转换为字节数组
            byte[] data = (jsonParam.toString()).getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "application/json");
            // 开始连接请求
            conn.connect();
            OutputStream out = new DataOutputStream(conn.getOutputStream());
            // 写入请求的字符串
            out.write((jsonParam.toString()).getBytes());
            out.flush();
            out.close();
            // 请求返回的状态
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                // 请求返回的数据
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                result = JSONObject.parseObject(in.readLine());
                in.close();
            } else {
                throw new Exception(String.valueOf(conn.getResponseCode()));
            }
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }

}
