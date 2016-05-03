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
    private int PORT;
    private boolean slowMode = false;

    public RDT10Sender(int PORT) {
        this.PORT = PORT;
    }

    public void startSender(String targetAddress, int receiverPortNumber, boolean slowMode) throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        internetAddress = InetAddress.getByName(targetAddress);
        this.receiverPortNumber = receiverPortNumber;
        this.slowMode = slowMode;
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
        double totalPacket = Math.ceil(data.length / 128.0) + 1;
        int packetNumber = 1;
        Timer timer = new Timer();

        while (byteStream.available() > 0) {
            byte[] packetData = new byte[packetDataSize];
            int bytesRead = byteStream.read(packetData);

            if (bytesRead < packetData.length - 1) {
                packetData = Arrays.copyOf(packetData, bytesRead);
            }
            byte[] packetDataWMethod = addMethodName(packetData, methodName);
            //Adding Ack to the Data in the end of Packet.
            byte[] modPacketData = addAckToData(senderack, packetDataWMethod);
            DatagramPacket packet = new DatagramPacket(modPacketData, modPacketData.length, internetAddress, receiverPortNumber);
            System.out.println("### Sender sending packet: " + new String(packetData) + "'");
            System.out.println("\n\nPacket Number: " + packetNumber + " out of " + totalPacket);
            boolean sending = true;
            packetNumber++;
            while (sending) {
                try {
                    socket.send(packet);
                    //Start the timer to capture sample RTT.
                    timer.startTimer();
                    //Gets the time Out interval (dynamic)
                    socket.setSoTimeout((int) timer.getTimeOutInterval());
                    // Minor pause for easier visualization only
                    if (slowMode) {
                        System.out.println("Slow Mode Started With sleep if 4 seconds");
                        Thread.sleep(4000);
                    }
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
                    // handle a timout and reset the timer to new value
                    timer.timerTimeOut();
                    socket.setSoTimeout((int)timer.getTimeOutInterval());
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

    public byte[] addMethodName(byte[] rawPacketData, String methodName) {
        String modDataString = new String(rawPacketData);
        modDataString = methodName + "*" + modDataString;
        return modDataString.getBytes();
    }

    public String getHost() throws UnknownHostException {
        return InetAddress.getLocalHost().toString() + "&" + PORT;
    }
}
