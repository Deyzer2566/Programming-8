package net;

import data.StudyGroup;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class RemoteDatabaseWithAuthAndInfoAboutGroupsOwners extends RemoteDatabaseWithAuth{

    public RemoteDatabaseWithAuthAndInfoAboutGroupsOwners(String host, int port) throws IOException {
        super(host, port);
    }

    public HashMap<Integer, LinkedList<Long>> getOwnersOfGroups(){
        try {
            sendLoginAndPassword();
            send(new Object[]{"getOwnersOfGroups"});
        } catch (IOException e) {
            disconnect();
            return null;
        }
        HashMap<Integer, LinkedList<Long>> info = null;
        try {
            info = (HashMap<Integer, LinkedList<Long>>) recieve();
        } catch (ClassNotFoundException | ClassCastException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return info;
    }
}
