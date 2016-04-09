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

    HashMap<String, ArrayList<FileUpload>> fileList;
    
    public DirectoryServer() {
    }

    public ArrayList<FileUpload> add(FileUpload file) {
        String songName = file.getSongName();
        ArrayList<FileUpload> f = fileList.get(songName);
        
        //if f is null then no mapping exists for that songName so create one
        if (f == null){
            f = new ArrayList<>();
            f.add(file);
            return fileList.put(songName, f);
        }
        else{
            f.add(file);
            return fileList.put(songName, f);
        }
    }
    
    public  ArrayList<FileUpload> remove(FileUpload file) {
        return fileList.remove(file.getSongName());
    }
    public Collection<ArrayList<FileUpload>> ListAll()  {
        return fileList.values();
        
    }
    
    public void List(String fileName) {
        
    }
}
