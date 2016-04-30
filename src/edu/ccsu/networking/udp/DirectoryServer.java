/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

import java.util.*;

/**
 *
 * @author Nilay Bhatt
 */
public class DirectoryServer {

    HashMap <String, FileUpload> fileList;
    
    public DirectoryServer() {
    }

    public boolean add(FileUpload file) {
        if(file != null){
            fileList.put(file.getFileName(), file);
            return true;
        }
        else
            return false;
    }
    
    public FileUpload remove(FileUpload file) {
        return fileList.remove(file.getFileName());
    }
    public Collection<FileUpload> ListAll()  {
        return fileList.values();  
    }
    
    public void List(String fileName) {
        
    }
}
