package com.dengtao.travel.service.impl;

import com.dengtao.travel.service.ICreateOpenId;
import com.dengtao.travel.util.JsonResult;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Service
public class CreateOpenIdImpl implements ICreateOpenId {
    @Override
    public String getOpenId(String js_code) {
        String appid = "wx20594613ee20196d";
        String secret = "e1664bf8f5586ba590eb8604db795bac";

        BufferedReader in = null;
        String result = "";
        try {
            String urlNameString ="https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&js_code=" + js_code + "&secret=" + secret + "&grant_type=authorization_code";
            URL realUrl = new URL(urlNameString);

            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();

            for (String key : map.keySet()) {
                for (String a : map.get(key)) {
                }
            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            JSONObject jsonObject = new JSONObject(result);
            String openid = jsonObject.get("openid").toString();
            System.out.println(openid);
            return openid;
        } catch (Exception e) {
            throw  new RuntimeException("openid获取失败！");
// TODO Auto-generated catch block
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
