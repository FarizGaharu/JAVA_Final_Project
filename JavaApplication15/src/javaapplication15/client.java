/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication15;
import java.awt.event.KeyEvent;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Chris
 */
public class client extends javax.swing.JFrame {
    String username;
    String serverIP = "10.25.151.195";
    int Port = 2222;
    Socket s;
    BufferedReader reader;
    PrintWriter writer;
    ArrayList<String> userList = new ArrayList();
    Boolean isConnected = false;
    /**
     * Creates new form server
     */
    public client() {
        initComponents();
    }
          public class threadmaker implements Runnable{

        public void run() {
            String[] data; // simple array
            String state, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat"; //different type of data we could receive from the server
            //so what we want [0]:username ; [1]:message ; [2] state;
            try {
                while ((state = reader.readLine()) != null) {//when there's no data

                    data = state.split(":"); // in order to split the data into portions

                     if (data[2].equals(chat)) {//array[2] == chat cuz we already split and array start with 4 which mean the last index

                        magichappens.append(data[0] + ": " + data[1] + "\n");// [0] is data username while data[1] usr message

                    } else if (data[2].equals(connect)){
                        //if it happens to be connect then add user to userlist
                        magichappens.removeAll();
                        addbuddies(data[0]);
       
                    } else if (data[2].equals(disconnect)) {
                        //to tell person is disconnect
                        removebuddies(data[0]);

                    } else if (data[2].equals(done)) {
                        //write all buddies online
                        buddylist.setText("");
                        writeUsers();
                        userList.clear();

                    }
                 
                }
           }catch(Exception ex) {
           }
        }
    }
         public void newthread() {
         Thread threadmaker = new Thread(new threadmaker());
         threadmaker.start();
         //creating new thread server if somehow I miraculously can connect to multiple computer
    }

    public void addbuddies(String data) {
         userList.add(data);
         //add  username to data
     }

    public void removebuddies(String data) {
         magichappens.append(data + " has disconnected!\n");
         //state to text are that  user has disconnected
     }

    public void writeUsers() {
         String[] tempList = new String[(userList.size())];
         userList.toArray(tempList);
         for (String token:tempList) {
             //show buddy list that currently online
              buddylist.append(token + "\n");

         }

     }

    public void sendDisconnect() {

       String disconnect = (username + ": :Disconnect");
        try{
            writer.println(disconnect); // Sends server the disconnect signal.
            writer.flush(); // flushes the buffer so that we could create new room for upcoming data
        } catch (Exception e) {
            magichappens.append("Could not send Disconnect message.\n");
        }

      }

    public void Disconnect() {

        try {
               magichappens.append("Disconnected.\n");
               s.close();
        } catch(Exception ex) {
               magichappens.append("Failed to disconnect. \n");
        }
        isConnected = false;
        nick.setEditable(true);
         buddylist.setText("");

      }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nick = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        buddylist = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        magichappens = new javax.swing.JTextArea();
        message = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nickname");

        nick.setText("Anon");
        nick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nickActionPerformed(evt);
            }
        });
        nick.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nickKeyPressed(evt);
            }
        });

        jButton1.setText("Connect");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton2.setText("Disconnect");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Biji Buddies");

        buddylist.setColumns(20);
        buddylist.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        buddylist.setLineWrap(true);
        buddylist.setRows(5);
        buddylist.setDisabledTextColor(java.awt.SystemColor.windowText);
        buddylist.setEnabled(false);
        jScrollPane1.setViewportView(buddylist);

        jPanel1.setBackground(new java.awt.Color(100, 100, 100));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(200, 200, 200));
        jLabel3.setText("BijiClub");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        magichappens.setColumns(20);
        magichappens.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        magichappens.setRows(5);
        magichappens.setDisabledTextColor(java.awt.SystemColor.textText);
        magichappens.setEnabled(false);
        jScrollPane2.setViewportView(magichappens);

        message.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                messageKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(message)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nick, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(6, 6, 6)
                        .addComponent(jButton2)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(message, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nickActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nickActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_jButton2MouseClicked

    private void messageKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageKeyPressed
            if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
      // Enter was pressed. Your code goes here.
      
      //if string null we could print it so that we don't get null error exception
        String nothing = "";
        if ((message.getText()).equals(nothing)) {
           message.setText("");
           message.requestFocus();
        } else {
            try {
                //so we can print in the magichappens text area
               writer.println(username + ":" + message.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                magichappens.append("Message was not sent. \n");
            }
           message.setText("");
           message.requestFocus();
        }
   }
    }//GEN-LAST:event_messageKeyPressed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
            if (isConnected == false) {
            username = nick.getText();
            nick.setEditable(false);

            try {
                //socket in order to connect to server we need ip and port
                s = new Socket(serverIP, Port);
                //basically like DisplayInputStream
                InputStreamReader streamreader = new InputStreamReader(s.getInputStream());
                reader = new BufferedReader(streamreader);
                //Basically DisplayOutputStream
                writer = new PrintWriter(s.getOutputStream());
                // Displays to everyone that user connected.
                writer.println(username + ":has connected.:Connect");
                // flushes the buffer
                writer.flush(); 
                 // Used to check if the client is connected.
                isConnected = true;
            } catch (Exception ex) {
                magichappens.append("Failed to connect! \n");
                nick.setEditable(true);
            }
            newthread();
        } else if (isConnected == true) {
            magichappens.append("You're already connected!. \n");
        }
    }//GEN-LAST:event_jButton1MouseClicked

    //nick = nickname
    private void nickKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nickKeyPressed
        // TODO add your handling code here:
           if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                      if (isConnected == false) {
            username = nick.getText();
            nick.setEditable(false);

            try {
                //socket in order to connect to server we need ip and port
                s = new Socket(serverIP, Port);
                //basically like DisplayInputStream
                InputStreamReader streamreader = new InputStreamReader(s.getInputStream());
                reader = new BufferedReader(streamreader);
                //Basically DisplayOutputStream
                writer = new PrintWriter(s.getOutputStream());
                // Displays to everyone that user connected.
                writer.println(username + ":has connected.:Connect");
                // flushes the buffer
                writer.flush(); 
                 // Used to check if the client is connected.
                isConnected = true;
            } catch (Exception ex) {
                magichappens.append("Failed to connect! \n");
                nick.setEditable(true);
            }
            newthread();
        } else if (isConnected == true) {
            magichappens.append("You're already connected!. \n");
        }
           }
    }//GEN-LAST:event_nickKeyPressed

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
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea buddylist;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea magichappens;
    private javax.swing.JTextField message;
    private javax.swing.JTextField nick;
    // End of variables declaration//GEN-END:variables
}
