import java.util.Scanner;

public class SocketsTest {

    public static void main(String[] args) {

        //First get port for this server
        System.out.print("Enter port for this server for this server to listen on: ");
        int thisServerPort = new Scanner(System.in).nextInt();

        //Next get IP address and port of server to connect to
        System.out.print("Enter IP address of server server to connect to: ");
        String otherServerIP = new Scanner(System.in).nextLine();

        System.out.print("Enter port of server to connect to: ");
        int otherServerPort = new Scanner(System.in).nextInt();

        //create multithreaded server
        SocketServer server = new SocketServer(thisServerPort);
        Thread serverThread = new Thread(server);
        serverThread.start();

        // Delay thread to give server time to start
        try {
            Thread.sleep(500);
        }
        catch(Exception ex){}

        while(true) {
            System.out.print("Message to send: ");
            String userMessage = new Scanner(System.in).nextLine();

            SocketClient client = new SocketClient();
            String reply = client.connectForOneMessage(otherServerIP, otherServerPort, userMessage);
            System.out.println("[Client]: Reply from server: " + reply);
        }
    }
}
