///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.ccsu.networking.udp;
//
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import java.lang.Thread;
//
///**
// *
// * @author Travis
// * 
// *  * testing done with values set to estRTT = sampRTT = timeOut = 2000 
// */
//public class TimerTest {
//    
//    public TimerTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    // TODO add test methods here.
//    // The methods must be annotated with annotation @Test. For example:
//    //
//    // @Test
//    // public void hello() {}
//
//    /**
//     * Test of updateTimeOutInterval method, of class Timer.
//     */
//    @Test
//    public void testUpdateTimeOutInterval() {
//        System.out.println("updateTimeOutInterval");
//        Timer instance = new Timer();
//        long expResult = 2000;
//        long result = instance.updateTimeOutInterval();
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void testUpdateTimeOutInterval2() {
//        System.out.println("updateTimeOutInterval");
//        Timer instance = new Timer();
//        instance.setDevRTT(0.63F);
//        instance.setEstimatedRTT(1245);
//        long expResult = 1248;
//        long result = instance.updateTimeOutInterval();
//        assertEquals(expResult, result);
//    }
//    /**
//     * Test of updateDevRTT method, of class Timer.
//     */
//    @Test
//    public void testUpdateDevRTT() {
//        System.out.println("updateDevRTT");
//        // estimated and sampleRTT = 2000 devRTT = 0
//        Timer instance = new Timer();
//        float expResult = 0.0F;
//        float result = instance.updateDevRTT();
//        assertEquals(expResult, result, 0.0);
//    }
//    @Test
//    public void testUpdateDevRTT2() {
//        System.out.println("updateDevRTT");
//        // estimatedRTT = 1234 ; sampleRTT = 2450 ; devRTT = .2
//        Timer instance = new Timer();
//        instance.setDevRTT(0.2f);
//        instance.setEstimatedRTT(1234);
//        instance.setSampleRTT(2450);
//        float expResult = 304.15F;
//        float result = instance.updateDevRTT();
//        assertEquals(expResult, result, 0.0);
//    }
//
//    /**
//     * Test of updateEstimatedRTT method, of class Timer.
//     */
//    @Test
//    public void testUpdateEstimatedRTT() {
//        System.out.println("updateEstimatedRTT");
//        // start value estimatedRTT=2000; sampleRTT=2000
//        Timer instance = new Timer();
//        long expResult = 2000;
//        long result = instance.updateEstimatedRTT();
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void testUpdateEstimatedRTT2() {
//        System.out.println("updateEstimatedRTT");
//        // start value estimatedRTT=1200; sampleRTT=4500
//        Timer instance = new Timer();
//        instance.setEstimatedRTT(1200);
//        instance.setSampleRTT(4500);
//        long expResult = 2025;
//        long result = instance.updateEstimatedRTT();
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testGetTimeOutInterval() {
//        System.out.println("getTimeOutInterval");
//        Timer instance = new Timer();
//        long expResult = 34567;
//        instance.setTimeOutInterval(expResult);
//        long result = instance.getTimeOutInterval();
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void testGetTimeOutInterval2() {
//        System.out.println("getTimeOutInterval");
//        Timer instance = new Timer();
//        long expResult = 34567;
//        long notResult = 4567;
//        long result = instance.getTimeOutInterval();
//        assertNotEquals(notResult, result);
//    }
//
//    /**
//     * Test of setTimeOutInterval method, of class Timer.
//     */
//    @Test
//    public void testSetTimeOutInterval() {
//        System.out.println("setTimeOutInterval");
//        long timeOutInterval = 3456;
//        Timer instance = new Timer();
//        instance.setTimeOutInterval(timeOutInterval);
//        assertEquals(timeOutInterval, instance.getTimeOutInterval());
//    }
//    @Test
//    public void testSetTimeOutInterval2() {
//        System.out.println("setTimeOutInterval");
//        long timeOutInterval = 3456;
//        long notInterval = 5636;
//        Timer instance = new Timer();
//        instance.setTimeOutInterval(timeOutInterval);
//        assertNotEquals(notInterval, instance.getTimeOutInterval());
//    }
//
//    /**
//     * Test of getSampleRTT method, of class Timer.
//     */
//    @Test
//    public void testGetSampleRTT() {
//        System.out.println("getSampleRTT");
//        Timer instance = new Timer();
//        long expResult = 2000;
//        long result = instance.getSampleRTT();
//        assertEquals(expResult, result);
//        
//    }
//        @Test
//    public void testGetSampleRTT2() {
//        System.out.println("getSampleRTT");
//        Timer instance = new Timer();
//        long expResult = 3000;
//        long result = instance.getSampleRTT();
//        assertNotEquals(expResult, result);
//        
//    }
//        @Test
//    public void testGetSampleRTT3() {
//        System.out.println("getSampleRTT");
//        Timer instance = new Timer();
//        long expResult = 21;
//        long result = instance.getSampleRTT();
//        assertNotEquals(expResult, result);
//        
//    }
//
//    /**
//     * Test of setSampleRTT method, of class Timer.
//     */
//    @Test
//    public void testSetSampleRTT() {
//        System.out.println("setSampleRTT");
//        long sampleRTT = 56;
//        Timer instance = new Timer();
//        instance.setSampleRTT(sampleRTT);
//        assertEquals(sampleRTT, instance.getSampleRTT());
//
//    }
//        public void testSetSampleRTT2() {
//        System.out.println("setSampleRTT");
//        long sampleRTT = 3040;
//        Timer instance = new Timer();
//        instance.setSampleRTT(sampleRTT);
//        assertEquals(sampleRTT, instance.getSampleRTT());
//
//    }
//
//    /**
//     * Test of getEstimatedRTT method, of class Timer.
//     */
//    @Test
//    public void testGetEstimatedRTT() {
//        System.out.println("getEstimatedRTT");
//        Timer instance = new Timer();
//        long expResult = 2000;
//        long result = instance.getEstimatedRTT();
//        assertEquals(expResult, result);
//    }
//        /**
//     * Test of getEstimatedRTT method, of class Timer.
//     */
//    @Test
//    public void testGetEstimatedRTT2() {
//        System.out.println("getEstimatedRTT");
//        Timer instance = new Timer();
//        long expResult = 7;
//        long result = instance.getEstimatedRTT();
//        assertNotEquals(expResult, result);
//    }
//
//    /**
//     * Test of setEstimatedRTT method, of class Timer.
//     */
//    @Test
//    public void testSetEstimatedRTT() {
//        System.out.println("setEstimatedRTT");
//        long estimatedRTT = 4000;
//        Timer instance = new Timer();
//        instance.setEstimatedRTT(estimatedRTT);
//        long results = instance.getEstimatedRTT();
//        assertEquals(estimatedRTT, results );
//    }
//    
//        /**
//     * Test of setEstimatedRTT method, of class Timer.
//     */
//    @Test
//    public void testSetEstimatedRTT2() {
//        System.out.println("setEstimatedRTT2");
//        long estimatedRTT = 83980;
//        Timer instance = new Timer();
//        instance.setEstimatedRTT(estimatedRTT);
//        long results = instance.getEstimatedRTT();
//        assertNotEquals(4, results );
//    }
//
//    /**
//     * Test of getDevRTT method, of class Timer.
//     */
//    @Test
//    public void testGetDevRTT() {
//        System.out.println("getDevRTT");
//        Timer instance = new Timer();
//        float expResult = 0.0F;
//        float result = instance.getDevRTT();
//        assertEquals(expResult, result, 0.0);
//    }
//    @Test
//    public void testGetDevRTT2() {
//        System.out.println("getDevRTT");
//        Timer instance = new Timer();
//        float expResult = 0.345F;
//        float result = instance.getDevRTT();
//        assertNotEquals(expResult, result, 0.0);
//    }
//
//    /**
//     * Test of setDevRTT method, of class Timer.
//     */
//    @Test
//    public void testSetDevRTT() {
//        System.out.println("setDevRTT");
//        float devRTT = 0.765F;
//        Timer instance = new Timer();
//        instance.setDevRTT(devRTT);
//        assertEquals(devRTT, instance.getDevRTT(), 0.0);
//    }
//
//    /**
//     * Test of startTimer method, of class Timer.
//     */
//    @Test
//    public void testStartTimer() {
//        System.out.println("startTimer");
//        Timer instance = new Timer();
//        instance.startTimer();
//        System.out.print("Start Time: " + instance.getStartTime());
//    }
//
//    /**
//     * Test of stopTimer method, of class Timer.
//     */
//    @Test
//    public void testStopTimer() throws InterruptedException {
//        System.out.println("stopTimer");
//        Timer instance = new Timer();
//        instance.startTimer();
//        Thread.sleep(100);
//        instance.stopTimer();
//        assertEquals(100, instance.getSampleRTT());
//        assertEquals(0, instance.getStartTime());
//        
//    }
//
//    /**
//     * Test of timerTimeOut method, of class Timer.
//     */
//    @Test
//    public void testTimerTimeOut() {
//        System.out.println("timerTimeOut");
//        Timer instance = new Timer();
//        long intervalFirst = instance.getTimeOutInterval();
//        long firstTimesTwo = intervalFirst * 2;
//        instance.startTimer();
//        instance.timerTimeOut();
//        long afterInterval = instance.getTimeOutInterval();
//        assertEquals(firstTimesTwo, afterInterval);
//    }
//
//    /**
//     * Test of getStartTime method, of class Timer.
//     */
//    @Test
//    public void testGetStartTime() {
//        System.out.println("getStartTime");
//        Timer instance = new Timer();
//        long expResult = 0L;
//        long result = instance.getStartTime();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of longToInt method, of class Timer.
//     */
//    @Test
//    public void testLongToInt() {
//        System.out.println("longToInt");
//        long value = Long.MAX_VALUE;
//        int expResult = Integer.MAX_VALUE;
//        int result = Timer.longToInt(value);
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void testLongToInt2() {
//        System.out.println("longToInt2");
//        long value = 4959385l;
//        int expResult = 4959385;
//        int result = Timer.longToInt(value);
//        assertEquals(expResult, result);
//    }
//}
