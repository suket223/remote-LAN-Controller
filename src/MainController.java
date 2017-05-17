
import extraPractice.ListElement;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MainController extends javax.swing.JFrame
{

    ArrayList<ListElement> al = new ArrayList<>();   //  STORES IP OF ONLINE DEVICES
    ArrayList<ListElement> bl = new ArrayList<>();   //  STORES IP OF ONLINE DEVICES HAVING PORT 7600 OPEN
    MyTableModel tm;
    MyTableModel1 tm1;

    String ipClass;

    public MainController()
    {

        initComponents();

        int width = 760;
        int height = 510;

        setSize(width, height);
        setVisible(true);
        int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

        int x = screenWidth / 2 - width / 2;
        int y = screenHeight / 2 - height / 2;

        setLocation(x, y);

        tm = new MyTableModel();
        jTable1.setModel(tm);
        tm1 = new MyTableModel1();
        jTable2.setModel(tm1);
        
        try
        {
        String selfIP
                        = InetAddress.getLocalHost().getHostAddress();
                tfIP.setText(selfIP);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lbHeading = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btShutdownAll = new javax.swing.JButton();
        tfIP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Remote Lan Controller (MainController)");
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(null);

        lbHeading.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbHeading.setForeground(new java.awt.Color(255, 255, 255));
        lbHeading.setText("Control Panel");
        jPanel1.add(lbHeading);
        lbHeading.setBounds(50, 10, 140, 30);

        jButton1.setText("SCAN");
        jButton1.setToolTipText("Click this button to scan other network devices");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(10, 130, 200, 60);

        jButton2.setText("CHECK SERVER");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(10, 200, 200, 60);

        jButton3.setText("VIEW SYSTEM");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(10, 270, 200, 60);

        btShutdownAll.setText("SHUTDOWN ALL");
        btShutdownAll.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btShutdownAllActionPerformed(evt);
            }
        });
        jPanel1.add(btShutdownAll);
        btShutdownAll.setBounds(10, 340, 200, 80);

        tfIP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(tfIP);
        tfIP.setBounds(10, 70, 200, 50);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter your ip here");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 50, 100, 14);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(20, 20, 220, 430);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new java.awt.CardLayout());

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable2.setRowHeight(30);
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, "card2");

        getContentPane().add(jPanel2);
        jPanel2.setBounds(500, 20, 220, 430);

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new java.awt.CardLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, "card2");

        getContentPane().add(jPanel3);
        jPanel3.setBounds(260, 20, 220, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String ip = tfIP.getText();

        if (ip.trim().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter ip first");
        } else
        {
            al.clear();
            
            ipClass = tfIP.getText();
            
            SystemDiscovery obj = new SystemDiscovery(ipClass);
        Thread t = new Thread(obj);
        t.start();
        jButton1.setEnabled(false);
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        bl.clear();
        ControllerClient obj = new ControllerClient("check server");
        Thread t = new Thread(obj);
        t.start();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int loc = jTable2.getSelectedRow();
        if (loc == -1)
        {
            JOptionPane.showMessageDialog(this, "PLEASE SELECT IP FIRST");
        }
        String ip = (bl.get(loc)).ipaddress;
        ViewRemoteSystem obj = new ViewRemoteSystem(ip);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void btShutdownAllActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btShutdownAllActionPerformed
    {//GEN-HEADEREND:event_btShutdownAllActionPerformed
        ControllerClient obj = new ControllerClient("shutdown all");
        Thread t = new Thread(obj);
        t.start();


    }//GEN-LAST:event_btShutdownAllActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(MainController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MainController().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btShutdownAll;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lbHeading;
    private javax.swing.JTextField tfIP;
    // End of variables declaration//GEN-END:variables

    //***************INNER CLASS CLIENT ***********
    class ControllerClient implements Runnable
    {

        Socket sock;
        String command;
        DataInputStream dis;
        DataOutputStream dos;

        ControllerClient(String commandWord)
        {
            command = commandWord;
        }

        @Override
        public void run()
        {
            if (command.equals("check server"))
            {
                Thread th[] = new Thread[al.size()];
                CheckServerJob job[] = new CheckServerJob[al.size()];
                for (int i = 0; i < job.length; i++)
                {
                    job[i] = new CheckServerJob(al.get(i).ipaddress);
                    th[i] = new Thread(job[i]);
                    th[i].start();

                }

                try
                {
                    for (int i = 0; i < job.length; i++)
                    {
                        th[i].join();
                    }
//                    System.out.println(bl);
                } catch (Exception e)
                {
//                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(MainController.this, "Done !!");

            } else if (command.equals("shutdown all"))
            {
                Thread th[] = new Thread[bl.size()];
                ShutDownAll job[] = new ShutDownAll[bl.size()];
                for (int i = 0; i < job.length; i++)
                {
                    job[i] = new ShutDownAll(bl.get(i).ipaddress);
                    th[i] = new Thread(job[i]);
                    th[i].start();

                }

                try
                {
                    for (int i = 0; i < job.length; i++)
                    {
                        th[i].join();
                    }
//                    System.out.println(bl);
                } catch (Exception e)
                {
//                    e.printStackTrace();
                }
            }
            JOptionPane.showMessageDialog(MainController.this, "All systems ShutDown");

        }

        class ShutDownAll implements Runnable
        {

            String ip;

            public ShutDownAll(String ip)
            {
                this.ip = ip;
            }

            @Override
            public void run()
            {
                try
                {
                    Socket sock = new Socket(ip, 9000);
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    dos.writeBytes("shutdown\r\n");

                } catch (Exception e)
                {
//                e.printStackTrace();
                }
            }

        }

        class CheckServerJob implements Runnable
        {

            String ip;

            public CheckServerJob(String ip)
            {
                this.ip = ip;

            }

            @Override
            public void run()
            {
                try
                {
                    Socket sock = new Socket(ip, 9000);
                    DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
                    dos.writeBytes("check server\r\n");
                    bl.add(new ListElement(ip));
                    tm1.fireTableDataChanged();
                } catch (Exception e)
                {
//                e.printStackTrace();
                }

            }

        }

    }

    class MyTableModel extends AbstractTableModel
    {

        @Override
        public String getColumnName(int i)
        {
            return "Online Devices";
        }

        @Override
        public int getRowCount()
        {

            return al.size();
        }

        @Override
        public int getColumnCount()
        {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            ListElement le = al.get(rowIndex);

            return le.ipname;
        }

    }

    class MyTableModel1 extends AbstractTableModel
    {

        @Override
        public String getColumnName(int i)
        {
            return "Online Servers";
        }

        @Override
        public int getRowCount()
        {

            return bl.size();
        }

        @Override
        public int getColumnCount()
        {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            ListElement le = bl.get(rowIndex);

            return le.ipname;
        }

    }

    class SystemDiscovery implements Runnable
    {

        String ip;
        int pos;

        public SystemDiscovery(String f)
        {

            int lastIndexOf = f.lastIndexOf(".");
            ip = f.substring(0, (lastIndexOf + 1));
            //  System.out.println(ip);

        }

        PingJob jobs[] = new PingJob[51];
        Thread th[] = new Thread[51];
        int count = 1;

        @Override
        public void run()
        {
            for (int m = 1; m <= 5; m++)
            {
                for (int i = 0; i < 51; i++)
                {
                    jobs[i] = new PingJob(ip + count);
                    th[i] = new Thread(jobs[i]);
                    th[i].start();
                    count++;
                }
                for (int i = 0; i < 51; i++)
                {
                    try
                    {
                        th[i].join();
                    } catch (Exception e)
                    {
                    }
                }
            }
            JOptionPane.showMessageDialog(MainController.this, "Scanning Completed........");
            jButton1.setEnabled(true);
//            for (String ips : al);
//            {
//                System.out.println(ip);
//            }
            //  System.out.println("----- all online ips ------");
            // for (String ip : al)
            //{
            //   System.out.println(ip);
            // }
        }

        class PingJob implements Runnable
        {

            String ip;

            public PingJob(String ip)
            {
                this.ip = ip;
            }

            @Override
            public void run()
            {
                try
                {
                    Process p
                            = Runtime.getRuntime()
                            .exec("ping " + ip);
                    BufferedReader br
                            = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while (true)
                    {
                        String s = br.readLine();
                        if (s == null)
                        {
                            break;
                        }
                        s = s.toLowerCase();
                        if (s.contains("ttl"))
                        {
                            al.add(new ListElement(ip));
                            tm.fireTableDataChanged();
                            break;
                        } else if (s.contains("destination host unreachable"))
                        {
                            break;
                        } else if (s.contains("request timed out"))
                        {
                            break;
                        }

                    }
                } catch (Exception ex)
                {

                }
            }
        }

    }

}
