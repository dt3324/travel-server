package com.dengtao.travel.web.controller;

import com.dengtao.travel.bean.TravelAddress;
import com.dengtao.travel.service.ITravelAddressService;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class TravelAddressController {
    @Autowired
    private ITravelAddressService travelAddressService;
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/search",produces={"application/json;charset=UTF-8"})
    public JsonResult search(String keyword){
        System.out.println("keyword:"+keyword);
        List<TravelAddress> list =null;
        list = travelAddressService.search(keyword);
        return JsonResult.get().setData(list);
    }
}
