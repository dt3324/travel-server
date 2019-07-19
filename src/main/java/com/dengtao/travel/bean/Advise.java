package com.dengtao.travel.bean;

public class Advise {
    private Long id;
    private String advise;
    private User user;
    private Long adviseTime;

    public Long getAdviseTime() {
        return adviseTime;
    }

    public void setAdviseTime(Long adviseTime) {
        this.adviseTime = adviseTime;
    }

    public Long getId() {
        return id;
    }

    public Advise setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAdvise() {
        return advise;
    }

    public Advise setAdvise(String advise) {
        this.advise = advise;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Advise setUser(User user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "Advise{" +
                "id=" + id +
                ", advise='" + advise + '\'' +
                ", user=" + user +
                '}';
    }
}
