package com.example.sujin.opencomplaintss;

/**
 * Created by Sujin on 7/3/2016.
 */
public class TrendComponent {
    String title;
    String desc;
    String vote;
    String tracker;

    public TrendComponent(String title, String desc, String vote, String tracker) {
        this.title = title;
        this.desc = desc;
        this.vote = vote;
        this.tracker = tracker;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getVote() {
        return vote;
    }

    public String getTracker() {
        return tracker;
    }
}
