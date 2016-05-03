/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.FileSharing.tcp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    
    String clientAddress;
    
    int port = 2010;
    
    private byte[] data;
    
    private byte[] httpStatus;
    
    private long fileLength;
    
    private String fileName;
    
    private String serverAddress;
    
    private File file;
    
    private FileInputStream fileIS;
    
    private BufferedInputStream bufferIS;
    
    private OutputStream outputStream;
    
    public TcpServer(String clientAddress, int port, String fileName) {
        this.clientAddress = clientAddress;
        this.port = port;
        this.fileName = fileName;
    }   
    
    public void run() {
           
        try {
            // create a socket

                ServerSocket serverSocket = new ServerSocket(port);
                Socket clientSocket = serverSocket.accept();
                

                // set the address
                InetAddress address = InetAddress.getByName(clientAddress);

                // set the file
                try {
                    file = new File(fileName);
                    fileIS = new FileInputStream(file);
                    bufferIS = new BufferedInputStream(fileIS);
                    
                // set up the output stream to client
                outputStream = clientSocket.getOutputStream();
                
                // get file length
                fileLength = file.length();

                // for tracking amount of file already sent intialize to 0 to start
                long sentData = 0;

                // flag for sending status code
                boolean flag = false;
  
                while(sentData != fileLength) {
                    int sendSize = BUFFER_SIZE;

                    // check the amout of data sent so far send next chuck or rest of file
                    if(fileLength - sentData >= sendSize) {
                        sentData += sendSize;
                    } else {
                        sendSize = (int) (fileLength - sentData);
                        sentData = fileLength;
                    }

                    // send the status code of OK
                    if (!flag) {
                        httpStatus = Integer.toString(200).getBytes();
                        outputStream.write(httpStatus);
                        flag = true;
                    }   
                    // send the new portion of the file
                    data = new byte[sendSize];
                    bufferIS.read(data, 0, sendSize);
                    outputStream.write(data);

                } 
                    
                    
                // if file not found
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    httpStatus = Integer.toString(404).getBytes();
                    outputStream.write(httpStatus);
                }



                outputStream.flush();
                clientSocket.close();
                serverSocket.close();
            
  
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
