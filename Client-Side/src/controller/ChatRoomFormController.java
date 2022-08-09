/**
 * @author : Udyogi Siphara
 * Project Name: Chat-Room
 * Date        : 8/6/2022
 * Time        : 1:21 PM
 */

package controller;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ChatRoomFormController extends Thread{

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
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;


    public void initialize() throws IOException {
        for (User ReqUser : users) {
            userName.setText(ReqUser.fullName + "");
        }
        socket = new Socket("localhost",5000);
        System.out.println("Socket is connected with server!");
        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.start();
    }

    public void sendMessageByKey(KeyEvent keyEvent) {

    }

    public void handleSendEvent(MouseEvent mouseEvent) {

    }
}
