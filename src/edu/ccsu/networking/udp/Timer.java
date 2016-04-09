/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

import static java.lang.Math.abs;
import static java.lang.Math.ceil;

/**
 *
 * @author Travis Moretz on 4/8/16
 * 
 * Timer class to run on separate thread
 */
public class Timer extends Thread {
    
    private static final float BETA = 0.25f;        
    
    private static final float ONE_MINUS_BETA = 0.75f;

    private static final float ALPHA = 0.125f;
    
    private static final int DEV_WEIGHT = 4;
    
    private int timeOutInterval;
    
    private int sampleRTT;
    
    private int estimatedRTT;
    
    private float devRTT;
    
    public Timer() {
        // set up initial values
        timeOutInterval = 2000;
        estimatedRTT = 2000;
        sampleRTT = estimatedRTT;
        devRTT = 0;
    }
    
    public int updateTimeOutInterval() {
       return this.timeOutInterval = (int) ceil(this.getEstimatedRTT() + 
                (DEV_WEIGHT * this.devRTT));
    }
    
    public float updateDevRTT() {
        return this.devRTT = (ONE_MINUS_BETA) * this.devRTT + 
                (BETA *  abs((float) this.sampleRTT - (float) this.estimatedRTT));
    }
    
    public int updateEstimatedRTT() {
        return this.estimatedRTT = (int) (ceil((ONE_MINUS_BETA) * 
                (float)this.estimatedRTT)) + 
                (int) (ceil((BETA) * (float) this.sampleRTT));
    }

    public int getTimeOutInterval() {
        return timeOutInterval;
    }

    public void setTimeOutInterval(int timeOutInterval) {
        this.timeOutInterval = timeOutInterval;
    }

    public int getSampleRTT() {
        return sampleRTT;
    }

    public void setSampleRTT(int sampleRTT) {
        this.sampleRTT = sampleRTT;
    }

    public int getEstimatedRTT() {
        return estimatedRTT;
    }

    public void setEstimatedRTT(int estimatedRTT) {
        this.estimatedRTT = estimatedRTT;
    }

    public float getDevRTT() {
        return devRTT;
    }

    public void setDevRTT(float devRTT) {
        this.devRTT = devRTT;
    }
    
    
    
    
}
