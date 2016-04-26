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
 * Timer that starts and stops when packet is sent and ack is received. 
 */
public class Timer {
    
    private static final float BETA = 0.25f;           
    private static final float ONE_MINUS_BETA = 0.75f;
    private static final float ALPHA = 0.125f;    
    private static final int DEV_WEIGHT = 4;    
    private long timeOutInterval;    
    private long sampleRTT;    
    private long estimatedRTT;    
    private float devRTT;    
    private long startTime;
    
    public Timer() {
        // set up initial values
        timeOutInterval = 1000;
        estimatedRTT = 900;
        sampleRTT = estimatedRTT;
        devRTT = 0;
        startTime = 0;
    }
    
    public long getStartTime() {
        return this.startTime;
    }
    
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }
    
    public void stopTimer() {
        this.sampleRTT = (System.currentTimeMillis() - this.startTime);
        this.startTime = 0;
    }
    
    public void timerTimeOut() {
        this.timeOutInterval = 2 * this.getTimeOutInterval();
        
    }
    
    public long updateTimeOutInterval() {
        long updatedEstRTT = updateEstimatedRTT();
        float updatedDevRTT = updateDevRTT();
       return this.timeOutInterval = (long) ceil(this.getEstimatedRTT() + 
                (DEV_WEIGHT * this.devRTT));
    }
    
    public float updateDevRTT() {
        return this.devRTT = (ONE_MINUS_BETA) * this.devRTT + 
                (BETA *  abs((float) this.sampleRTT - (float) this.estimatedRTT));
    }
    
    public long updateEstimatedRTT() {
        return this.estimatedRTT = (long) (ceil((ONE_MINUS_BETA) * 
                (float)this.estimatedRTT)) + 
                (long) (ceil((BETA) * (float) this.sampleRTT));
    }

    public long getTimeOutInterval() {
        return timeOutInterval;
    }

    public void setTimeOutInterval(long timeOutInterval) {
        this.timeOutInterval = timeOutInterval;
    }

    public long getSampleRTT() {
        return sampleRTT;
    }

    public void setSampleRTT(long sampleRTT) {
        this.sampleRTT = sampleRTT;
    }

    public long getEstimatedRTT() {
        return estimatedRTT;
    }

    public void setEstimatedRTT(long estimatedRTT) {
        this.estimatedRTT = estimatedRTT;
    }

    public float getDevRTT() {
        return devRTT;
    }

    public void setDevRTT(float devRTT) {
        this.devRTT = devRTT;
    }
    
    
    /**
     * Needed because system time for timer is long, and setSoTimeout takes int
     * @param value
     * @return intager value of the long passed in, or the max int value but this
     * will probably never be need, but its here.
     * @throws IllegalArgumentException 
     */
    public static int longToInt(long value) throws IllegalArgumentException  {
    if (value > Integer.MAX_VALUE) {
        return Integer.MAX_VALUE;
    } else {
        return (int) value;
    }
}  
    
}
