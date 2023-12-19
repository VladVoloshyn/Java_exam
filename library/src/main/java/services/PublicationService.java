package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

// ... Existing imports ...

public interface PublicationService extends Remote {
    String createPublication(String title, String content) throws RemoteException;
    String readPublication(String title) throws RemoteException;
    String updatePublication(String title, String newContent) throws RemoteException;
    String deletePublication(String title) throws RemoteException;
}

