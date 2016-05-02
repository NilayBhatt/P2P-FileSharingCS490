/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

/**
 *
 * @author Nilay Bhatt
 */
public interface DataHandler {
    
    public void deliverData(String data, String method, String hostAddress, int hostPort);
    public void deliverList(String data);
    public void queryData (String data, String method, String hostAddress);
    public void requestFile(String data, String hostAddress, String port);
    public void killConnection(String hostAddress);
}
