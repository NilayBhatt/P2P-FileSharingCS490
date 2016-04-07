package edu.ccsu.networking.udp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
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
        while (byteStream.available() > 0) {
            byte[] packetData = new byte[packetDataSize];
            int bytesRead = byteStream.read(packetData);
            
            if (bytesRead < packetData.length) {
                packetData = Arrays.copyOf(packetData, bytesRead);
            }
            byte[] intbytes = ByteBuffer.allocate(4).putInt(5).array();
            List<Byte> modData = new ArrayList<>();
            
            for (byte b : packetData) {
                modData.add(b);
            }
            for (byte b : intbytes) {
                modData.add(b);
            }
            //byte[] mod_packetData = (byte[])modData.toArray();
            
            
            //System.out.println("### Sender sending packet(" + new String((packetNumber++) + ")") + ": '" + new String(packetData) + "'");
            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, internetAddress, receiverPortNumber);
            socket.setSoTimeout(10000);
            socket.send(packet);
            // Minor pause for easier visualization only
            //Thread.sleep(1200);

        }
        System.out.println("### Sender done sending");
    }
}
