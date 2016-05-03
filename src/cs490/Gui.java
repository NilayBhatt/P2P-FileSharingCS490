/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs490;

import edu.ccsu.networking.udp.Client;
import edu.ccsu.networking.udp.DirectoryServer;
import edu.ccsu.networking.udp.FileUpload;
import edu.ccsu.networking.udp.RDT10Sender;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.util.Pair;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nilay Bhatt
 */
public class Gui extends javax.swing.JFrame {

    //private RDT10Sender sender = new RDT10Sender();
    private Client client, clientLeecher;
    private File[] sf;
    private DirectoryServer server;

    /**
     * Creates new form gui
     */
    public Gui() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new java.awt.ScrollPane();
        serverGui = new javax.swing.JPanel();
        buttonFileChooser = new javax.swing.JButton();
        lableFileChooser = new javax.swing.JLabel();
        serverIPAddress = new javax.swing.JTextField();
        killConnectionToServer = new javax.swing.JToggleButton();
        syncFiles = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        fileDataTable = new javax.swing.JTable();
        clientPort = new javax.swing.JTextField();
        startServerButton = new javax.swing.JButton();
        slowMode = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        cilentDownloadGui = new javax.swing.JPanel();
        clientServerConnect = new javax.swing.JTextField();
        clientConnectToServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        availableDownloadsTable = new javax.swing.JTable();
        downloadFileButton = new javax.swing.JButton();
        clientLeecherPort = new javax.swing.JTextField();
        slowMode2 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverGui.setBackground(new java.awt.Color(102, 102, 102));

        buttonFileChooser.setText("Files...");
        buttonFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFileChooserActionPerformed(evt);
            }
        });

        lableFileChooser.setText("Click here to Choose the folder to share:");

        serverIPAddress.setText("Enter Server IP Address...");
        serverIPAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serverIPAddressActionPerformed(evt);
            }
        });

        killConnectionToServer.setText("Kill Connection");
        killConnectionToServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                killConnectionToServerActionPerformed(evt);
            }
        });

        syncFiles.setText("Sync Files");
        syncFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncFilesActionPerformed(evt);
            }
        });

        fileDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FIle Name", "File Size"
            }
        ));
        jScrollPane3.setViewportView(fileDataTable);

        clientPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientPortActionPerformed(evt);
            }
        });

        startServerButton.setText("Start Server On this Machine");
        startServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startServerButtonActionPerformed(evt);
            }
        });

        slowMode.setText("SlowMode?");

        jLabel2.setText("Your Sending Port number:");

        javax.swing.GroupLayout serverGuiLayout = new javax.swing.GroupLayout(serverGui);
        serverGui.setLayout(serverGuiLayout);
        serverGuiLayout.setHorizontalGroup(
            serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverGuiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lableFileChooser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
            .addGroup(serverGuiLayout.createSequentialGroup()
                .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(serverGuiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(serverGuiLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(clientPort, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(serverGuiLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(startServerButton))
                    .addGroup(serverGuiLayout.createSequentialGroup()
                        .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(serverGuiLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(serverIPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(serverGuiLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(killConnectionToServer)))
                        .addGap(27, 27, 27)
                        .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(serverGuiLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(slowMode, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(syncFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        serverGuiLayout.setVerticalGroup(
            serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverGuiLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(startServerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonFileChooser)
                    .addComponent(lableFileChooser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clientPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverIPAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(syncFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(serverGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(killConnectionToServer)
                    .addComponent(slowMode))
                .addGap(25, 25, 25))
        );

        cilentDownloadGui.setBackground(new java.awt.Color(204, 204, 204));

        clientServerConnect.setText("Enter Server IP Address...");
        clientServerConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientServerConnectActionPerformed(evt);
            }
        });

        clientConnectToServer.setText("Connect");
        clientConnectToServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientConnectToServerActionPerformed(evt);
            }
        });

        availableDownloadsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "File Name", "File Size", "Host Address", "Host Port"
            }
        ));
        availableDownloadsTable.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                availableDownloadsTableComponentAdded(evt);
            }
        });
        jScrollPane1.setViewportView(availableDownloadsTable);

        downloadFileButton.setText("Download Selected File");
        downloadFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadFileButtonActionPerformed(evt);
            }
        });

        clientLeecherPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientLeecherPortActionPerformed(evt);
            }
        });

        slowMode2.setText("Slow Mode?");

        jLabel1.setText("Connect to the Server and Download Files");

        jLabel3.setText("Your Listening Port Number:");

        javax.swing.GroupLayout cilentDownloadGuiLayout = new javax.swing.GroupLayout(cilentDownloadGui);
        cilentDownloadGui.setLayout(cilentDownloadGuiLayout);
        cilentDownloadGuiLayout.setHorizontalGroup(
            cilentDownloadGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                .addGroup(cilentDownloadGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(cilentDownloadGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                                .addComponent(clientServerConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(slowMode2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clientConnectToServer, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(2, 2, 2)
                                .addComponent(clientLeecherPort, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(jLabel1)))
                .addContainerGap(33, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cilentDownloadGuiLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(downloadFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        cilentDownloadGuiLayout.setVerticalGroup(
            cilentDownloadGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cilentDownloadGuiLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(cilentDownloadGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clientLeecherPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(cilentDownloadGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientServerConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientConnectToServer)
                    .addComponent(slowMode2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(downloadFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serverGui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cilentDownloadGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cilentDownloadGui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(serverGui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void clientPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientPortActionPerformed

    private void syncFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncFilesActionPerformed
        try {
            //server.startSender();
            //client = new Client();
            client.setServerAddress(serverIPAddress.getText());
            client.setServerReceiverPort(2010);
            client.setClientSenderPort(Integer.parseInt(clientPort.getText()));
            client.startClientSender(slowMode.isSelected());
            client.startClientReceiver(Integer.parseInt(clientPort.getText())+2000);
            client.SyncFilesToServer();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_syncFilesActionPerformed

    private void killConnectionToServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_killConnectionToServerActionPerformed
        client.Kill();
    }//GEN-LAST:event_killConnectionToServerActionPerformed

    private void serverIPAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serverIPAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serverIPAddressActionPerformed

    private void buttonFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFileChooserActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        int option = chooser.showOpenDialog(Gui.this);

        if (option == JFileChooser.APPROVE_OPTION) {
            sf = chooser.getSelectedFiles();
            //filesChoosed.setModel(listModel);
            client = new Client();
            client.SetFilesToSend(sf);
            //listModel.addElement("File Name \t\t File Size");
            DefaultTableModel tableModel = (DefaultTableModel) fileDataTable.getModel();
            for (File f : sf) {
                String[] fileArray = {f.getName(), Integer.toString((int) f.length())};
                tableModel.addRow(fileArray);
                //listModel.addElement(f.getName() +"\t" + + f.length());
            }
        }
    }//GEN-LAST:event_buttonFileChooserActionPerformed

    private void startServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startServerButtonActionPerformed
        
        server = new DirectoryServer();
        server.startReceiver(2010);
        System.out.println("Directory Server Started With Port no: 2010");
    }//GEN-LAST:event_startServerButtonActionPerformed

    private void downloadFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadFileButtonActionPerformed
        int row = availableDownloadsTable.getSelectedRow();
        String fileName = (String)availableDownloadsTable.getModel().getValueAt(row, 0);
        String address = (String)availableDownloadsTable.getModel().getValueAt(row, 2);
        address = address.replace("/", "");
        String port = (String)availableDownloadsTable.getModel().getValueAt(row, 3);
        int porting = Integer.parseInt(port) - 2000;
        clientLeecher.setClientSenderPort(Integer.parseInt(port));
        try {
            clientLeecher.RequestFileFromClient(fileName, address, port, false);
            //clientLeecher.RequestFileFromClient(null, null, null, null);
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_downloadFileButtonActionPerformed

    private void clientConnectToServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientConnectToServerActionPerformed
        String serverIP = clientServerConnect.getText();
        clientLeecher = new Client();
        clientLeecher.setClientSenderPort(Integer.parseInt(clientLeecherPort.getText())); //4010
        //clientLeecher.setClientReceiverPort(6010);
        clientLeecher.setServerAddress(serverIP);
        clientLeecher.setServerReceiverPort(2010);
        clientLeecher.startClientSender(slowMode2.isSelected());
        clientLeecher.startClientReceiver(3010);
        //server.startReceiver(3010);
        clientLeecher.RequestFileList();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }
        DefaultTableModel tableModel2 = (DefaultTableModel) availableDownloadsTable.getModel();
        ArrayList<FileUpload> serverFiles = new ArrayList<>();
        serverFiles = clientLeecher.getAvailableFileList();
        for(FileUpload f : serverFiles) {
            String[] temp = {f.getFileName(), Integer.toString((int)f.getFileSize()), f.getHostAddress(), Integer.toString(f.getPort())};
            System.out.println("Adding this to the DataTable: ");
                System.out.println(temp.toString());
            tableModel2.addRow(temp);
        }
    }//GEN-LAST:event_clientConnectToServerActionPerformed

    private void clientServerConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientServerConnectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientServerConnectActionPerformed

    private void availableDownloadsTableComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_availableDownloadsTableComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_availableDownloadsTableComponentAdded

    private void clientLeecherPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientLeecherPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clientLeecherPortActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JTable availableDownloadsTable;
    private javax.swing.JButton buttonFileChooser;
    private javax.swing.JPanel cilentDownloadGui;
    private javax.swing.JButton clientConnectToServer;
    private javax.swing.JTextField clientLeecherPort;
    private javax.swing.JTextField clientPort;
    private javax.swing.JTextField clientServerConnect;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JButton downloadFileButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JTable fileDataTable;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton killConnectionToServer;
    private javax.swing.JLabel lableFileChooser;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JPanel serverGui;
    private javax.swing.JTextField serverIPAddress;
    private javax.swing.JCheckBox slowMode;
    private javax.swing.JCheckBox slowMode2;
    private javax.swing.JButton startServerButton;
    private javax.swing.JButton syncFiles;
    // End of variables declaration//GEN-END:variables

}
