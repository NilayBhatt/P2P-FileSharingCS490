/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

/**
 * File Upload Object class, helps 
 * us update items  in the 
 * Directory Server.
 * 
 * @author Nilay Bhatt
 */
public class FileUpload {

    private String fileName = "";
    private String songName = "";
    private int fileSize = 0;
    private int hostAddress = 0;
    private int hostPort = 0;

    public FileUpload(String songName, String fileName, int fileSize, int hostAddress, int hostPort) {
        this.songName = songName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.hostAddress = hostAddress;
        this.hostPort = hostPort;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public int getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(int hostAddress) {
        this.hostAddress = hostAddress;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public String getFileName() {
        return fileName;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public String getSongName() {
        return songName;
    }
    
    public void setSongName(String songName){
        this.songName = songName;
    }
}
