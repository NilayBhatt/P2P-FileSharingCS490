package edu.ccsu.networking.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * These classes contain a very simple example of a UDT send and receive similar
 * to the RDT 1.0 covered in class. A receiver thread is started that begins
 * listening on 49000 that waits for packets to delivers. A sending socket is
 * then opened for sending data to the receiver.
 *
 * With a real sender a large message will need to be broken into packets so
 * this demo also shows one way to break a larger message up this way.
 *
 * @author Chad Williams
 */
public class Main {
    public static void main(String[] args) {
        RDT10Receiver receiverThread = null;
        try {
            //Start receiver
            receiverThread = new RDT10Receiver("Receiver", 55000);
            receiverThread.start();
//            while (true) {
//                Thread.sleep(1000);
//            }

            // Create sender
            byte[] targetAdddress = {127, 0, 0, 1};
            //byte[] targetAdddress = {(byte) 192, (byte) 168, (byte) 1, (byte) 2};
            RDT10Sender sender = new RDT10Sender();
            sender.startSender(targetAdddress, 55000);

            for (int i = 0; i < 10; i++) {
                String data = "Here is the message I want to send and I am rebuilding the whole data upon full delivery Nilay";

                //Hack for spaces.s
                data = data.replace(" ", "%");
                // Send the data
                sender.rdtSend(data.getBytes());

                // Sleeping simply for demo visualization purposes
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}