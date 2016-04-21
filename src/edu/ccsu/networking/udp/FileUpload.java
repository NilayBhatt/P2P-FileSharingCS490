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
    private long fileSize = 0;

    public FileUpload(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    
    public String toString() {
        String s = fileName + "*" + fileSize + "\r\n";
        
        return s;
    }
    
}