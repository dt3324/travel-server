package com.dengtao.travel.web.controller;

import com.dengtao.travel.service.ICreateOpenId;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@RestController
public class CreateOpenIdController {
    @Autowired
    private ICreateOpenId createOpenId;

    //appid 开发者appid
//secret开发者appid对应的密钥（好像）
//js_code code
//只要这三个参数就行
    @CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST}, origins = "*")
    @RequestMapping(value = "/getUserOpenId", produces = {"application/json;charset=UTF-8"})
    public JsonResult getUserOpenId(String js_code) {
        String openId = createOpenId.getOpenId(js_code);
        return new JsonResult().setData(openId);
    }
}
