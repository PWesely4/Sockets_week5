import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketClient {

    public String connectForOneMessage(String IP, int Port, String Message) {

        try(Socket Socket = new Socket()) {
            Socket.connect(new InetSocketAddress(IP, Port), 5000);

            //Build a writer
            OutputStream output = Socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            writer.println(Message);

            //Build a reader
            InputStream input = Socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String Return = reader.readLine();
            return Return;
        }
        catch(Exception ex){
            System.out.println("[Client]: client exception " + ex.getMessage());
            ex.printStackTrace();

            return null;
        }

    }
}
