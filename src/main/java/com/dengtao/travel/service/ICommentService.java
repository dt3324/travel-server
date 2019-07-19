package com.dengtao.travel.service;

import com.dengtao.travel.bean.Comment;

import java.util.List;
import java.util.Map;

public interface ICommentService {

    Map<String,List<Comment>> select();

    void save(Long userId, String commentText, Long pId, Long toUserId);
}
