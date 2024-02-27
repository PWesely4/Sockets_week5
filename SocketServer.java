import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable{

    private int thisServerPort;

    /**
     *This constructor forces port to be passed in that is necessary for startup of ServerSocket
     */
    public SocketServer (int port) {
        thisServerPort = port;
    }


    /**
     * THis thread listens for connecting clients, receives messages and returns a response
     */

    public void run(){

        try(ServerSocket ServerSocket = new ServerSocket(thisServerPort)) {

            System.out.println("Server is listening on port " + thisServerPort);
            while(true) {
                // Server pauses on this line and listens for connection
                Socket Socket = ServerSocket.accept();

                System.out.println("[server]: New client connected: " + Socket.getRemoteSocketAddress());

                //Build a reader
                InputStream input = Socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                //Build a writer
                OutputStream output = Socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                // Get one time message from client
                String receivedMessage = reader.readLine();
                System.out.println("[server]: Server received message: " + receivedMessage);

                writer.println("Server got your message: " + receivedMessage);
            }

        }
        catch(IOException ex){
            System.out.println("[Server]: Server exception " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
