/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:21 PM
 */

package controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.User;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatRoomFormController extends Thread {

    public Circle proPic;
    public Label userName;
    public Pane profile;
    public Pane chat;
    public JFXTextArea txtChatbox;
    public TextField txtmsg;
    public JFXTextArea txtMsgBox;
    public TextField txtMsgFiled;
    public AnchorPane chatRoomContext;

    public static ArrayList<User> users = SignupFormController.users;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    public void initialize(){
        for (User ReqUser : users) {
            userName.setText(ReqUser.fullName + "");
        }
        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        try {
            while (true) {

                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
//                txtTextArea.appendText(cmd+"\n");
                StringBuilder fullMsg = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMsg.append(tokens[i]);
                }
                System.out.println(fullMsg);
//                txtTextArea.appendText(cmd+" "+fullMsg+"\n");
                /*if (cmd.equalsIgnoreCase("Client" + ":")) {
                    continue;
                } else if (fullMsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }*/

                System.out.println("cmd="+cmd+"-----"+"UserName"+userName.getText());
                if(!cmd.equalsIgnoreCase(userName.getText()+":")){
                    txtChatbox.appendText(msg + "\n");
                }

            }
//            reader.close();
//            writer.close();
//            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendMessageByKey(KeyEvent keyEvent) throws IOException {
        String message = txtmsg.getText();
        writer.println(userName.getText() + ": " + txtmsg.getText());
        txtChatbox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtChatbox.appendText("Me :"+message+"\n");
        txtmsg.setText("");
        if (message.equalsIgnoreCase("By") || message.equalsIgnoreCase("Bye") || message.equalsIgnoreCase("by") || message.equalsIgnoreCase("bye")){
            System.exit(0);
            Stage stage = (Stage) txtmsg.getScene().getWindow();
            stage.close();
        }
    }

    public void handleSendEvent(MouseEvent mouseEvent) throws IOException {
        String message = txtmsg.getText();
        writer.println(userName.getText() + ": " + txtmsg.getText());
        txtChatbox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtChatbox.appendText("Me :"+message+"\n");
        txtmsg.setText("");
        if (message.equalsIgnoreCase("By") || message.equalsIgnoreCase("Bye") || message.equalsIgnoreCase("by") || message.equalsIgnoreCase("bye")){
                System.exit(0);
                Stage stage = (Stage) txtmsg.getScene().getWindow();
                stage.close();
        }
    }

}
