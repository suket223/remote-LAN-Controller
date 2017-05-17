
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


class CommandServer implements Runnable
{

    public void run()
    {
        try
        {
            ServerSocket sersock = new ServerSocket(9000);
            System.out.println("Command Server Started at 9000");
            while (true)
            {

                Socket sock = sersock.accept();
//                System.out.println("connection accepted");
                new Thread(new CommandHandler(sock)).start();
//                System.out.println("connection send to annother thread");

            }
        } catch (Exception e)
        {
//            e.printStackTrace();
        }

    }

    class CommandHandler implements Runnable
    {

        DataInputStream dis;
        DataOutputStream dos;
        Robot robot;

        public CommandHandler(Socket sock)
        {
            try
            {
                robot = new Robot();
                dis = new DataInputStream(sock.getInputStream());
                dos = new DataOutputStream(sock.getOutputStream());
            } catch (Exception ex)
            {
//                ex.printStackTrace();
            }

        }

        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    String command = dis.readLine();
                    if (command.equals("endsession"))
                    {
                        break;

                    } else if (command.equals("check server"))
                    {
                        break;
                    } else if (command.startsWith("mouse"))
                    {
                        StringTokenizer st = new StringTokenizer(command, "#");
                        st.nextToken();
                        int x = Integer.parseInt(st.nextToken());
                        int y = Integer.parseInt(st.nextToken());

                        if (command.startsWith("mouseclickedleft"))
                        {
                            robot.mouseMove(x, y);
                            robot.mousePress(MouseEvent.BUTTON1_MASK);
                            robot.mouseRelease(MouseEvent.BUTTON1_MASK);

                        } else if (command.startsWith("mouseclickedright"))
                        {
                            robot.mouseMove(x, y);
                            robot.mousePress(MouseEvent.BUTTON3_MASK);
                            robot.mouseRelease(MouseEvent.BUTTON3_MASK);
                        } 
                        else if (command.startsWith("mousemoved"))
                        {
                            robot.mouseMove(x, y);
                        }
                        else if(command.startsWith("mousedragged"))
                        {
                            robot.mousePress(MouseEvent.BUTTON1_MASK);
                            robot.mouseMove(x, y);
                        }
                        else if(command.startsWith("mouserelease"))
                        {
                            robot.mouseRelease(MouseEvent.BUTTON1_MASK);
                        }
                    } 
                    else if (command.startsWith("keypressed"))
                    {
                        int keycode = Integer.parseInt(command.substring(11));
                        robot.keyPress(keycode);
                    }
                    else if (command.startsWith("keyreleased"))
                    {
                        int keycode = Integer.parseInt(command.substring(12));
                        robot.keyRelease(keycode);
                    }
                    else if (command.equals("shutdown"))
                    {
                        Process p = Runtime.getRuntime().exec("shutdown -s");
                        break;
                    }
                    else if(command.equals("new message"))
                    {
                        String message = dis.readLine();
                        message = message.replace("he#ll#0", "\n");
                        JOptionPane.showMessageDialog(null,message);
                        break;
                    }
                    else if(command.equals("uploading new file"))
                    {
                        String folderPath = System.getProperty("user.home")+File.separator+"Downloads";
                        String filename = dis.readLine();
                        FileOutputStream fos = new FileOutputStream(folderPath+File.separator+filename);
                        long filesize = dis.readLong();
                        long count=0;
                        byte b[] = new byte[100000];
                        while(true)
                        {
                            int n = dis.read(b,0,b.length);
                            fos.write(b,0,n);
                            count+=n;
                            if(count==filesize)
                            {
                                break;
                            }
                        }
                        dos.writeBytes("file received\r\n");
                        fos.close();
                        dos.close();
                        break;
                    }
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }

        }

    }

}

class PhotoServer implements Runnable
{

    public void run()
    {
        try
        {
            ServerSocket sersock = new ServerSocket(6700);
            System.out.println("Photo Server Started at 6700");

            while (true)
            {
                new Thread(new PhotoSender(sersock.accept())).start();
//              System.out.println("connection build");
            }

        } catch (Exception e)
        {
        }
    }

    class PhotoSender implements Runnable
    {

        DataInputStream dis;
        DataOutputStream dos;

        PhotoSender(Socket sock)
        {
            try
            {
                dis = new DataInputStream(sock.getInputStream());
                dos = new DataOutputStream(sock.getOutputStream());

            } catch (Exception e)
            {
//                e.printStackTrace();
            }
        }

        public void run()
        {
            try
            {

//                File f = new File("e:\\one\\"+index+".png");
//                index++;
//                FileInputStream fis = new FileInputStream(f);
//                long size = f.length();
//                long count = 0;
//                byte b[] = new byte[10000];
                // dos.writeLong(size);
                //Sender Code
                //            Robot robot = new Robot();
//            
//            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//            
//            Rectangle rectangle = new Rectangle(dimension);
//            
//            BufferedImage image = robot.createScreenCapture(rectangle);
//            
//            File f = new File("c:\\test\\screenshot.jpg");
//            
//            ImageIO.write(image,"jpg",f);
//            
//            System.out.println("Done !! ");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(image, "jpg", baos);
                byte imageData[] = baos.toByteArray();

                dos.writeLong(imageData.length);
                dos.write(imageData);

                dis.readLine();

//                while(true)
//                {
//                    int n = fis.read(b,0,10000);
//                    dos.write(b,0,n);
//                    count+=n;
//                    System.out.println("count = "+count);
//                    if(count==size)
//                    {
//                        break;
//                    }
//                }
//                
                //    dis.readLine();
                //  fis.close();
                dis.close();
                dos.close();

            } catch (Exception e)
            {
//                e.printStackTrace();
            }
        }
    }

}

public class Normalcomputer
{

    public static void main(String[] args)
    {
        CommandServer commandServer = new CommandServer();
        PhotoServer photoServer = new PhotoServer();

        Thread t1 = new Thread(commandServer);
        Thread t2 = new Thread(photoServer);

        t1.start();
        t2.start();
        try
        {

            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getLocalHost().getHostName());
        } catch (Exception ex)
        {

        }

    }

}
