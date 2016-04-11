package edu.ccsu.networking.udp;

import java.io.ByteArrayInputStream;
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
    private final int packetDataSize = 16;
    private final int checksumSize = 4;
    
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
     * @throws java.net.SocketException
     * @throws java.lang.InterruptedException
     */
    public void rdtSend(byte[] data) throws SocketException, IOException, InterruptedException {
        // Just as an example assume packet size is a maximum of 256 bytes
        // Actual ethernet packet size is 1500 bytes, so any message
        // larger than that would have to be split across packets

        // For simplicity using a stream to read off packet size chunks
//        List<Byte> modData = new ArrayList<>();
//        
//        for(byte b: data) {
//            modData.add(b);
//        }
//        
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        int packetNumber = 0;
        byte[] ack1 = "1".getBytes();
        byte[] ack0 = "0".getBytes();
        byte[] senderack = "0".getBytes();
        byte[] ack = new byte[1];
        
        // Get a new timer for measuring RTT
        Timer timer = new Timer();
        
        while (byteStream.available() > 0) {
            byte[] packetData = new byte[packetDataSize];
            int bytesRead = byteStream.read(packetData);
            
            if (bytesRead < packetData.length - 1) {
                packetData = Arrays.copyOf(packetData, bytesRead);
            }
            //byte[] intbytes = ByteBuffer.allocate(4).putInt(5).array();
            //List<Byte> modData = new ArrayList<>();       
            System.arraycopy(senderack, 0, packetData, packetData.length - 1, senderack.length);
            
            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, internetAddress, receiverPortNumber);

//            for (byte b : packetData) {
//                modData.add(b);
//            }
//            for (byte b : intbytes) {
//                modData.add(b);
//            }
            //byte[] mod_packetData = (byte[])modData.toArray();
            System.out.println("### Sender sending packet: " + new String(packetData) + "'");
            try {
                socket.send(packet);
                socket.setSoTimeout(100);
                // Start timer for getting elapsed time
                timer.startTimer();
                
                // Minor pause for easier visualization only
                //Thread.sleep(1200);
                DatagramPacket receiveack = new DatagramPacket(ack, ack.length);
                socket.receive(receiveack);
                byte[] receivingAck = receiveack.getData();
                
// Stop timer for elapse time      
//                timer.getTime()
                System.out.println("Got Ack From Receiver: " + new String(receivingAck));
                if (new String(receivingAck).equals(new String(senderack))) {
                    // stop timer
                    
                    if (new String(senderack).equals("0")) {
                        senderack = ack1;
                    } else {
                        senderack = ack0;
                    }
                }
            } catch (SocketTimeoutException e) {
                
                // Stop timer for elapse time with timeout case
                timer.timerTimeOut();

                System.out.println("We got a time out for packet: " + new String(packet.getData()));
                socket.send(packet);
                // setSoTimeOut to new timeout interval                 
                socket.setSoTimeout(Timer.longToInt(timer.getTimeOutInterval()));
                timer.startTimer();              
            }
            
        }
        
        System.out.println(
                "### Sender done sending");
    }
}
