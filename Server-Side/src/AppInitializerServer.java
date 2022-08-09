import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class AppInitializerServer{

    private static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(5002);
        Socket accept;

        while (true){

            System.out.println("Waiting for Client ...");
            accept= serverSocket.accept();
            System.out.println("Client Connected");
            ClientHandler clientThread = new ClientHandler(accept, clientHandlers);
            clientHandlers.add(clientThread);
            clientThread.start();
        }


    }


}
