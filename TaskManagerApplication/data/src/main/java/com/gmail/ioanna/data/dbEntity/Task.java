package com.gmail.ioanna.data.dbEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {

    private String name;
    private int percentOfCompletion;
    private String state;
    private int estimatedTime;
    private Date startDate;
    private Date dueDate;

    public Task() {
    }

    public Task(String name, int percentOfCompletion, String state, int estimatedTime, Date startDate, Date dueDate) {
        this.name = name;
        this.percentOfCompletion = percentOfCompletion;
        this.state = state;
        this.estimatedTime = estimatedTime;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentOfCompletion() {
        return percentOfCompletion;
    }

    public void setPercentOfCompletion(int percentOfCompletion) {
        this.percentOfCompletion = percentOfCompletion;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) throws ParseException {
        this.startDate = parse(startDate);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) throws ParseException {
        this.dueDate = parse(dueDate);
    }

    public Date parse(String date) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        return format.parse(date);
    }
}
