import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class DataInput {
    public static String Input(){
        String username = "";
        String password = "";
        try{
            FileInputStream fis = new FileInputStream("log.txt");
            DataInputStream dis = new DataInputStream(fis);
            username = dis.readLine();
            fis.close();
            dis.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return username;
    }
}
