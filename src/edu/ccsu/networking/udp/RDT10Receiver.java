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
public class RDT10Receiver implements Runnable {

    private int port;
    private DatagramSocket receivingSocket = null;
    private byte[] packetData;
    private String finalData = "";
    private byte[] receiverack = "0".getBytes();
    private final byte[] receiverack0 = "0".getBytes();
    private final byte[] receiverack1 = "1".getBytes();
    private DataHandler dataHandler;

    public RDT10Receiver(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public void stopListening() {
        if (receivingSocket != null) {
            receivingSocket.close();
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void deliverData(byte[] data, String hostAddress) {
        byte[] method = new byte[4];
        System.arraycopy(data, 0, method, 0, 3);
        byte[] rawData = new byte[data.length - 4];
        System.arraycopy(data, 4, rawData, 0, rawData.length);
        String endPacket = new String(rawData);
        String stringMethod = new String(method);
        //endPacket = endPacket.replace("%", " ");
        //if(endPacket.)
        //System.out.println("So Far we have Received: " + finalData);
        finalData += endPacket;
        if (finalData.endsWith("\r\n")) {
            finalData = finalData.replace("\r\n", "");
            //finalData += endPacket;
            System.out.println("@@@ Receiver delivered packet with: '" + finalData + "'");
            if (stringMethod.contains("add")) {
                String[] temp = finalData.split("%");
                dataHandler.deliverData(temp[1], new String(method), hostAddress, Integer.parseInt(temp[0]));
            } else if(stringMethod.contains("qur")) {
                dataHandler.queryData(finalData, new String(method), hostAddress);
            } else if(stringMethod.contains("lst")) {
                dataHandler.deliverList(finalData);
            } else if(stringMethod.contains("get")){
                
            }
            // Resetting whole data to start listening again
            finalData = "";
            receiverack = receiverack0;
        }
    }

    /**
     * Start the thread to begin listening
     */
    @Override
    public void run() {
        try {
            receivingSocket = new DatagramSocket(port);

            byte[] ackin = new byte[1];
            while (true) {
                System.out.println("@@@ Receiver waiting for packet");
                byte[] buf = new byte[128];
                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                receivingSocket.receive(packet);
                packetData = Arrays.copyOf(packet.getData(), packet.getLength());
                System.out.println("Got Data: " + new String(packetData));
                if (sendAck(packetData, packet)) {
                    byte[] dataToBeDelivered = new byte[packetData.length - 1];
                    System.arraycopy(packetData, 0, dataToBeDelivered, 0, packetData.length - 1);
                    //System.out.println("Delivering Data: " + new String(dataToBeDelivered));
                    if (new String(receiverack).equals("1")) {
                        receiverack = receiverack0;
                    } else {
                        receiverack = receiverack1;
                    }
                    deliverData(dataToBeDelivered, packet.getAddress().toString());
                }
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

    public boolean sendAck(byte[] b, DatagramPacket packet) throws IOException {
        byte[] ackin = new byte[1];
        ackin[0] = packetData[packetData.length - 1];
        if (new String(receiverack).equals(new String(ackin))) {
            try {
                DatagramPacket ack = new DatagramPacket(receiverack, receiverack.length, packet.getAddress(), packet.getPort());
                receivingSocket.send(ack);
                System.out.println("Receiver Sending Ack: " + new String(receiverack));

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (new String(receiverack).equals("1")) {
                DatagramPacket ack = new DatagramPacket(receiverack0, receiverack0.length, packet.getAddress(), packet.getPort());
                receivingSocket.send(ack);
                System.out.println("Receiver Sending Ack: " + new String(receiverack0));
            } else {
                DatagramPacket ack = new DatagramPacket(receiverack1, receiverack1.length, packet.getAddress(), packet.getPort());
                receivingSocket.send(ack);
                System.out.println("Receiver Sending Ack: " + new String(receiverack0));
            }
        }

        return false;
    }
}
