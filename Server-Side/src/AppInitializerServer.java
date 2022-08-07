import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AppInitializerServer{

    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private static final int PORT=5000;
    private static ServerSocket serverSocket;
    private static Socket accept;

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                System.out.println("Waiting for clients...");
                accept= serverSocket.accept();
                System.out.println("Client Connected...");
                ClientHandler clientThread = new ClientHandler(accept, clientHandlers);
                clientHandlers.add(clientThread);
                clientThread.start();


            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }


}
