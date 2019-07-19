package com.dengtao.travel.web.controller;

import com.dengtao.travel.service.ILunchService;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lunch")
public class LunchController {
    @Autowired
    private ILunchService lunchService;
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/random",produces={"application/json;charset=UTF-8"})
    public JsonResult random(String name){
        String lunch = lunchService.random(name);
        return JsonResult.get().setData(lunch);
    }

}
