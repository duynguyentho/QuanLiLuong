import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class DataOutput {
    public static void Output(String username, String password){
        try
        {
            FileOutputStream fos = new FileOutputStream("log.txt");
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes(username+ "-" +password);
            dos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
