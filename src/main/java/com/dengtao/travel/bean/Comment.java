package com.dengtao.travel.bean;

import java.util.ArrayList;
import java.util.List;

public class Comment {
    private Long id;
    private Long pId;
    private String  userName;
    private String toUserName;
    private String commentText;
    private List<Comment> childComment;
    private String  commentTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public List<Comment> getChildComment() {
        return childComment;
    }

    public void setChildComment(List<Comment> childComment) {
        this.childComment = childComment;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", pId=" + pId +
                ", userName='" + userName + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", commentText='" + commentText + '\'' +
                ", childComment=" + childComment +
                ", commentTime=" + commentTime +
                '}';
    }
}
