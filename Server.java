import java.io.*;
import java.net.*;
import java.util.Vector;

public class Server {
    // declare thedefault parameter variables
    protected Socket ctSocket = null;
    protected ServerSocket serSocket = null;
    protected ServerThread[] serThreads = null;
    protected int clientNums = 0;

    // set a max number of clients
    public static int maxClients = 50;

    // create a server port
    public static int serverPort = 41901;

    // create a server function
    public Server() {
        // use try catch to catch any variables
        try {
            // set the server socket
            serSocket = new ServerSocket(serverPort);

            // output a message to the user
            System.out.println("---------------------------");
            System.out.println("Server Application is running");
            System.out.println("---------------------------");
            System.out.println("Listening to port: " + serverPort);

            // set the length of the serThreads variable
            serThreads = new ServerThread[maxClients];

            // use a while loop to constantly loop through the clients at hand
            while (true) {
                ctSocket = serSocket.accept();
                System.out.println("Client #" + (clientNums + 1) + " connected.");
                serThreads[clientNums] = new ServerThread(ctSocket);
                serThreads[clientNums].start();
                clientNums++;
            }
        } catch (Exception e) {
            // output error
            System.out.println("Failure has occured when connecting to the server");
        }
    }

    public static void main(String[] args) {
        Server altServer = new Server();
    }
}
