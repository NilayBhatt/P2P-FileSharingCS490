/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p.filesharingcs490.GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author Nilay Bhatt
 */
public class SenderGUI extends JFrame {

    public SenderGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Connet Here!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        JPanel ServerConnectPane = new JPanel();

        ServerConnectPane.setBackground(Color.WHITE);

        JButton button = new JButton("Click to connect to the Directory Server.");
        ServerConnectPane.add(button);
        ServerConnectPane.add(new JSeparator(SwingConstants.HORIZONTAL));

        JPanel FileChooserPane = new JPanel();
        FileChooserPane.setBackground(Color.lightGray);
        FileChooserPane.add(InvokeFileChooser());

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, FileChooserPane, ServerConnectPane);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        Dimension minimumSize = new Dimension(100, 50);
        ServerConnectPane.setMinimumSize(minimumSize);
        FileChooserPane.setMinimumSize(minimumSize);

        //Provide a preferred size for the split pane.
        splitPane.setPreferredSize(new Dimension(400, 200));
        frame.getContentPane().add(splitPane);

        frame.setVisible(true);
    }

    public static JButton InvokeFileChooser() {
        JButton fileUploader = new JButton("UploadFiles");
        fileUploader.addActionListener(new ActionListenerImpl());
        return fileUploader;
    }

    private static class ActionListenerImpl implements ActionListener {

        public ActionListenerImpl() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();

        }
    }

    public static void main(String[] args) throws IOException {
    }
//        String message = "hi this is a a message that we want to deliver and show them that the checksum works and travis is awesome and Lauren sucks";
//        ByteArrayInputStream byteStream = new ByteArrayInputStream(message.getBytes());
//        int packetNumber = 0;
//        byte[] packetData = new byte[124];
//        while (byteStream.available() > 0) {
//            int bytesRead = byteStream.read(packetData);
//            if (bytesRead < packetData.length) {
//                packetData = Arrays.copyOf(packetData, bytesRead);
//            }
//            //new SenderGUI();
//        }
//        int checksum = Arrays.hashCode(packetData); // Adding corruption checking.
//        System.out.println(checksum);
//
//        ByteBuffer bb = ByteBuffer.allocate(4);
//
//        bb.putInt(checksum);
//        byte[] csArray = bb.array();
//        
//        byte[] finalPacketData = new byte[155];
//        System.arraycopy(csArray, 0, finalPacketData, 0, csArray.length);
//        System.arraycopy(packetData, 0, finalPacketData, csArray.length, finalPacketData.length);
//        
//        System.out.println(Arrays.toString(finalPacketData));
//    }
}
