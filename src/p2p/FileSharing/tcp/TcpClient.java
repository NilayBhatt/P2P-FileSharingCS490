/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.FileSharing.tcp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Travis
 */
public class TcpClient extends Thread {
    
    final int BUFFER_SIZE =  8192; // 8k bytes of buffer 
    private String fileName;

    private String clientAddress;
    
    private String serverAddress;
//    // hard coded local address + port for testing now
//    byte[] serverAddress = {127, 0, 0, 1};
    int port = 2010;

    public TcpClient(String serverAddress, int port) {
        super(serverAddress);
        this.port = port;
    }

    
    public void run() {
        
        // set up socket
        try {
            Socket socket=  new Socket(InetAddress.getByName(serverAddress), port);
            
            // set data to recieve
            byte[] data = new byte[BUFFER_SIZE];
            
            byte[] httpStatus = new byte[Integer.SIZE / 8];
            
            // get file & file output stream to write to file from incoming data
            // hard coded file for testing.
            
            File file = new File("//Users/Travis/SomeNewFile");
            FileOutputStream fileOS = new FileOutputStream(file);
            BufferedOutputStream bufferedOS = new BufferedOutputStream(fileOS);
            InputStream inputStream = socket.getInputStream();
            
            // track amount of data read
            int receivedData = 0;
            
            boolean flag = false;
            // while there is data to read, read it
            
            if (!flag) {
                try {
                receivedData = inputStream.read(httpStatus, 0, (Integer.SIZE / 8) -1 );
                flag = true;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            String httpString = new String(httpStatus).trim();
            
            switch (Integer.parseInt(httpString)) {
                    case 200:
                        // OK - so read file
                        try {
                            while((receivedData = inputStream.read(data)) != -1 ) {
                                // write the data to file
                                bufferedOS.write(data, 0, receivedData);
                            }
                        } catch (IOException ex) {
                              Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);

                        }
                        try {
                        bufferedOS.flush();
                          socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                     break;
//                     
//                    case "400":
//                        // bad request
//                        
//                        break;
                        
                    case 404:
                        // file not found
                        System.out.println("File not found");
                        
                        break;
                        
                    case 505:
                        // HTTP Version Not Supported
                        System.out.println("HTTP version not supported");
                        
                        break;
                        
                    default: 
                        // bad request 400
                        System.out.println("Bad Request");
                     
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            
            
    
    }
    
    public String getFileName() {
        return "User/Travis/C1-3.txt";
    }
    
    public String setFileName(String s) {
        this.fileName = s;
        return this.fileName;
    }
    
    public String getClientAddress() {
        return this.clientAddress;
    }
    
    public void setClientAddress(String hostAddress) {
        this.clientAddress = hostAddress;
    }
}
