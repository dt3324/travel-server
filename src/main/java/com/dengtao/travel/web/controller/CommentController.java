package com.dengtao.travel.web.controller;

import com.dengtao.travel.bean.Comment;
import com.dengtao.travel.service.ICommentService;
import com.dengtao.travel.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/save",produces={"application/json;charset=UTF-8"})
    public JsonResult save(Long userId,String commentText,Long pId,Long toUserId){
        commentService.save(userId,commentText,pId,toUserId);
        return JsonResult.get();
    }
    @CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
    @RequestMapping(value ="/select",produces={"application/json;charset=UTF-8"})
    public JsonResult select(){
        Map<String,List<Comment>> result = commentService.select();
        return JsonResult.get().setData(result);
    }
}
