package edu.ccsu.networking.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simple receiver thread that starts up and endlessly listens for packets on
 * the specified and delivers them. Recall this simple implementation does not
 * handle loss or corrupted packets.
 *
 * @author Chad Williams
 */
public class RDT10Receiver extends Thread {

    private int port;
    private DatagramSocket receivingSocket = null;
    private byte[] packetData;
    private String finalData = "";

    public RDT10Receiver(String name, int port) {
        super(name);
        this.port = port;
    }

    public void stopListening() {
        if (receivingSocket != null) {
            receivingSocket.close();
        }
    }

    public void deliverData(byte[] data) {
        String endPacket = new String(data);
        if (endPacket.endsWith("\r\n'")) {
            finalData += new String(data);
            System.out.println("@@@ Receiver delivered packet with: '" +finalData + "'");
            finalData = ""; // Resetting my whole 
        } else {
            try {
                finalData += new String(data);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    /**
     * Start the thread to begin listening
     */
    @Override
    public void run() {
        try {
            receivingSocket = new DatagramSocket(49000);
            while (true) {
                System.out.println("@@@ Receiver waiting for packet");
                byte[] buf = new byte[128];
                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                receivingSocket.receive(packet);
                packetData = Arrays.copyOf(packet.getData(), packet.getLength());
                deliverData(packetData);
            }
        } catch (SocketTimeoutException e) {
            stopListening();

        } catch (SocketException ex) {
            Logger.getLogger(RDT10Receiver.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RDT10Receiver.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
