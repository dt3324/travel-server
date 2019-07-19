package com.dengtao.travel.web.controller;

import com.dengtao.travel.bean.TravelAddressDetail;
import com.dengtao.travel.service.ITravelAddressDetailService;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/detail")
public class TravelAddressDetailController {
    @Autowired
    private ITravelAddressDetailService travelAddressDetailService;

    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/select",produces={"application/json;charset=UTF-8"})
    public JsonResult select(String keyword){
        Long traveladdressId = Long.valueOf(keyword);
        TravelAddressDetail travelAddressDetail = travelAddressDetailService.select(traveladdressId);
        return JsonResult.get().setData(travelAddressDetail);
    }
}
