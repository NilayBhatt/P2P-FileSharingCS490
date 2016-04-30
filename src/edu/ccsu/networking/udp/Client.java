/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ccsu.networking.udp;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nilay Bhatt
 */
public class Client implements DataHandler{

    private File[] files;
    private ArrayList<FileUpload> fileUploadList;
    private RDT10Sender sender;
    private String serverAddress;
    private int serverReceiverPort;
    private RDT10Receiver receiver;
    private int clientReceiverPort;
    private int clientSenderPort;
    private Thread receiverThread;

    public Client() {
    }

    public RDT10Sender getSender() {
        return sender;
    }

    public void setSender(RDT10Sender sender) {
        this.sender = sender;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getServerReceiverPort() {
        return serverReceiverPort;
    }

    public void setServerReceiverPort(int serverReceiverPort) {
        this.serverReceiverPort = serverReceiverPort;
    }

    public RDT10Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(RDT10Receiver receiver) {
        this.receiver = receiver;
    }

    public int getClientReceiverPort() {
        return clientReceiverPort;
    }

    public void setClientReceiverPort(int clientReceiverPort) {
        this.clientReceiverPort = clientReceiverPort;
    }

    public int getClientSenderPort() {
        return clientSenderPort;
    }

    public void setClientSenderPort(int clientSenderPort) {
        this.clientSenderPort = clientSenderPort;
    }

    public Thread getReceiverThread() {
        return receiverThread;
    }

    public void setReceiverThread(Thread receiverThread) {
        this.receiverThread = receiverThread;
    }

    public void startClientSender() {
        try {
            sender = new RDT10Sender(clientSenderPort);
            sender.startSender(serverAddress, serverReceiverPort);
        } catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void startClientReceiver(int clientReceiverPort) {
        receiver = new RDT10Receiver(this);
        receiver.setPort(clientReceiverPort);
        receiverThread = new Thread(receiver);
        receiverThread.start();
    }

    public void SetFilesToSend(File[] files) {
        this.files = new File[files.length];
        this.files = files;
    }

    public void SyncFilesToServer() throws UnknownHostException, IOException, SocketException {
        fileUploadList = new ArrayList<>();
        for (File f : files) {
            FileUpload file;
            file = new FileUpload(f.getName(), (int) f.length());
            fileUploadList.add(file);
        }
        String rawData = makeRawPacketFileData(fileUploadList);
        try {
            sender.rdtSend(rawData.getBytes(), "add");
        } catch (InterruptedException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates raw string of file names and file sizes seperated by a ! and @
     * respectively and ending with \r\n
     *
     * @param filesToSend
        * @return String of file names and sizes.
     * @throws UnknownHostException
     */
    private String makeRawPacketFileData(ArrayList<FileUpload> filesToSend) {
        String fileData = "";
        for (FileUpload f : filesToSend) {
            /**
             * ! is the delimeter for end of file name and
             *
             * @ is the delimeter for end of file size and all its data for it.
             */
            fileData += f.getFileName() + "!" + (int) f.getFileSize() + "@";
        }
        //Ending for total files data.
        fileData += "\r\n";
        return fileData;
    }

    /**
     * Kill method that kills the connection from the client. The directory
     * server kills all the data it has from this client.
     */
    public void Kill() {
        try {
            sender.rdtSend(sender.getHost().getBytes(), "kill");
        } catch (UnknownHostException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File[] getFiles() {
        return files;
    }

    public ArrayList<FileUpload> getFileUploadList() {
        return fileUploadList;
    }

    public void setFileUploadList(ArrayList<FileUpload> fileUploadList) {
        this.fileUploadList = fileUploadList;
    }

    public void deliverData(String data, String method, String hostAddress, int hostPort) {
    }
}
