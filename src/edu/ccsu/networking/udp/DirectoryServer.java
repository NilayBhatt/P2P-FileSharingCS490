/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilay Bhatt
 */
public class DirectoryServer implements DataHandler {

    private List<FileUpload> fileList;
    private RDT10Sender serverSender;
    private RDT10Receiver serverReceiver;
    private String clientAddress;
    private int clientPort;
    private int serverPort;
    private Thread receiverThread;

    public DirectoryServer() {
        
    }
    
    public void startSender(String clientAddress, int clientPort, int serverPort){
        try {
            this.clientAddress = clientAddress;
            this.clientPort = clientPort;
            this.serverPort = serverPort;
            serverSender = new RDT10Sender(serverPort);
            serverSender.startSender(clientAddress, clientPort);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void startReceiver(int serverPort) {
        this.serverPort = serverPort;
        serverReceiver = new RDT10Receiver(this);
        serverReceiver.setPort(serverPort);
        receiverThread = new Thread(serverReceiver);
        receiverThread.start();
    }

    public boolean add(FileUpload file) {
        if (fileList == null || fileList.isEmpty()) {
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

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    @Override
    public void deliverData(String data, String method, String hostAddress, int hostPort) {
        clientPort = hostPort;
        clientAddress = hostAddress;
        if(method.contains("add")) {
            //filename!fileSize
            String []fileData = data.split("@");
            for(String s : fileData){
                String [] file = s.split("!");
                FileUpload fileObject = new FileUpload(file[0], Integer.parseInt(file[1]));
                fileObject.setHostAddress(hostAddress);
                fileObject.setPort(hostPort);
                add(fileObject);
            }
        }
    }
    
    public String makePacketFileList() {
        String fileString = "";
        for(FileUpload file : fileList) {
            fileString += file.toString();
        }
        return fileString +"\r\n";
    }
    
    public void deliverList(String data) {
    }

    @Override
    public void queryData(String data, String method, String hostAddress) {
        if(method.contains("qur")) {
            if(fileList == null || fileList.isEmpty()){
                
            }else {
                String files = makePacketFileList();
                try {
                    hostAddress = hostAddress.replace("/","");
                    startSender(hostAddress, 3010, 6010);
                    serverSender.rdtSend(files.getBytes(), "lst");
                } catch (IOException ex) {
                    Logger.getLogger(DirectoryServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DirectoryServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
    
}
