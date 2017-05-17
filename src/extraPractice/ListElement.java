
package extraPractice;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SUKET
 */
public class ListElement 

{
 public String ipaddress,ipname;

    public ListElement(String ipaddress)
    {
        this.ipaddress = ipaddress;
         try
            {
              
                
                ipname= InetAddress.getByName(ipaddress).getCanonicalHostName();
                this.ipname = ipname;
//                System.out.println(ipname);
            } catch (Exception ex)
            {
                this.ipname = "Some Computer";
              
            }

        
    }
   
    }
