package rmi;

import services.PublicationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class ServerRmiTaskN extends UnicastRemoteObject implements PublicationService {
    private HashMap<String, String> publications = new HashMap<>();

    protected ServerRmiTaskN() throws RemoteException {
        super();
    }

    @Override
    public String createPublication(String title, String content) {
        publications.put(title, content);
        return "Publication created";
    }

    @Override
    public String readPublication(String title) {
        return publications.getOrDefault(title, "Publication not found");
    }

    @Override
    public String updatePublication(String title, String newContent) {
        if (publications.containsKey(title)) {
            publications.put(title, newContent);
            return "Publication updated";
        } else {
            return "Publication not found";
        }
    }

    @Override
    public String deletePublication(String title) {
        if (publications.containsKey(title)) {
            publications.remove(title);
            return "Publication deleted";
        } else {
            return "Publication not found";
        }
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PublicationService", new ServerRmiTaskN());
            System.out.println("RMI server is running");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

