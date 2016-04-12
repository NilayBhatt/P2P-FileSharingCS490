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
    private final int packetDataSize = 15;
    private final int checksumSize = 4;
    private final byte[] ack1 = "1".getBytes();
    private final byte[] ack0 = "0".getBytes();
    private byte[] senderack = "0".getBytes();
    private byte[] ack = new byte[1];

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

        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);
        int packetNumber = 0;
        // Get a new timer for measuring RTT
        Timer timer = new Timer();
        while (byteStream.available() > 0) {
            byte[] packetData = new byte[packetDataSize];
            int bytesRead = byteStream.read(packetData);

            if (bytesRead < packetData.length - 1) {
                packetData = Arrays.copyOf(packetData, bytesRead);
            }
            byte[] modPacketData = addAckToData(senderack, packetData);

            DatagramPacket packet = new DatagramPacket(modPacketData, modPacketData.length, internetAddress, receiverPortNumber);
            System.out.println("### Sender sending packet: " + new String(packetData) + "'");
            boolean sending = true;
            while (sending) {
                try {
                    socket.send(packet);
                    socket.setSoTimeout(100);
                    // Minor pause for easier visualization only
                    //Thread.sleep(1200);
                    if (receiveAck()) {
                        sending = false;
                    }
                } catch (SocketTimeoutException e) {
                    System.out.println("We got a time out for packet: " + new String(packet.getData()));
                    //socket.send(packet);
                    //socket.setSoTimeout(100);
                    continue;
                }
            }
            System.out.println("### Sender done sending");
            senderack = ack0;
        }
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
}
