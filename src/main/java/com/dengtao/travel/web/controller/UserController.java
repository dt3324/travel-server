package com.dengtao.travel.web.controller;

import com.dengtao.travel.bean.User;
import com.dengtao.travel.service.IUserService;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/save",produces={"application/json;charset=UTF-8"})
    public JsonResult save( User user){
        userService.save(user);
        User findUser = userService.findByUser(user);
        return JsonResult.get().setData(findUser);
    }
}
