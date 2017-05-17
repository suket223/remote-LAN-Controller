
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ViewRemoteSystem extends javax.swing.JFrame
{

    String ip;
    CommandClient commandClient;

    public ViewRemoteSystem(String ip)

    {
        this.ip = ip;
        initComponents();

        commandClient = new CommandClient(ip);
        new Thread(commandClient).start();
        
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50;

        setSize(width, height);
        setVisible(true);

        new Thread(new FileReceiver(ip)).start();
        
        
        
        requestFocus();
        

    }

    class FileReceiver implements Runnable
    {

        String ip;

        public FileReceiver(String ip)
        {
            this.ip = ip;
        }

        public void run()
        {
            while (true)
            {
                //File receiving request code here

                try
                {
                    Socket sock = new Socket(ip, 6700);
                    DataInputStream dis = new DataInputStream(sock.getInputStream());
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

                    long size = dis.readLong();

                    long count = 0;
                    byte b[] = new byte[100000];

                    // File f = new File("c:\\two\\"+System.currentTimeMillis()+".png");
                    // FileOutputStream fos = new FileOutputStream(f);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    //count,byte b[].....
                    while (true)
                    {
                        int r = dis.read(b, 0, 100000);
                        baos.write(b, 0, r);
                        count += r;
                        if (count == size)
                        {
                            break;
                        }

                    }

                    byte imageData[] = baos.toByteArray();
                    ImageIcon imageIcon = new ImageIcon(imageData);
                    lbScreenShot.setIcon(imageIcon);

                    dos.writeBytes("received\r\n");

                    dos.close();
                    dis.close();
                    //  fos.close();

                    // lbPhoto.setIcon(new ImageIcon(f.getPath()));
                    Thread.sleep(100);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }
    }

    class CommandClient implements Runnable
    {

        DataInputStream dis;
        DataOutputStream dos;
        String ip;

        public CommandClient(String ip)
        {
            this.ip = ip;
        }

        public void run()
        {
            try
            {
                Socket sock = new Socket(ip, 9000);
                dis = new DataInputStream(sock.getInputStream());
                dos = new DataOutputStream(sock.getOutputStream());
            } catch (Exception e)
            {
            }
        }
    }

    class MessageSender implements Runnable
    {

        String ip;
        String message;

        public MessageSender(String ip, String message)
        {
            this.ip = ip;
            this.message = message;
        }

        public void run()
        {
            try
            {
                Socket sock = new Socket(ip, 9000);
                PrintWriter pw = new PrintWriter(sock.getOutputStream());
                pw.println("new message");
                pw.println(message);
                pw.flush();
            } catch (Exception e)
            {
            }
        }
    }

    class FileUploader implements Runnable
    {

        String ip;
        String filepath;

        public FileUploader(String ip, String filepath)
        {
            this.ip = ip;
            this.filepath = filepath;
        }

        public void run()
        {
            try
            {
                Socket sock = new Socket(ip, 9000);
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                DataInputStream dis = new DataInputStream(sock.getInputStream());

                dos.writeBytes("uploading new file\r\n");
                File f = new File(filepath);
                FileInputStream fis = new FileInputStream(f);
                long filesize = f.length();
                String filename = f.getName();

                dos.writeBytes(filename + "\r\n");
                dos.writeLong(filesize);

                byte b[] = new byte[100000];
                long count = 0;

                while (true)
                {
                    int n = fis.read(b, 0, b.length);
                    dos.write(b, 0, n);
                    count += n;
                    int per = (int) ((count * 100) / filesize);
                    pbUpload.setValue(per);
                    pbUpload.setString("File uploaded : " + per + "%");
                    if (count == filesize)
                    {
                        break;
                    }
                }
                dis.readLine();
                JOptionPane.showMessageDialog(jTabbedPane1, "File uploaded successfully");
                lbSelectedFilePath.setText("");

            } catch (Exception e)
            {
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        lbScreenShot = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMessage = new javax.swing.JTextArea();
        btSend = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbSelectedFilePath = new javax.swing.JLabel();
        btUpload = new javax.swing.JButton();
        btBrowse1 = new javax.swing.JButton();
        pbUpload = new javax.swing.JProgressBar();
        jPanel4 = new javax.swing.JPanel();
        btShutdown = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                formKeyReleased(evt);
            }
        });
        getContentPane().setLayout(null);

        lbScreenShot.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseDragged(java.awt.event.MouseEvent evt)
            {
                lbScreenShotMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt)
            {
                lbScreenShotMouseMoved(evt);
            }
        });
        lbScreenShot.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                lbScreenShotMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt)
            {
                lbScreenShotMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lbScreenShot);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 940, 550);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(null);

        jLabel1.setText("Enter message here");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(30, 20, 320, 20);

        taMessage.setColumns(20);
        taMessage.setRows(5);
        jScrollPane2.setViewportView(taMessage);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(20, 50, 330, 180);

        btSend.setText("Send");
        btSend.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btSendActionPerformed(evt);
            }
        });
        jPanel2.add(btSend);
        btSend.setBounds(180, 243, 170, 40);

        jTabbedPane1.addTab("Send Notification", jPanel2);

        jPanel3.setLayout(null);

        jLabel2.setText("Select file to upload on remote system");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(20, 20, 330, 14);

        jLabel3.setText("Selected file is :");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(20, 50, 330, 14);

        lbSelectedFilePath.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jPanel3.add(lbSelectedFilePath);
        lbSelectedFilePath.setBounds(20, 80, 330, 20);

        btUpload.setText("Upload");
        btUpload.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btUploadActionPerformed(evt);
            }
        });
        jPanel3.add(btUpload);
        btUpload.setBounds(20, 180, 330, 40);

        btBrowse1.setText("Browse");
        btBrowse1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btBrowse1ActionPerformed(evt);
            }
        });
        jPanel3.add(btBrowse1);
        btBrowse1.setBounds(20, 120, 330, 40);

        pbUpload.setStringPainted(true);
        jPanel3.add(pbUpload);
        pbUpload.setBounds(20, 250, 330, 17);

        jTabbedPane1.addTab("Upload File", jPanel3);

        jPanel4.setLayout(null);

        btShutdown.setText("Shut Down");
        btShutdown.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btShutdownActionPerformed(evt);
            }
        });
        jPanel4.add(btShutdown);
        btShutdown.setBounds(10, 10, 350, 50);

        jTabbedPane1.addTab("Other Options", jPanel4);

        jPanel1.add(jTabbedPane1, "card2");

        getContentPane().add(jPanel1);
        jPanel1.setBounds(950, 10, 380, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbScreenShotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbScreenShotMouseClicked
        // TODO add your handling code here:
        int x, y;
        String command;

        x = lbScreenShot.getMousePosition().x;
        y = lbScreenShot.getMousePosition().y;

        try
        {
            if (evt.getButton() == MouseEvent.BUTTON1)
            {
                command = "mouseclickedleft#" + x + "#" + y;
                commandClient.dos.writeBytes(command + "\r\n");

            } else if (evt.getButton() == MouseEvent.BUTTON3)
            {
                command = "mouseclickedright#" + x + "#" + y;
                commandClient.dos.writeBytes(command + "\r\n");

            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lbScreenShotMouseClicked

    private void lbScreenShotMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbScreenShotMouseMoved
        // TODO add your handling code here:
        int x, y;
        String command;

        x = lbScreenShot.getMousePosition().x;
        y = lbScreenShot.getMousePosition().y;
        command = "mousemoved#" + x + "#" + y;
        try
        {
            commandClient.dos.writeBytes(command + "\r\n");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }//GEN-LAST:event_lbScreenShotMouseMoved

    private void formWindowClosing(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowClosing
    {//GEN-HEADEREND:event_formWindowClosing

        String command;

        try
        {

            command = "endsession";
            commandClient.dos.writeBytes(command + "\r\n");

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formWindowClosing

    private void formKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyPressed
    {//GEN-HEADEREND:event_formKeyPressed
        // TODO add your handling code here:
        int code = evt.getKeyCode();
        String command;
        try
        {

            command = "keypressed#" + code;
            commandClient.dos.writeBytes(command + "\r\n");

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formKeyPressed

    private void btShutdownActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btShutdownActionPerformed
    {//GEN-HEADEREND:event_btShutdownActionPerformed
        try
        {
            commandClient.dos.writeBytes("shutdown\r\n");
        } catch (Exception ex)
        {

        }

    }//GEN-LAST:event_btShutdownActionPerformed

    private void lbScreenShotMouseDragged(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lbScreenShotMouseDragged
    {//GEN-HEADEREND:event_lbScreenShotMouseDragged
        int x, y;
        String command;

        x = lbScreenShot.getMousePosition().x;
        y = lbScreenShot.getMousePosition().y;
        command = "mousedragged#" + x + "#" + y;
        try
        {
            commandClient.dos.writeBytes(command + "\r\n");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lbScreenShotMouseDragged

    private void lbScreenShotMouseReleased(java.awt.event.MouseEvent evt)//GEN-FIRST:event_lbScreenShotMouseReleased
    {//GEN-HEADEREND:event_lbScreenShotMouseReleased
        try
        {
            commandClient.dos.writeBytes("mousereleased\r\n");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lbScreenShotMouseReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_formKeyReleased
    {//GEN-HEADEREND:event_formKeyReleased
        // TODO add your handling code here:
        int code = evt.getKeyCode();
        String command;
        try
        {

            command = "keyreleased#" + code;
            commandClient.dos.writeBytes(command + "\r\n");

        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_formKeyReleased

    private void btSendActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btSendActionPerformed
    {//GEN-HEADEREND:event_btSendActionPerformed
        String msg = taMessage.getText();
        msg = msg.replace("\n", "he#ll#0");
        new Thread(new MessageSender(commandClient.ip, msg)).start();

    }//GEN-LAST:event_btSendActionPerformed

    private void btBrowse1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btBrowse1ActionPerformed
    {//GEN-HEADEREND:event_btBrowse1ActionPerformed
        JFileChooser ch = new JFileChooser();
        int ans = ch.showOpenDialog(this);
        if (ans == JFileChooser.APPROVE_OPTION)
        {
            String filepath = ch.getSelectedFile().getPath();
            lbSelectedFilePath.setText(filepath);
        }
    }//GEN-LAST:event_btBrowse1ActionPerformed

    private void btUploadActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btUploadActionPerformed
    {//GEN-HEADEREND:event_btUploadActionPerformed
        if (!lbSelectedFilePath.getText().equals(""))
        {
            String path = lbSelectedFilePath.getText();
            new Thread(new FileUploader(commandClient.ip, path)).start();
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Select file first");
        }
    }//GEN-LAST:event_btUploadActionPerformed

    public static void main(String[] args)
    {
        new ViewRemoteSystem("172.16.1.100");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBrowse1;
    private javax.swing.JButton btSend;
    private javax.swing.JButton btShutdown;
    private javax.swing.JButton btUpload;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbScreenShot;
    private javax.swing.JLabel lbSelectedFilePath;
    private javax.swing.JProgressBar pbUpload;
    private javax.swing.JTextArea taMessage;
    // End of variables declaration//GEN-END:variables

}
