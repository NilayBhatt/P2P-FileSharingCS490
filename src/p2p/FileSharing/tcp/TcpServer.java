/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.FileSharing.tcp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Travis
 * 
 * TCP server delivers the file to the client after the directory server gives 
 * the address of the client
 */
public class TcpServer extends Thread {
    
    final int BUFFER_SIZE =  8192; // 8k bytes of buffer 
    
//    byte[] clientAddress = {127,0,0,1};
    int port = 2010;
    
    private byte[] data;
    
    private long fileLength;
    
    private String fileName;
    
    private String serverAddress;
    
    public TcpServer(String clientAddress, String fileName) {
        super(clientAddress);
        this.fileName = fileName;
    }   
    
    public void run() {
           
        try {
            // create a socket
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                
                Socket clientSocket = serverSocket.accept();

                // set the address
    //            InetAddress address = InetAddress.getByAddress(clientAddress);

                // set the file
                File file = new File(fileName);
                FileInputStream fileIS = new FileInputStream(file);
                BufferedInputStream bufferIS = new BufferedInputStream(fileIS);

                // set up the output stream to client
                OutputStream outputStream = clientSocket.getOutputStream();

                // get file length
                fileLength = file.length();

                // for tracking amount of file already sent intialize to 0 to start
                long sentData = 0;

                while(sentData != fileLength) {
                    int sendSize = BUFFER_SIZE;

                    // check the amout of data sent so far send next chuck or rest of file
                    if(fileLength - sentData >= sendSize) {
                        sentData += sendSize;
                    } else {
                        sendSize = (int) (fileLength - sentData);
                        sentData = fileLength;
                    }

                    // send the new portion of the file
                    data = new byte[sendSize];
                    bufferIS.read(data, 0, sendSize);
                    outputStream.write(data);

                }

                outputStream.flush();
                clientSocket.close();
                serverSocket.close();
            }
  
        } catch (IOException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String getFileName() {
        return this.fileName;
    }
    
    public void setFileName(String fileName) {
        this.fileName = fileName;
    } 
    
    public String getServerAddress() {
        return this.serverAddress;
    }
    
    public void setServerAddress() {

        try {
        this.serverAddress = InetAddress.getLocalHost().toString();
        } catch(UnknownHostException e) {
            e.printStackTrace();
        }
        
    }
    
}
