package socket;

import java.io.*;
import java.net.*;

public class ClientSocketTaskN {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            String userInput;
            out.println("create MyArticle=Це стаття про науку");
            System.out.println("Server response: " + in.readLine());
            out.println("read MyArticle");
            System.out.println("Server response: " + in.readLine());
            out.println("update MyArticle=Оновлений зміст статті");
            System.out.println("Server response: " + in.readLine());
            out.println("read MyArticle");
            System.out.println("Server response: " + in.readLine());
            out.println("delete MyArticle");
            System.out.println("Server response: " + in.readLine());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + HOST);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + HOST);
            System.exit(1);
        }
    }
}

