/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.FileSharing.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author NNB0930
 */
public class TcpClient {

    private final int CLIENT_PORT = 3010;
    private final int SERVER_PORT = 4010;
    private final String downloadPath = "C:\\";
    private String fileName;
    private int fileSize = 0; 
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }
    
    public TcpClient() {
    }
    
    public void  ReceiveFile() {
        
    }
    
    public void ConnectAndRequestFile(String fileName, byte[] p2pServerAddress, int port) throws IOException{
        int bytesToRead;
        
    }
    
    public void DownloadFile() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        Socket socket = serverSocket.accept();
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
    }

}
