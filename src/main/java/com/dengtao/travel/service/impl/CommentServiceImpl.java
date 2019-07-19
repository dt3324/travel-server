package com.dengtao.travel.service.impl;

import com.dengtao.travel.bean.Comment;
import com.dengtao.travel.bean.User;
import com.dengtao.travel.mapper.CommentMapper;
import com.dengtao.travel.mapper.UserMapper;
import com.dengtao.travel.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String,List<Comment>> select() {
        Map<String,List<Comment>> result = new HashMap<>(20);
        List<Comment> list = commentMapper.select(null);
        for (Comment comment : list) {
            Long id = comment.getId();
            List<Comment> select = commentMapper.select(id);
            comment.setChildComment(select);
        }
        result.put("comment",list);
        return result;
    }

    @Override
    public void save(Long userId, String commentText, Long pId, Long toUserId) {
        Comment comment = new Comment();
        if(commentText==null||commentText==""){
            throw new RuntimeException("评论不能够为空；");
        }else {
            comment.setCommentText(commentText);
        }
        User user = userMapper.findById(userId);
        comment.setUserName(user.getNickName());
        User toUser = userMapper.findById(toUserId);
        comment.setToUserName(toUser.getNickName());
        comment.setCommentTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        comment.setpId(pId);
        System.out.println(comment);
        commentMapper.save(comment);
    }

}
