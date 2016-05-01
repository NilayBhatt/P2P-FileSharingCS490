/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

import java.net.InetAddress;

/**
 * File Upload Object class, helps 
 * us update items  in the 
 * Directory Server.
 * 
 * @author Nilay Bhatt
 */
public class FileUpload {

    private String fileName = "";
    private int fileSize = 0;
    private String hostAddress;
    private int port;

    
    public FileUpload(String fileName, int fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public String toString() {
        String s = fileName + "!" + fileSize + "@" + getHostAddress() + "#" +getPort()+ "$";
        
        return s;
    }
    
}