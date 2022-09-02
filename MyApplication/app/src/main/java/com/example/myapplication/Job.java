package com.example.myapplication;

import java.util.Date;



public class Job {
    private int idjob;
    private String description;
    private Date date;
    private String location;
    private int customer;
    private int status;

    public Job() {}

    public int getIdjob() { return idjob; }
    public String getDescription() { return description; }
    public Date getDate() { return date; }
    public String getLocation() { return location; }
    public int getCustomer() { return customer; }
    public int getStatus() { return status; }

    public void setIdjob(int idjob) { this.idjob = idjob; }
    public void setSubject(String description) { this.description = description; }
    public void setDate(Date date) { this.date = date; }
    public void setLocation(String location) { this.location = location; }
    public void setQuiz(int customer) { this.customer = customer; }
    public void setStatus(int status) { this.status = status; }
}

