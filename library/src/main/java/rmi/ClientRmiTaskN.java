package rmi;

import services.PublicationService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRmiTaskN {
    private ClientRmiTaskN() {}

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            PublicationService stub = (PublicationService) registry.lookup("PublicationService");

            // Example usage of CRUD operations
            stub.createPublication("Example Title", "Example Content");
            System.out.println(stub.readPublication("Example Title"));
            stub.updatePublication("Example Title", "Updated Content");
            System.out.println(stub.readPublication("Example Title"));
            stub.deletePublication("Example Title");

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

