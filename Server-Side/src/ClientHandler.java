import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:22 PM
 */

public class ClientHandler extends Thread {
    private ArrayList<ClientHandler> clientHandlers;
    private Socket accept;
    private BufferedReader reader;
    private PrintWriter writer;

    public ClientHandler(Socket accept, ArrayList<ClientHandler> clientHandlers) {
        try {
            this.accept = accept;
            this.clientHandlers = clientHandlers;
            this.reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            this.writer = new PrintWriter(accept.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            String msg;
            while ((msg = reader.readLine()) != null) {
                if (msg.equalsIgnoreCase( "exit")) {
                    break;
                }
                for (ClientHandler cl : clientHandlers) {
                    cl.writer.println(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
