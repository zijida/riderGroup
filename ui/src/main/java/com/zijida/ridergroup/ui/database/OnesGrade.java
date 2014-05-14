package com.zijida.ridergroup.ui.database;

/**
 * Created by SHENJUN on 14-5-8.
 * Create in RiderGroup
 */
public class OnesGrade {
    private String title;
    private String datetime;
    private String distance;
    private String spendtime;
    private String speed;

    public OnesGrade()
    {
        title = null;
        datetime = null;
        distance = null;
        spendtime = null;
        speed = null;
    }

    public OnesGrade(OnesGrade o)
    {
        this.title = o.title;
        this.datetime = o.datetime;
        this.distance = o.distance;
        this.spendtime = o.spendtime;
        this.speed = o.speed;
    }

    public String getTitle() { return title; }
    public String getDatetime() { return datetime; }
    public String getDistance() { return distance; }
    public String getSpendtime() { return spendtime; }
    public String getSpeed() { return speed; }

    public void setTitle(String s) { title = s; }
    public void setDatetime(String s) { datetime = s; }
    public void setDistance(String s) { distance = s; }
    public void setSpendtime(String s) { spendtime = s; }
    public void setSpeed(String s) { speed = s; }
}
