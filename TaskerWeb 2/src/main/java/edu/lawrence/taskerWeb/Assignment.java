/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;

/**
 *
 * @author nafisahmedmunim
 */
public class Assignment {
   private int idassignments;
   private int job;
   private int worker;
   
   public Assignment() {}

    public int getidassignment() {
        return idassignments;
    }

    public void setidassignment(int idassignments) {
        this.idassignments = idassignments;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }


   
}

