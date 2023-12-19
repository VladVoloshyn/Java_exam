package socket;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.HashMap;

public class ServerSocketTaskN {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        }
    }

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private static HashMap<String, String> publications = new HashMap<>();

        ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        private String processRequest(String request) {
            String[] parts = request.split(" ", 2);
            String command = parts[0];

            switch (command.toLowerCase()) {
                case "create":
                    String[] createParts = parts[1].split("=", 2);
                    publications.put(createParts[0], createParts[1]);
                    return "Publication created";
                case "read":
                    return publications.getOrDefault(parts[1], "Publication not found");
                case "update":
                    String[] updateParts = parts[1].split("=", 2);
                    publications.put(updateParts[0], updateParts[1]);
                    return "Publication updated";
                case "delete":
                    publications.remove(parts[1]);
                    return "Publication deleted";
                default:
                    return "Invalid command";
            }
        }

        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    String response = processRequest(inputLine);
                    out.println(response);
                }
            } catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port " + PORT + " or listening for a connection");
                System.out.println(e.getMessage());
            }
        }
    }
}
