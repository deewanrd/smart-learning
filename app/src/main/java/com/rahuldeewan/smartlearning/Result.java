package com.rahuldeewan.smartlearning;

import java.util.List;

/**
 * Created by Mridul
 * on 17-11-2017.
 */

public class Result {

    private String id;
    private long Score;
    private String Topic;
    private String level;
    private String date;

    public Result() {
    }

    public Result(ProfileActivity profileActivity, List<Result> result) {

    }

    Result(String id, long score, String topic, String level,String date) {
        this.id = id;
        Score = score;
        Topic = topic;
        this.level = level;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    long getScore() {
        return Score;
    }

    public void setScore(long score) {
        Score = score;
    }

    String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}