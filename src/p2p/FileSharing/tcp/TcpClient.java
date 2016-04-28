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

    // hard coded local address + port for testing now
    byte[] serverAddress = {127, 0, 0, 1};
    int port = 2010;

    public TcpClient(String name, int port) {
        super(name);
        this.port = port;
    }

    
    public void run() {
        
        // set up socket
        try {
            Socket socket = new Socket(InetAddress.getByName("localHost"), port);
            
            // set data to recieve
            byte[] data = new byte[BUFFER_SIZE];
            
            // get file & file output stream to write to file from incoming data
            // hard coded file for testing.
            File file = new File("//Users//Travis//TestNETWORKING2");
            FileOutputStream fileOS = new FileOutputStream(file);
            BufferedOutputStream bufferedOS = new BufferedOutputStream(fileOS);
            InputStream inputStream = socket.getInputStream();
            
            // track amount of data read
            int receivedData = 0;
            
            // while there is data to read, read it
            while((receivedData = inputStream.read(data)) != -1 ) {
                // write the data to file
                bufferedOS.write(data, 0, receivedData);
            }
            
            bufferedOS.flush();
            socket.close();

        } catch (IOException ex) {
                Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);

        }
    
    }
}
