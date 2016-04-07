/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 *
 * @author Nilay Bhatt
 */
public class DirectoryServer {

    private List<FileUpload> fileList;

    public DirectoryServer() {
    }

    public boolean add(FileUpload file) {
        if (fileList.isEmpty() || fileList == null) {
            fileList = new ArrayList<>();
            return fileList.add(file);
        } else {
            return fileList.add(file);
        }
    }
    
    public boolean remove(FileUpload file) {
        return fileList.remove(file);
    }
    public List<FileUpload> ListAll()  {
        return fileList;
        
    }
    
    public void List(String fileName) {
        
    }
}
