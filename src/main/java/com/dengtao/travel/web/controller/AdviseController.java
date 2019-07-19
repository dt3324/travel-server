package com.dengtao.travel.web.controller;

import com.dengtao.travel.bean.Advise;
import com.dengtao.travel.service.IAdviseService;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/advise")
public class AdviseController {
    @Autowired
    private IAdviseService adviseService;
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/save",produces={"application/json;charset=UTF-8"})
    public JsonResult save(Long userId,String advise){
        adviseService.save(userId,advise);
        return JsonResult.get();
    }
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/select",produces={"application/json;charset=UTF-8"})
    public JsonResult select(){
        List<Advise> list = adviseService.select();
        return JsonResult.get().setData(list);
    }
}
