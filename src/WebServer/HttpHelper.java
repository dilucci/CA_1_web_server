/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Gruppe 4, Andreas, Michael og Sebastian
 */
public class HttpHelper extends Thread {

    private Socket socket;
    private InetAddress serverAddress;
    private Scanner input;
    private PrintWriter output;

    public void connect(String address, int port) throws UnknownHostException, IOException {
        if (socket == null) {
            serverAddress = InetAddress.getByName(address);
            socket = new Socket(serverAddress, port);
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(), true);  //Set to true, to get auto flush behaviour
            start();
        }
    }
    public int getOnlineUsers() {
        output.println("ONLINEUSERS#");
        String onlineUsers = input.nextLine();
        return Integer.parseInt(onlineUsers);
    }

    public void disconnect() {
        output.println("CLOSE#");
    }

    public String getChatLog() {
        output.println("CHATLOG#");
        String chatLogString = input.nextLine();
        return chatLogString;
    }
}
