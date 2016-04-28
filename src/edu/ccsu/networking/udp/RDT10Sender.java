package edu.ccsu.networking.udp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.System.arraycopy;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple sender, takes passed data breaks it into packets and sends them to the
 * receiver
 *
 * @author Chad Williams
 */
public class RDT10Sender {

    private int receiverPortNumber = 0;
    private DatagramSocket socket = null;
    private InetAddress internetAddress = null;
    private final int packetDataSize = 123;
    private final byte[] ack1 = "1".getBytes();
    private final byte[] ack0 = "0".getBytes();
    private byte[] senderack = "0".getBytes();
    private byte[] ack = new byte[1];
    private File[] files;
    private ArrayList<FileUpload> fileUploadList;
    private final int PORT = 2010;

    public RDT10Sender() {
    }

    public void startSender(byte[] targetAddress, int receiverPortNumber) throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        internetAddress = InetAddress.getByAddress(targetAddress);
        this.receiverPortNumber = receiverPortNumber;
    }

    public void stopSender() {
        if (socket != null) {
            socket.close();
        }
    }
    /**
     * Receive data and pass it to the current state
     *
     * @param data
     * @param methodName
     * @throws java.net.SocketException
     * @throws java.lang.InterruptedException
     */
    public void rdtSend(byte[] data, String methodName) throws SocketException, IOException, InterruptedException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        int packetNumber = 0;
        Timer timer = new Timer();

        while (byteStream.available() > 0) {
            byte[] packetData = new byte[packetDataSize];
            int bytesRead = byteStream.read(packetData);

            if (bytesRead < packetData.length - 1) {
                packetData = Arrays.copyOf(packetData, bytesRead);
            }
            byte[] packetDataWMethod = add(packetData, methodName);
            //Adding Ack to the Data in the end of Packet.
            byte[] modPacketData = addAckToData(senderack, packetDataWMethod);
            DatagramPacket packet = new DatagramPacket(modPacketData, modPacketData.length, internetAddress, receiverPortNumber);
            System.out.println("### Sender sending packet: " + new String(packetData) + "'");
            boolean sending = true;
            
            while (sending) {
                try {
                    socket.send(packet);
                    //Start the timer to capture sample RTT.
                    timer.startTimer(); 
                    //Gets the time Out interval (dynamic)
                    socket.setSoTimeout((int)timer.getTimeOutInterval());
                    // Minor pause for easier visualization only
                    //Thread.sleep(1200);
                    if (receiveAck()) {
                        //If we got the ack then stop the timer to collect the sample.
                        timer.stopTimer();
                        // Update the time out interval for the second trip to send.
                        timer.updateTimeOutInterval();
                        sending = false;                   
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("We got a time out for packet: " + new String(packet.getData()));
                    System.out.println("Resending...");
                    continue;
                }
            }
        }
        System.out.println("### Sender done sending");
        senderack = ack0;
    }

    public byte[] addAckToData(byte[] ack, byte[] packet) {
        byte[] packetData = new byte[packet.length + 1];
        for (int i = 0; i < packet.length; i++) {
            packetData[i] = packet[i];
        }
        packetData[packetData.length - 1] = ack[0];
        return packetData;
    }

    public boolean receiveAck() throws IOException {
        DatagramPacket receiveack = new DatagramPacket(ack, ack.length);
        socket.receive(receiveack);
        byte[] receivingAck = receiveack.getData();
        System.out.println("Got Ack From Receiver: " + new String(receivingAck));
        if (new String(receivingAck).equals(new String(senderack))) {
            if (new String(senderack).equals("0")) {
                senderack = ack1;
            } else {
                senderack = ack0;
            }

            return true;
        }

        return false;
    }

    public byte[] add(byte[] rawPacketData, String methodName) {
        String modDataString = new String (rawPacketData); 
        modDataString  = methodName + "*" + modDataString;
        return modDataString.getBytes();
    }
    
    public String getHost() throws UnknownHostException {
            return InetAddress.getLocalHost().toString() + "*" + PORT;
    }

    public void SetFilesToSend(File[] files) {
        this.files = new File[files.length];
        this.files = files;
    }

    public void SyncFilesToServer(byte[] serverAddress) throws UnknownHostException, IOException, SocketException {
        fileUploadList = new ArrayList<>();
        for (File f : files) {
            FileUpload file;
            file = new FileUpload(f.getName(), (int)f.length());
            fileUploadList.add(file);
        }
        String rawData = makeRawPacketFileData(fileUploadList);
        try {
            rdtSend(rawData.getBytes(), "add");
        } catch (InterruptedException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Creates raw string of file names and file sizes 
     * seperated by a ! and @ respectively and
     * ending with \r\n
     * @param filesToSend
     * @return String of file names and sizes.
     * @throws UnknownHostException 
     */
    private String makeRawPacketFileData(ArrayList<FileUpload> filesToSend){
        String fileData = "";
        for(FileUpload f : filesToSend) {
            /**
             * ! is the delimeter for end of file name and
             * @ is the delimeter for end of file size 
             * and all its data for it.
             */
            fileData += f.getFileName() +"!"+ (int)f.getFileSize() +"@";
        }
        //Ending for total files data.
        fileData += "\r\n";
        return fileData;
    }
    
    /**
     * Kill method that kills the connection from 
     * the client. The directory server 
     * kills all the data it has
     * from this client.
     */
    public void Kill() {
        try {
            rdtSend(makePacketKillConnection().getBytes(), "kill");
        } catch (UnknownHostException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(RDT10Sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String makePacketKillConnection() throws UnknownHostException {
        String hostAdd = getHost();
        
        return hostAdd;
    }
}
